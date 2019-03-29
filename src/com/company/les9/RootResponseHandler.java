package com.company.les9;

import com.company.les9.ResponseHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import static com.company.les9.Connection.filesContainer;

public class RootResponseHandler implements ResponseHandler {
    @Override
    public void handle(String path, Socket client) {
        StringBuilder htmlText = new StringBuilder("<html><head></head><body>");
        File[] files = filesContainer.listFiles();
        if (null == files) {
            htmlText.append("Empty!");
        } else {
            htmlText.append("<ul>");
            for (File file : files) {
                htmlText.append("<li><a href=\"" + "\\")
                        .append(file.getName())
                        .append("\">")
                        .append(file.getName())
                        .append("</a></li>");
            }
            htmlText.append("</ul>");
        }
        htmlText.append("</body></html>\n");
        try {
            OutputStream out = client.getOutputStream();
            PrintWriter outWriter = new PrintWriter(out);
            outWriter.println(new ResponseHeader(200, htmlText.length(), "text/html"));

            outWriter.println(htmlText);
            outWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
