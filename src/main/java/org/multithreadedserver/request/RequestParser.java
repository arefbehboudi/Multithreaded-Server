package org.multithreadedserver.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RequestParser {

    public Request parseRequest(InputStream inputStream) throws IOException {
        System.out.println("************************");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String firstLine = reader.readLine();
        Method method = Method.valueOf(getMethod(firstLine));
        String path = getPath(firstLine);

        return new Request(path, method);
    }

    private String getPath(String line) {
        return line.split(" ")[1].split("\\?")[0];
    }

    private String getMethod(String line) {
        return line.split(" ")[0];
    }
}
