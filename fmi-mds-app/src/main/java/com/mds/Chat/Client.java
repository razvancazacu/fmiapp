package com.mds.Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;
    private static Scanner scanner;

    public Client(String username, int group) throws IOException {
        socket = new Socket("localhost", 8818);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(username);
        out.writeUTF("" + group);
    }

    public String reading()  {

        try {
            return in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading!\n";
        }

    }

    public void writting(String msg) {

            try {
                out.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}


