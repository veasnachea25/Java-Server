package com.company.les9;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

public class Server implements Runnable {


    @Override
    public void run() {
        try {

            ServerSocket serverSocket = new ServerSocket(8080);
            while (true) {
                Socket client = serverSocket.accept();
                new Thread() {
                    @Override
                    public void run() {
                        new Connection(client).handle();
                    }
                }.start();

            } //close main while loop

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        Server server1 = new Server();
        server1.run();
    }
}
