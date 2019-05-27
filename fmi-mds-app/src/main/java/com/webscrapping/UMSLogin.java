package com.webscrapping;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UMSLogin {
    private JPasswordField password;
    private JTextField username;
    private JButton button1;
    private JTextArea UMSTextArea;
    private UMSConnectionDummy session;
    public UMSLogin() {


        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
    }
}
