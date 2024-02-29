package org.multithreadedserver.request;

import java.net.Socket;

public abstract class RequestHandler extends Request implements Runnable {

    protected Socket socket;

    public RequestHandler(String path, Method method) {
        super(path, method);
    }

    abstract boolean canHandle(Request request);

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
