package com.mds.GUI;

import com.mds.CurrentUser;
import com.mds.DataBaseConnection.MyConnection;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddAccountForm {
    private JTextField usernameField;
    private JPanel topPanel;
    private JTextField passwordField;
    private JTextField emailField;
    private JButton confirmAddingButton;
    private JComboBox accountTypeCombo;

    public AddAccountForm(JFrame frame, CurrentUser currentUser) {
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
                    usernameField.setForeground(new Color(153, 153, 153));
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
                    passwordField.setForeground(new Color(153, 153, 153));
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
                    emailField.setForeground(new Color(153, 153, 153));
                }
                //removing the border
                emailField.setBorder(null);
            }
        });
        confirmAddingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                addAccount(frame, currentUser);
            }

            private void addAccount(JFrame frame, CurrentUser currentUser) {
                PreparedStatement preparedStatement;

                String username = usernameField.getText();
                String password = passwordField.getText();
                String acc_type = accountTypeCombo.getSelectedItem().toString();
                String email = emailField.getText();
                if (username.isEmpty() || password.isEmpty() || acc_type.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Invalid", "Invalid", 2);
                } else {
                    String query = "INSERT INTO `users`\n" +
                            "(`username`,\n" +
                            "`password`,\n" +
                            "`acc_type`,\n" +
                            "`email`)\n" +
                            "VALUES\n" +
                            "(?,\n" +
                            "?,\n" +
                            "?,\n" +
                            "?);\n";

                    try {
                        preparedStatement = MyConnection.getConnection().prepareStatement(query);
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, password);
                        preparedStatement.setString(3, acc_type);
                        preparedStatement.setString(4, email);

                        preparedStatement.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Success", "Success", 2);
                        frame.dispose();

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error", "Error", 2);
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public JPanel getTopPanel() {
        return topPanel;
    }
}
