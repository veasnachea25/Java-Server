package com.company.les9;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class Connection {
    public static final File filesContainer = new File("C:\\Users\\DELL\\IdeaProjects\\Server\\src\\com\\company\\les9\\files");
    private Socket client;

    public Connection(Socket client) {
        this.client = client;
    }

    public void handle() {
        try {
            System.out.println(client.getInetAddress().toString() + " connected");
            RequestReader requestReader = new RequestReader(client);

            String path = requestReader.getPath();
            ResponseHandler handler = ("/".equals(path))
                    ? new RootResponseHandler()
                    : new FileResponseHandler();
            handler.handle(path, client);
            requestReader.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
