package com.mds.GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;

    public MainFrame()
    {
        add(rootPanel);
        setTitle("Fmi APP");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension minDimension = new Dimension(750, 500);
        Dimension maxDimension = new Dimension(1800, 800);
        setMinimumSize(minDimension);
        setMaximumSize(maxDimension);
    }
}
