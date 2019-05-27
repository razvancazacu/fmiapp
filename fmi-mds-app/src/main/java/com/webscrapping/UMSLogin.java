package com.webscrapping;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class UMSLogin {
    private JPasswordField password;
    private JTextField username;
    private JButton button1;
    private JTextArea UMSTextArea;

    public UMSLogin() {
        username.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {

            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }
        });
    }
}
