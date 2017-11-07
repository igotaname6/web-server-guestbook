package com.codecool.krk;

import com.codecool.krk.controller.GuestbookController;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/guestbook", new GuestbookController());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
