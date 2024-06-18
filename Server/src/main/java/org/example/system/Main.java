package org.example.system;

import org.example.filelogic.WriterJSON;
import org.example.network.Server;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        String path = System.getenv("lab5");
        if (path == null) {
            System.out.println("Что-то не так с переменной окружения");
            System.exit(1);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Сохранение данных...");
            try {
                WriterJSON.write(path);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }));

        try {
            Server server = new Server(8080, path);
            server.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Что-то пошло не так");
        }
    }
}