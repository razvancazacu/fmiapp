package com.mds.Chat;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            String msg = scanner.nextLine();

            String[] tokens = msg.split("-");
            System.out.println(tokens[0]+ " "+tokens[1]+ " "+tokens[2]+ " ");
        }
    }
}
