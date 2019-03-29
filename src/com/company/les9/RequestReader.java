package com.company.les9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestReader {

    private String path;
    private final BufferedReader bufferedReader;

    public RequestReader(Socket client) throws IOException {
        bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String line = bufferedReader.readLine();
        if (null != line && line.contains("GET")) path = line.split(" ")[1];
        else throw new IOException("Invalid method");
    }

    public String getPath() {
        return path;
    }

    public void close() {
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
