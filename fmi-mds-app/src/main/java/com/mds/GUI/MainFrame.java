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

    public MainFrame(JFrame mainFrame, CurrentUser currentUser)
    {
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
                frame.dispose();
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
