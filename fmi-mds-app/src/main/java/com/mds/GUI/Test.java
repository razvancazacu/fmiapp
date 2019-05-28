package com.mds.GUI;

import com.mds.CurrentUser;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        MainFrame x = new MainFrame(new JFrame(), new CurrentUser("t","admin"));

        x.setVisible(true);
    }
}
