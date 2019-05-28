package com.mds;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PasswordController {
    private JPanel MainPanel;
    private JPanel topPanel;
    private JLabel loginLabel;
    private JTextField passwordTextField;
    private JButton ConfirmButton;
    private JButton CANCELButton;
    private JPasswordField passwordConfirmField;

    public PasswordController() {
        passwordTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (passwordTextField.getText().trim().toLowerCase().equals("password")) {
                    passwordTextField.setText("");
                    passwordTextField.setForeground(Color.black);
                }

                // showing that the textfield it's focused on
                Border usernameTextBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.yellow);
                passwordTextField.setBorder(usernameTextBorder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                //checking if the username is empty or still 'username'
                // we'll set the text field to "username"
                if (passwordTextField.getText().trim().toLowerCase().equals("password")
                        || passwordTextField.getText().trim().toLowerCase().equals("")
                ) {
                    passwordTextField.setText("password");
                    passwordTextField.setForeground(new Color(153,153,153));
                }
                //removing the border
                passwordTextField.setBorder(null);
            }
        });
        passwordConfirmField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                String pass = String.valueOf(passwordConfirmField.getPassword());
                if (pass.toLowerCase().equals("password")) {
                    passwordConfirmField.setText("");
                    passwordConfirmField.setForeground(Color.black);
                }

                // showing that the textfield it's focused on
                Border passwordFieldBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.yellow);
                passwordConfirmField.setBorder(passwordFieldBorder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                //checking if the password is empty or still 'password'
                // we'll set the text field to "password"

                String pass = String.valueOf(passwordConfirmField.getPassword());

                if (pass.toLowerCase().equals("password")
                        || pass.toLowerCase().equals("")
                ) {
                    passwordConfirmField.setText("password");
                    passwordConfirmField.setForeground(new Color(153,153,153));
                }
                //removing the border
                passwordConfirmField.setBorder(null);
            }
        });
    }
}
