package com.mds.GUI;


import com.webscrapping.Grades;
import com.webscrapping.UMSConnectionDummy;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class UMSLogin extends JFrame{
    private JButton button1;
    private JPanel loginPanel;
    private JLabel loginLabel;
    private JButton CANCELButton;
    private JTextField cnpFiled;
    private JTextField dobField;
    private JButton loginButton;
    private UMSConnectionDummy session;

    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public UMSLogin(JFrame frameUMSLogin,JTable sem1Table,JTable sem2Table,String option) {
        cnpFiled.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (cnpFiled.getText().trim().equals("cnp")) {
                    cnpFiled.setText("");
                    cnpFiled.setForeground(Color.black);
                }

                // showing that the textfield it's focused on
                Border usernameTextBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.yellow);
                cnpFiled.setBorder(usernameTextBorder);
            }
        });
        dobField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
            }
        });
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Integer opti = (Integer) Integer.parseInt(String.valueOf(option.charAt(option.length()-1))) -1;
                String user = cnpFiled.getText();
                String pass = dobField.getText();
                session = new UMSConnectionDummy(user, pass);
                String code = session.makeConnection(opti);
                if (code.equals("Succes")) {
                    DefaultTableModel defaultTableSem1 = new DefaultTableModel();
                    DefaultTableModel defaultTableSem2 = new DefaultTableModel();
                    defaultTableSem1.addColumn("Materie");
                    defaultTableSem2.addColumn("Materie");
                    defaultTableSem1.addColumn("Nota");
                    defaultTableSem2.addColumn("Nota");
                    defaultTableSem1.addColumn("Credite");
                    defaultTableSem2.addColumn("Credite");
                    ArrayList<Grades> userInformation = session.getGrades();
                    int i = 0;
                    for(Grades temp : userInformation){
                        if(i<=6)
                            defaultTableSem1.addRow(new String[]{userInformation.get(i).getCourse(),
                                                                userInformation.get(i).getFinalGrade(),
                                                                userInformation.get(i).getCredits()});
                        else{
                            defaultTableSem2.addRow(new String[]{userInformation.get(i).getCourse(),
                                    userInformation.get(i).getFinalGrade(),
                                    userInformation.get(i).getCredits()});
                        }
                            i++;
                    }
                    sem1Table.setModel(defaultTableSem1);
                    sem2Table.setModel(defaultTableSem2);
                }
                else {

                    System.out.print("Failed");
                }

            }

        });


        CANCELButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                frameUMSLogin.dispose();

            }
        });
    }

    }
