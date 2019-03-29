package com.company.les9;

import java.util.HashMap;
import java.util.Map;

public class ResponseHeader {

    private Map<Integer, String> statuses = new HashMap<Integer, String>() {
        {
            put(200, "OK");
            put(404, "Not Found");
            put(500, "Internal Server Error");
        }
    };

    private int httpCode;
    private long length;
    private String mimeType;

    public ResponseHeader(int httpCode, long length, String mimeType) {
        this.httpCode = httpCode;
        this.length = length;
        this.mimeType = mimeType;
    }

    @Override
    public String toString() {
        return "HTTP/1.0 "+ httpCode + " " + statuses.get(httpCode) +"\n" +
                "Server: java\n" +
                "Content-Length: " + length + "\n" +
                "Content-Type: " + mimeType + "\n\n";
    }
}
