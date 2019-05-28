package com.mds;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MenuForm extends JFrame{
    private JPanel topPanel;


    public MenuForm(JFrame frame, CurrentUser currentUser)  throws SQLException, ClassNotFoundException {

    }

    public JPanel getTopPanel() {
        return topPanel;
    }
}
