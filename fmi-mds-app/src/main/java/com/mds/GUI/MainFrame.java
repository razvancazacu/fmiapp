package com.mds.GUI;

import com.mds.CurrentUser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JButton changePasswordButton;
    private JComboBox comboBox1;
    private JTable sem1Table;
    private JPanel sem2Panel;
    private JTable sem2Table;
    private JButton addAccount;
    private JPanel addAccountPanel;

    public MainFrame(JFrame mainFrame, CurrentUser currentUser) {
        if (!currentUser.getAcc_type().equals("admin")) {
            addAccountPanel.setVisible(false);
        }
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
                    frameChange.setContentPane(new PasswordController(frameChange, currentUser).getMainPanel());
                    frameChange.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frameChange.pack();
                    frameChange.setVisible(true);
                    frameChange.setLocationRelativeTo(null);
                }
            });
            comboBox1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

//                    JFrame frame = new JFrame("UMSLogin");
//                    frame.setContentPane(new UMSLogin(frame).getLoginPanel());
//                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                    frame.pack();
//                    frame.setVisible(true);
//                    frame.setLocationRelativeTo(null);


                    DefaultTableModel defaultTableModel = new DefaultTableModel();
                    defaultTableModel.addColumn("First Name");
                    defaultTableModel.addColumn("Last Name");

                    defaultTableModel.addRow(new Object[]{"alakazam", "pikapika"});

                    sem1Table.setModel(defaultTableModel);
                }
            });
        addAccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (currentUser.getAcc_type().equals("admin")) {
                    JFrame frameChange = new JFrame("Add User");
                    frameChange.setContentPane(new AddAccountForm(frameChange, currentUser).getTopPanel());
                    frameChange.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frameChange.pack();
                    frameChange.setVisible(true);
                    frameChange.setLocationRelativeTo(null);
                }
            }
        });
    }
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
