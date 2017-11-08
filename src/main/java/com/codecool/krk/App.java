package com.codecool.krk;

import com.codecool.krk.controller.GuestbookController;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;


public class App 
{
    public static void main( String[] args )
    {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/guestbook", new GuestbookController());

            server.setExecutor(null); // creates a default executor
            // start listening
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
