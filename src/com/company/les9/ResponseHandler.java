package com.company.les9;

import java.net.Socket;

public interface ResponseHandler {
    void handle(String path, Socket client);
}
