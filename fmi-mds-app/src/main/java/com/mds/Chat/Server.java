package com.mds.Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server extends Thread {

    private final ServerSocket serverSocket;
    private Socket clientSocket;
    private ArrayList<Socket> clientList = new ArrayList<>();

    public Server(int port) throws IOException {
            serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {

       while(!(serverSocket.isClosed()))
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

           //Thread for closing the server
           Thread closeTheServer = new Thread (){
               @Override
               public void run() {
                   String msg = new String();
                   Scanner scanner = new Scanner(System.in);
                   msg = scanner.nextLine();
                   if (msg.equalsIgnoreCase("exit"))
                   {
                       for (Socket client : clientList)
                       {
                           try {
                               client.close();
                           } catch (IOException e) {
                               e.printStackTrace();
                           }
                       }
                       try {
                           threadForHandlingConnections.interrupt();
                           serverSocket.close();

                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
               }
           };
           closeTheServer.start();
       }

    }

    private void handleConnection(Socket clientSocket, ArrayList<Socket> clientList) throws IOException{
        System.out.println("New user connected : " + clientSocket + "\n");

        DataInputStream in = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());


        while(true)
        {
            String msg = in.readUTF();
            if (msg.equalsIgnoreCase("exit"))
            {
                clientList.remove(clientSocket);
                System.out.println("User Disconnected!");
                break;

            }
            else {
                String[] tokens = msg.split("/");
                if((tokens.length==5) && (tokens[2].matches("pm")))
                {
                    int index;
                    int destinationPort = Integer.parseInt(tokens[3]);
                    for (index = 0; index<clientList.size();index++)
                    {
                        if(clientList.get(index).getPort() == destinationPort)
                            break;
                    }
                    DataOutputStream pmout = new DataOutputStream(clientList.get(index).getOutputStream());
                    out.writeUTF("Private message from port" + clientSocket.getPort() + ": "+ tokens[4]);
                    pmout.writeUTF("Private message from port" + clientSocket.getPort() + ": "+ tokens[4]);
                }
                else {

                    for(int i = 0;i<clientList.size();i++) {
                        Socket actualClient = clientList.get(i);
                        DataOutputStream clientOut = new DataOutputStream(actualClient.getOutputStream());
                        clientOut.writeUTF(msg);
                    }
                }
            }



        }
    }
}
