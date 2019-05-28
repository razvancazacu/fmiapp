package com.mds.GUI;


import com.webscrapping.Grades;
import com.webscrapping.UMSConnectionDummy;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class UMSLogin extends JFrame{
    private JButton button1;
    private JPanel loginPanel;
    private JLabel loginLabel;
    private JButton CANCELButton;
    private JTextField cnpFiled;
    private JTextField dobField;
    private JButton loginButton;
    private UMSConnectionDummy session;

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public UMSLogin(JFrame frameUMSLogin) {
        cnpFiled.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (cnpFiled.getText().trim().equals("cnp")) {
                    cnpFiled.setText("");
                    cnpFiled.setForeground(Color.black);
                }

                // showing that the textfield it's focused on
                Border usernameTextBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.yellow);
                cnpFiled.setBorder(usernameTextBorder);
            }
        });
        dobField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }
        });
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String user = cnpFiled.getText();
                String pass = dobField.getText();
                session = new UMSConnectionDummy(user, pass);
                String code = session.makeConnection();
                if (code.equals("Succes")) {

                    ArrayList<Grades> userInformation = session.getGrades();
                    for(Grades temp : userInformation){
                        temp.display();
                    }
                }
                else {

                    System.out.print("Failed");
                }
            }

        });


        CANCELButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frameUMSLogin.dispose();
            }
        });
    }
    public static void main (String[]args){
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame("UMSLogin");
                    frame.setContentPane(new UMSLogin(frame).loginPanel);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}