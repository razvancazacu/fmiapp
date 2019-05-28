package com.mds.GUI;

import com.mds.CurrentUser;
import com.mds.DataBaseConnection.MyConnection;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginForm extends JFrame {
    private JTextField usernameTextField;
    private JButton LOGINButton;
    private JPanel topPanel;
    private JLabel loginLabel;
    private JPasswordField passwordField;

    public LoginForm(JFrame frame) {

        //creating a border for the panel and setting the border for tha top panel
        Border panelTopBorder = BorderFactory.createMatteBorder(0, 2, 2, 2, Color.darkGray);
        topPanel.setBorder(panelTopBorder);
        //for the login title

        usernameTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (usernameTextField.getText().trim().toLowerCase().equals("username")) {
                    usernameTextField.setText("");
                    usernameTextField.setForeground(Color.black);
                }

                // showing that the textfield it's focused on
                Border usernameTextBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.yellow);
                usernameTextField.setBorder(usernameTextBorder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                //checking if the username is empty or still 'username'
                // we'll set the text field to "username"
                if (usernameTextField.getText().trim().toLowerCase().equals("username")
                        || usernameTextField.getText().trim().toLowerCase().equals("")
                ) {
                    usernameTextField.setText("username");
                    usernameTextField.setForeground(new Color(153,153,153));
                }
                //removing the border
                usernameTextField.setBorder(null);
            }
        });
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                String pass = String.valueOf(passwordField.getPassword());
                if (pass.toLowerCase().equals("password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.black);
                }

                // showing that the textfield it's focused on
                Border passwordFieldBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.yellow);
                passwordField.setBorder(passwordFieldBorder);
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                //checking if the password is empty or still 'password'
                // we'll set the text field to "password"

                String pass = String.valueOf(passwordField.getPassword());

                if (pass.toLowerCase().equals("password")
                        || pass.toLowerCase().equals("")
                ) {
                    passwordField.setText("password");
                    passwordField.setForeground(new Color(153,153,153));
                }
                //removing the border
                passwordField.setBorder(null);
            }
        });
        LOGINButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                login(frame);
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login(frame);
                }
            }
        });
    }
    private void login(JFrame frameLogin){
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        // get the username and password
        String username = usernameTextField.getText();
        String password = String.valueOf(passwordField.getPassword());

        // creating a select query to see if the username already exists in the db

        String query = "SELECT * FROM `users` WHERE `username` = ? and `password` = ?";

        try {
            preparedStatement = MyConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                frameLogin.dispose();
                String acc_type = resultSet.getString("acc_type");
                CurrentUser currentUser = new CurrentUser(acc_type, username);
                currentUser.setGroup(resultSet.getInt("group"));
                JFrame frameMenu = new JFrame("Menu");
                try {
                    frameMenu.setContentPane(new MainFrame(frameMenu,currentUser).getRootPanel());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameMenu.pack();
                frameMenu.setVisible(true);
                frameMenu.setLocationRelativeTo(null);
            } else {
                // error
                JOptionPane.showMessageDialog(null,"Invalid Username / Password","Login Error",2);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame("LoginForm");
                    frame.setContentPane(new LoginForm(frame).topPanel);
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

