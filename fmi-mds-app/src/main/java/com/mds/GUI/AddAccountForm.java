package com.mds.GUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddAccountForm {
    private JTextField usernameField;
    private JPanel panel1;
    private JTextField passwordField;
    private JTextField emailField;
    private JButton confirmAddingButton;

    public AddAccountForm() {
        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (usernameField.getText().trim().toLowerCase().equals("username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.black);
                }

                // showing that the textfield it's focused on
                Border usernameTextBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.yellow);
                usernameField.setBorder(usernameTextBorder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                //checking if the username is empty or still 'username'
                // we'll set the text field to "username"
                if (usernameField.getText().trim().toLowerCase().equals("username")
                        || usernameField.getText().trim().toLowerCase().equals("")
                ) {
                    usernameField.setText("username");
                    usernameField.setForeground(new Color(153,153,153));
                }
                //removing the border
                usernameField.setBorder(null);
            }
        });
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (passwordField.getText().trim().toLowerCase().equals("password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.black);
                }

                // showing that the textfield it's focused on
                Border passTextBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.yellow);
                passwordField.setBorder(passTextBorder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                //checking if the username is empty or still 'username'
                // we'll set the text field to "username"
                if (passwordField.getText().trim().toLowerCase().equals("password")
                        || passwordField.getText().trim().toLowerCase().equals("")
                ) {
                    passwordField.setText("password");
                    passwordField.setForeground(new Color(153,153,153));
                }
                //removing the border
                passwordField.setBorder(null);
            }
        });
        emailField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (emailField.getText().trim().toLowerCase().equals("email")) {
                    emailField.setText("");
                    emailField.setForeground(Color.black);
                }

                // showing that the textfield it's focused on
                Border usernameTextBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.yellow);
                emailField.setBorder(usernameTextBorder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                //checking if the username is empty or still 'username'
                // we'll set the text field to "username"
                if (emailField.getText().trim().toLowerCase().equals("email")
                        || emailField.getText().trim().toLowerCase().equals("")
                ) {
                    emailField.setText("email");
                    emailField.setForeground(new Color(153,153,153));
                }
                //removing the border
                emailField.setBorder(null);
            }
        });
        confirmAddingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }
}
