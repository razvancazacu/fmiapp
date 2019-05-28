package com.mds.GUI;

import com.mds.CurrentUser;
import com.mds.DataBaseConnection.MyConnection;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PasswordController {
    private JPanel MainPanel;
    private JPanel topPanel;
    private JLabel loginLabel;
    private JTextField passwordTextField;
    private JButton ConfirmButton;
    private JButton CANCELButton;
    private JPasswordField passwordConfirmField;

    public PasswordController(JFrame passFrame, CurrentUser currentUser) {
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
                if (passwordTextField.getText().trim().toLowerCase().equals("password")
                        || passwordTextField.getText().trim().toLowerCase().equals("")
                ) {
                    passwordTextField.setText("password");
                    passwordTextField.setForeground(new Color(153, 153, 153));
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
                    passwordConfirmField.setForeground(new Color(153, 153, 153));
                }
                //removing the border
                passwordConfirmField.setBorder(null);
            }
        });
        CANCELButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                passFrame.dispose();
            }
        });
        ConfirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        ConfirmButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                changePassword(passFrame, currentUser);
            }
        });
        passwordConfirmField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    changePassword(passFrame, currentUser);
                }
            }
        });
    }

    private void changePassword(JFrame framePass, CurrentUser currentUser) {
        PreparedStatement preparedStatement;

        // get the password and confirm password
        String password = passwordTextField.getText();
        String passwordConfirm = String.valueOf(passwordConfirmField.getPassword());

        if (password.equals(passwordConfirm) && !password.equals("password")) {

            String query = "UPDATE `users`\n" +
                    "SET\n" +
                    "`password` = ?\n" +
                    "WHERE `username` = ?;\n";
            try {
                preparedStatement = MyConnection.getConnection().prepareStatement(query);
                preparedStatement.setString(1, password);
//                preparedStatement.setString(2, "admin");
                preparedStatement.setString(2, currentUser.getUsername());

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Change succeeded", "Success", 2);
                framePass.dispose();
            } catch (
                    SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Passwords do not match", "Change Error", 2);
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PasswordController");
        CurrentUser currentUser = new CurrentUser("admin","admin");
        frame.setContentPane(new PasswordController( frame,  currentUser).MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
