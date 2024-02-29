package org.multithreadedserver.request;

import java.net.Socket;

public class Request {

    private String path;

    private Method method;

    private Socket socket;

    public Request(String path, Method method) {
        this.path = path;
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
