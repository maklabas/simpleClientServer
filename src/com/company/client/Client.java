package com.company.client;

import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket clientSocket;
    private  static BufferedReader reader;
    private  static BufferedReader in;
    private  static BufferedWriter out;

    public static  void main(String[] args) {
        try {
            try {
                clientSocket = new Socket("localhost", 4000);


                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                System.out.println("Enter some string:");
                String msg = reader.readLine();
                out.write(msg + "\n");
                out.flush();

                System.out.println(in.readLine());
            }
            finally {
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
