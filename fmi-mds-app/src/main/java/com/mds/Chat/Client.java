package com.mds.Chat;


import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    Socket s;

    public Client(int port ) {
        try {
            s=new Socket("localhost",port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client c = new Client(9901);
        while(true)
        {

        }
    }



}
