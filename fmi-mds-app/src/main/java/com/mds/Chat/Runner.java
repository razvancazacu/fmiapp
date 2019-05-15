package com.mds.Chat;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    public static Server s = null;
    private static int port = 9901;
    private static int loop = 1;
    private static ArrayList<Client> clientList = new ArrayList<>();

    public static void main(String[] args) {
        try {
            s = new Server(port);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                s.start();
            }
        });
        thread.start();


    }
}
