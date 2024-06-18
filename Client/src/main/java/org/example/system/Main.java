package org.example.system;

import org.example.network.UDPClient;

public class Main {
    public static void main(String[] args) {
        try {
            UDPClient udpClient = new UDPClient(Integer.parseInt(args[0]));
            udpClient.start();
        } catch (Exception e) {
            System.out.println("Что-то пошло не так: " + e.getMessage());
        }
    }
}