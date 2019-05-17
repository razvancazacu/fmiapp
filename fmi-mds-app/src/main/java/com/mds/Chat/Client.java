package com.mds.Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",8818);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);


           Thread reading = new Thread (){
               @Override
               public void run() {
                   while(true)
                   {
                       try {
                           System.out.println(in.readUTF());
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }

               }
           };

            Thread writting = new Thread() {
                @Override
                public void run() {
                   while(true)
                   {
                       String msg = scanner.nextLine();
                       try {
                           out.writeUTF(socket + ": "+  msg);

                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
                }
            };
            reading.start();
            writting.start();

    }
}
