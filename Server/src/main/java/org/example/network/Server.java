package org.example.network;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.exceeption.ReadFileException;
import org.example.exceeption.RootException;
import org.example.filelogic.ReaderJSON;
import org.example.managers.CommandManager;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Server {
    private int port;
    private DatagramChannel channel;
    private InetSocketAddress clientAddress;
    private static String path;
    private CommandManager commandManager;

    public Server(int port, String path_) throws IOException {
        this.port = port;
        path = path_;
        SocketAddress socket = new InetSocketAddress(port);
        channel = DatagramChannel.open();
        channel.bind(socket);
        this.commandManager = new CommandManager();
    }

    public void start(){
        try {
            ReaderJSON.read(path);
        } catch (RootException | JsonProcessingException | ReadFileException e) {
            System.out.println("Что-то не так с файлом");
        }

        while (true){
            try {
                Request request = getRequest();
                String answer = execute(request);
                sendResponse(answer);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
    }

    public Request getRequest() throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(10000);
        clientAddress = (InetSocketAddress) channel.receive(buffer);
        buffer.flip();

        System.out.println("Получен запрос от клиента " + clientAddress);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer.array());
        ObjectInputStream oi = new ObjectInputStream(byteArrayInputStream);
        return (Request) oi.readObject();
    }

    public String execute(Request request){
        return commandManager.executeCommand(request);
    }

    public void sendResponse(String line) throws IOException {
        Response request = new Response(line);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);

        ByteBuffer buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        channel.send(buffer, clientAddress);

        System.out.println("Отправлен ответ клиенту " + clientAddress);
    }

    public static String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
