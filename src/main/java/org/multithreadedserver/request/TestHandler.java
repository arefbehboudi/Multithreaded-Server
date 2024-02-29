package org.multithreadedserver.request;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class TestHandler extends RequestHandler {


    public TestHandler(String path, Method method) {
        super(path, method);
    }

    @Override
    public void run() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write("HTTP/1.1 200 OK\r\n");
            bufferedWriter.write("ContentType: text/html\r\n");
            bufferedWriter.write("\r\n");
            bufferedWriter.write("Test");
            bufferedWriter.write("\r\n\r\n");
            bufferedWriter.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean canHandle(Request request) {
        return this.getPath().equals(request.getPath());
    }
}
