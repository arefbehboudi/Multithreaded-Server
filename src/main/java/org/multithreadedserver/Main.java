package org.multithreadedserver;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Thread: " + Thread.currentThread().getName());
            new WebServer(9090).start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}