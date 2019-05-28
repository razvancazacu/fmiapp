package com.mds.Chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server extends Thread {

    private final ServerSocket serverSocket;
    private Socket clientSocket;
    private ArrayList<Socket> socketList = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<String> groupList = new ArrayList<>();

    public Server(int port) throws IOException {
            serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {

       while(!(serverSocket.isClosed()))
       {
           try {
               clientSocket = serverSocket.accept();

               socketList.add(clientSocket);
               DataInputStream in = new DataInputStream(clientSocket.getInputStream());
               nameList.add(in.readUTF());
               groupList.add(in.readUTF());


           } catch (IOException e) {
               e.printStackTrace();
           }
           Thread threadForHandlingConnections = new Thread() {
               @Override
               public void run() {
                   try {
                       handleConnection(clientSocket, socketList, groupList, nameList);
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
                       for (Socket client : socketList)
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

    private void handleConnection(Socket clientSocket, ArrayList<Socket> clientList, ArrayList<String> groupList, ArrayList<String> nameList) throws IOException{
        System.out.println("New user connected : " + nameList.get(nameList.size()-1) + "\n");

        DataInputStream in = new DataInputStream(clientSocket.getInputStream());
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());


        while(true)
        {
            String msg = in.readUTF();
            int curentIndex;
            curentIndex = getCurentIndex(clientSocket, clientList);

            if (msg.equalsIgnoreCase("exit"))
            {
                userDisconected(clientSocket, clientList);
                break;
            }
            else {
                String[] tokens = msg.split("->");
                switch (tokens[0])
                {
                    case "all":
                        if (tokens.length>1)
                        broadcastMessage(clientList, groupList, nameList, out, curentIndex, tokens);
                        else out.writeUTF("Incomplete Command");
                        break;

                        case "w":
                        if(tokens.length>2)
                            privateMessage(clientList, nameList, out, curentIndex, tokens);
                        else out.writeUTF("Incomplete Command");
                        break;

                        case "g":
                            if (tokens.length>1)
                                groupMessage(clientList, groupList, nameList, out, curentIndex, tokens);
                            else out.writeUTF("Incomplete Command");
                            break;
                    default: out.writeUTF("Unknown Command");
                }


            }
        }
        }

    private void groupMessage(ArrayList<Socket> clientList, ArrayList<String> groupList, ArrayList<String> nameList, DataOutputStream out, int curentIndex, String[] tokens) throws IOException {
        String fullMessage = new String();
        fullMessage=tokens[1];
        for (int i=2;i<tokens.length;i++)
            fullMessage+="->"+tokens[i];

        for(int i=0;i<clientList.size();i++)
        {
            if (groupList.get(i).equalsIgnoreCase(groupList.get(curentIndex)))
            {
                DataOutputStream dout = new DataOutputStream(clientList.get(i).getOutputStream());
                dout.writeUTF(nameList.get(curentIndex)+" in "+groupList.get(curentIndex) +": "+ fullMessage);
            }
        }


    }

    private void privateMessage(ArrayList<Socket> clientList, ArrayList<String> nameList, DataOutputStream out, int curentIndex, String[] tokens) throws IOException {
        String destinationName = tokens[1];
        int i;
        for(i=0;i<nameList.size();i++)
        {
            if(nameList.get(i).equalsIgnoreCase(destinationName))
                break;
        }

        if (i>nameList.size()-1)
            out.writeUTF("User Not Found!");
        else
        {
            String fullMessage = new String();
            fullMessage=tokens[2];
            for (int ii=3;ii<tokens.length;ii++)
                fullMessage+="->"+tokens[ii];

            DataOutputStream dout = new DataOutputStream(clientList.get(i).getOutputStream());
            dout.writeUTF("Private message from "+nameList.get(curentIndex)+": "+fullMessage);
            out.writeUTF("Private message to "+nameList.get(i)+": "+fullMessage);
        }
    }

    private void broadcastMessage(ArrayList<Socket> clientList, ArrayList<String> groupList, ArrayList<String> nameList, DataOutputStream out, int curentIndex, String[] tokens) throws IOException {
        String fullMessage = new String();
        fullMessage=tokens[1];
        for (int i=2;i<tokens.length;i++)
            fullMessage+="->"+tokens[i];

        for(int i=0;i<clientList.size();i++)
        {
            DataOutputStream dout = new DataOutputStream(clientList.get(i).getOutputStream());
            dout.writeUTF(nameList.get(curentIndex)+"/"+groupList.get(curentIndex)+": "+ fullMessage);
        }

    }



    private void userDisconected(Socket clientSocket, ArrayList<Socket> clientList) {
        int i;
        for (i = 0 ;i<clientList.size();i++)
            if(clientSocket==clientList.get(i))
                break;

            clientList.remove(i);
            nameList.remove(i);
            groupList.remove(i);
        System.out.println("User Disconnected!");
    }

    private int getCurentIndex(Socket clientSocket, ArrayList<Socket> clientList) {
        int curentIndex;
        for (curentIndex=0; curentIndex<clientList.size(); curentIndex++)
            if(clientList.get(curentIndex).getPort()==clientSocket.getPort())
                break;
        return curentIndex;
    }
}

