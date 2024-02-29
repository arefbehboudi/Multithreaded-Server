package org.multithreadedserver.request;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RequestRouteHandler {

    private final List<RequestHandler> handlers = new ArrayList<>();

    public RequestRouteHandler() {
        handlers.add(new TestHandler("/", Method.GET));
    }

    public RequestHandler handle(RequestParser requestParser, Socket socket) throws IOException {
        Request request = requestParser.parseRequest(socket.getInputStream());

        for (RequestHandler handler : handlers) {
            if(handler.canHandle(request)) {
                handler.setSocket(socket);
                return handler;
            }

        }

        throw new IllegalArgumentException("Not found.");
    }
}
