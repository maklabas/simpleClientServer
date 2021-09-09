package com.company.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server {
    private static Socket clientSocket;
    private static ServerSocket serverSocket;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(4000);
            System.out.println("Server start!");

            clientSocket = serverSocket.accept();

            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));


                String read_from_client = in.readLine();
                System.out.println("Client sent:");
                System.out.println(read_from_client);

                // Клиент вводит путь к файлу, оно считывает количество знаков в файле и считает его
                int count = 0;
                String[] sub_str;
                String delimiter = " ";

                sub_str = read_from_client.split(delimiter);

                for (int i = 0; i < sub_str.length; i++) {
                    count++;
                }

                out.write("Num of symbols = " + count + "\n");
                out.write("[SERVER GOT MSG]");
                out.flush();
            } finally {
                clientSocket.close();
                in.close();
                out.close();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
