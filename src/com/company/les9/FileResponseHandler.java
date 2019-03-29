package com.company.les9;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.company.les9.Connection.filesContainer;

public class FileResponseHandler implements ResponseHandler {
    @Override
    public void handle(String path, Socket client) {
        boolean found = false;
        File[] files = filesContainer.listFiles();
        if (null != files) {
            for (File file : files) {
                if (file.getName().equals(path.substring(1))) {
                    //System.out.println("found it : " + file.getName());
                    found = true;
                    break;
                }
            }
        }
        OutputStream out = null;
        try {
            out = client.getOutputStream();
            if (found) {
                String filePath = filesContainer + path;
                Path anotherPath = new File(filePath).toPath();

                File myFile = new File(filePath);
                if (myFile.isFile()) {

                    String mimeType = Files.probeContentType(anotherPath);
                    System.out.println("mimeType : " + mimeType);

                    String httpHeader = new ResponseHeader(200, myFile.length(), mimeType).toString();
                    out.write(httpHeader.getBytes(Charset.forName("UTF-8")));
                    sendFile(myFile, out);
                    out.close();
                }

            } else {
                PrintWriter outWriter = new PrintWriter(out);
                outWriter.print(new ResponseHeader(404, 33, "text/html"));
                outWriter.print("<html><body>Page Not found</body></html>");
                outWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (null != out) {
                PrintWriter outWriter = new PrintWriter(out);
                outWriter.print(new ResponseHeader(500, 22, "text/plain"));
                outWriter.print("Internal Server Error");
                outWriter.close();
            }
        }
    }

    public void sendFile(File in, OutputStream out){
        try {
            InputStream fis = new BufferedInputStream(new FileInputStream(in));

            int bufSize = 1024 * 1024;
            byte[] buffer = new byte[bufSize];
            int len = 0;
            while ((len = fis.read(buffer)) != 0) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

