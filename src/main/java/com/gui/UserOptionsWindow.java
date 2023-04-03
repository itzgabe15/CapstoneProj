package main.java.com.gui;

import javax.swing.*;

import main.java.com.app.PasswordDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserOptionsWindow extends JFrame{
    private JButton savePasswordButton, viewPasswordsButton;
    private String username;
    private PasswordDAO passwordDAO;

    public UserOptionsWindow(PasswordDAO passwordDAO, String username) {
        this.passwordDAO = passwordDAO;
        this.username = username;

        setTitle("Password Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        savePasswordButton = new JButton("Save Password");
        viewPasswordsButton = new JButton("View Saved Passwords");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(savePasswordButton);
        buttonPanel.add(viewPasswordsButton);
        add(buttonPanel);

        savePasswordButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                SavePasswordWindow savePasswordWindow = new SavePasswordWindow(passwordDAO, username);
                savePasswordWindow.setVisible(true);
                dispose();
            }
        });
        viewPasswordsButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ViewPasswordsWindow viewPasswordsWindow = new ViewPasswordsWindow(passwordDAO, username);
                viewPasswordsWindow.setVisible(true);
                dispose();
            }
        });

        setVisible(true);
    }

}
