package com.mds.Chat;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public ServerSocket getServerInstance() {
        return ss;
    }

    private ServerSocket ss;
    private boolean running;
    private ArrayList<Socket> clientList = new ArrayList<Socket>();

    public Server(int port) throws IOException {
        ss=new ServerSocket(port);
        System.out.println("Server started on port " + port + "!");
        running = true;
    }

    public void start()
    {
        System.out.println("Waiting for clients ");
        while(running)
        {
            Socket newClient = null;
            try {
                newClient = ss.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            clientList.add(newClient);
            System.out.println("New client! Client nr: "+clientList.size());
        }
    }


    public void stop()
    {
        running = false;
        System.out.println("Server Closed!");
    }

}


