package org.multithreadedserver;

import org.multithreadedserver.request.RequestHandler;
import org.multithreadedserver.request.RequestParser;
import org.multithreadedserver.request.RequestRouteHandler;
import org.multithreadedserver.server.ServerState;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WebServer {
    private final Logger logger = Logger.getLogger(WebServer.class.getName());
    private final PrintStream stdOut;
    private final ServerSocket serverSocket;
    private final RequestParser requestParser;
    private final RequestRouteHandler requestRouter;
    private final Executor executor;

    private ServerState serverState;
    private int port;

    public WebServer(int port) throws IOException {
        if (port < 0 || port > 65535)
            throw new IllegalArgumentException("Invalid port number!");

        this.stdOut = System.out;
        this.port = port;
        this.serverSocket = new ServerSocket(this.port);
        this.requestParser = new RequestParser();
        this.requestRouter = new RequestRouteHandler();
        this.executor = Executors.newFixedThreadPool(10);
    }

    public void start() throws IOException {
        logger.info("Server running on port: ".concat(String.valueOf(port)));
        serverState = ServerState.RUNNING;
        while (serverState == ServerState.RUNNING) {
            Socket socket = serverSocket.accept();
            try {
                RequestHandler handler = requestRouter.handle(requestParser, socket);
                executor.execute(handler);
            } catch (Exception e) {
                this.logger.log(Level.WARNING, "Error in handle request: ", e);
            }
        }
    }

    public void stop() throws IOException {
        serverState = ServerState.STOP;
        serverSocket.close();
    }

}
