package com.mds.GUI;

import com.mds.CurrentUser;

import javax.swing.*;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        MainFrame x = null;
        try {
            x = new MainFrame(new JFrame(), new CurrentUser("admin","admin"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        x.setVisible(true);
    }
}
