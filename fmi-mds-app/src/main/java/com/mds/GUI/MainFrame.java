package com.mds.GUI;

import com.mds.CurrentUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;
    private JButton changePasswordButton;

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
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
