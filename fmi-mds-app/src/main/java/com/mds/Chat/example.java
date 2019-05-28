package com.mds.Chat;

import java.io.IOException;
import java.util.Scanner;

public class example {
    public static void main(String[] args) throws IOException{
        final Client x;
        x = new Client ("Gigel", 142);

        Thread reading = new Thread(){
            @Override
            public void run() {
                while (true)
                {
                    System.out.println(x.reading());
                }
            }
        };

        Thread writting = new Thread(){
            @Override
            public void run() {
                while(true)
                {
                    Scanner s = new Scanner(System.in);
                    x.writting(s.nextLine());
                }
            }
        };

        reading.start();
        writting.start();
    }
}
