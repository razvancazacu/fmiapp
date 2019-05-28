package com.mds.GUI;

import com.mds.Chat.Client;
import com.mds.CurrentUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JButton changePasswordButton;
    private JComboBox comboBox1;
    private JTable sem1Table;
    private JPanel sem2Panel;
    private JTable sem2Table;
    private JTextArea globalChat;
    private JButton sendButtonGLC;
    private JTextField inputTextFieldGLC;
    private JTextArea groupChat;
    private JButton sendButtonGRC;
    private JTextField inputTextFieldGRC;

    public MainFrame(JFrame mainFrame, CurrentUser currentUser) throws IOException
    {

        //test
        final Client currentClient;
        currentClient = new Client(currentUser.getUsername(),242);

        Thread reading = new Thread(){
            @Override
            public void run() {
                while (true)
                {
                    String helper = currentClient.reading();

                    String[] tokens = helper.split("~");
                    switch (tokens[1]) {
                    case "all":
                        globalChat.setText(globalChat.getText()+tokens[0]+tokens[2]+"\n");
                        break;
                    case "g":
                        groupChat.setText(groupChat.getText()+tokens[0]+tokens[2]+"\n");
                        break;
                    case "w":
                        //to do
                        }
                }
            }
        };





        reading.start();

        //
        add(rootPanel);
        setTitle("Fmi APP");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension minDimension = new Dimension(750, 500);
        Dimension maxDimension = new Dimension(1800, 800);
        setMinimumSize(minDimension);
        setMaximumSize(maxDimension);
        changePasswordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFrame frameChange = new JFrame("Change Password");
                frameChange.setContentPane(new PasswordController(frameChange,currentUser).getMainPanel());
                frameChange.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frameChange.pack();
                frameChange.setVisible(true);
                frameChange.setLocationRelativeTo(null);
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String option = comboBox1.getSelectedItem().toString();
                JFrame frame = new JFrame("UMSLogin");
                frame.setContentPane(new UMSLogin(frame,sem1Table,sem2Table,option).getLoginPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);

            }
        });
        sendButtonGLC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentClient.writting("all->"+ inputTextFieldGLC.getText());
                inputTextFieldGLC.setText("");
            }
        });
        inputTextFieldGLC.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    currentClient.writting("all->"+ inputTextFieldGLC.getText());
                    inputTextFieldGLC.setText("");
                }
            }
        });
        sendButtonGRC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentClient.writting("g->"+ inputTextFieldGRC.getText());
                inputTextFieldGRC.setText("");
            }
        });

        inputTextFieldGRC.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    currentClient.writting("g->"+ inputTextFieldGRC.getText());
                    inputTextFieldGRC.setText("");
                }
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
