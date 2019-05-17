package com.mds.Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {

    private final ServerSocket serverSocket;
    private Socket clientSocket;
    private ArrayList<Socket> clientList = new ArrayList<>();

    public Server(int port) throws IOException {
            serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {

       while(true)
       {
           try {
               clientSocket = serverSocket.accept();
               clientList.add(clientSocket);
           } catch (IOException e) {
               e.printStackTrace();
           }
           Thread threadForHandlingConnections = new Thread() {
               @Override
               public void run() {
                   try {
                       handleConnection(clientSocket, clientList);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
               }
           };

           threadForHandlingConnections.start();
       }

    }

    private void handleConnection(Socket clientSocket, ArrayList<Socket> clientList) throws IOException{
        System.out.println("New user connected : " + clientSocket + "\n");

        DataInputStream in = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());


        while(true)
        {
            String msg = in.readUTF();
           for(int i = 0;i<clientList.size();i++) {
                Socket actualClient = clientList.get(i);
                DataOutputStream clientOut = new DataOutputStream(actualClient.getOutputStream());
                clientOut.writeUTF(msg);
            }

        }
    }
}
