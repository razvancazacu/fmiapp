package com.mds.Chat;

import java.io.IOException;


public class ServerRunner {
    public static void main(String[] args) throws IOException {
        int port = 8818;
        Server server;
        server = new Server(port);
        server.start();


        }
    }

