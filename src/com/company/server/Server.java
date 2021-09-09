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

                System.out.println("Client send:");

                // Клиент вводит два числа через запятую, оно выводит сумму двух этих чисел
                String read_from_client = in.readLine();
                /*
                String array[] = read_from_client.split(", ");
                for (var i = 0; i < array.length; i++) {
                    System.out.println(array[i]);
                }
                int num1 = Integer.parseInt(array[0]);
                int num2 = Integer.parseInt(array[1]);
                int res = num1 + num2;
                out.write(num1 + " + " + num2 + " = " + res + "\n");
                */

                // Клиент вводит путь к файлу, оно считывает количество знаков в файле и считает его
                int count = 0;
                String clientSend;
                File file = new File(read_from_client);
                FileInputStream fis = new FileInputStream(file);
                byte[] ByteArray = new byte[(int) file.length()];
                String s = new String(ByteArray);
                String[] data = s.split(" ");
                for (int i = 0; i < data.length; i++) {
                    count++;
                }
                System.out.println("Num of symbols = " + count);

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
