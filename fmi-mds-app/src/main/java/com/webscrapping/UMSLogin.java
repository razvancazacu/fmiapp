package com.webscrapping;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;

public class UMSLogin extends JFrame{
    private JPasswordField password;
    private JTextField username;
    private JButton button1;
    private JTextArea UMSTextArea;
    private JPanel login;
    private UMSConnectionDummy session;
    public UMSLogin() {


        username.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (username.getText().trim().equals("username")) {
                    username.setText("");
                    username.setForeground(Color.black);
                }

                // showing that the textfield it's focused on
                Border usernameTextBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.yellow);
                username.setBorder(usernameTextBorder);
            }
        });
        password.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }
        });
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String user = username.getText();
                String pass = String.valueOf(password.getPassword());
                session = new UMSConnectionDummy(user, pass);
                String code = session.makeConnection();
                if (code.equals("Succes"))
                /**
                 * @Dumi
                 * Call for the succesful display of the information of grades
                 */
                    System.out.print("Succes");
                    session.display();
                else {
                    /**@Dumi
                     * Error displaying and noticed user
                     */
                    System.out.print("Failed");
                }
            }

        });



    }
    public static void main (String[]args){
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    JFrame frame = new JFrame("UMSLogin");
                    frame.setContentPane(new UMSLogin().login);
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