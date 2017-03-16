package com.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] a) {
        try {
            ServerSocket serverSocket = new ServerSocket(4444);
            while (true) {

                Socket accept = serverSocket.accept();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static class SocketRun implements Runnable {
        Socket socket;

        public SocketRun(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();
                while (true) {
                    byte[] arr = new byte[1024];
                    int len;
                    while ((len = inputStream.read(arr)) != -1) {
                        System.out.println(len);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
