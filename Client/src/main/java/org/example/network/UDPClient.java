package org.example.network;

import org.example.network.Request;
import org.example.network.Response;
import org.example.recources.generator.WorkerGenerator;
import org.example.system.ScriptExecutor;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Scanner;

public class UDPClient {
    private int port;
    private ArrayList<String> commandsWithWorker = new ArrayList<>();
    private DatagramSocket socket = new DatagramSocket();
    private InetAddress address;

    public UDPClient(int port) throws SocketException {
        this.port = port;
        commandsWithWorker.add("add");
        commandsWithWorker.add("update");
        commandsWithWorker.add("insert_at");
    }

    public void start() {
        try {
            address = InetAddress.getByName("localhost");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Введите сообщение: ");
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("exit")) {
                    break;
                } else if (message.contains("execute_script")) {
                    ScriptExecutor.executeScript(message.split(" ")[1], this);
                } else {
                    Request request = new Request(message);
                    if (commandsWithWorker.contains(message.split(" ")[0])) {
                        request.setWorker(WorkerGenerator.createWorker());
                    }
                    sendRequest(request);
                    Response response = getResponse();
                    System.out.println(response.getLine());
                }
            }

            socket.close();
            scanner.close();
        } catch (IOException e) {
            System.out.println("Возникла ошибка при получении ответа от сервера");
            start();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void sendRequest(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        ByteBuffer buffer = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        DatagramPacket packet = new DatagramPacket(buffer.array(), byteArrayOutputStream.size(), address, port);
        socket.send(packet);
    }

    public Response getResponse() throws IOException, ClassNotFoundException {
        byte[] bufferReader = new byte[10000];
        DatagramPacket packetGet = new DatagramPacket(bufferReader, bufferReader.length);

        try {
            socket.setSoTimeout(5000);
            socket.receive(packetGet);
            // Обработка полученных данных
        } catch (SocketTimeoutException e) {
            // Обработка ситуации, когда ответ не был получен в течение 5 секунд
            System.out.println("Возникла ошибка при чтении запроса, попробуйте позже");
        } catch (IOException e) {
            // Обработка других ошибок ввода-вывода
            System.out.println("Возникла ошибка при чтении запроса, попробуйте позже");
        } finally {
            // socket.close();
        }

        ByteBuffer bg = ByteBuffer.wrap(packetGet.getData());
        bg.flip();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bg.array());
        ObjectInputStream oi = new ObjectInputStream(byteArrayInputStream);
        return (Response) oi.readObject();
    }
}