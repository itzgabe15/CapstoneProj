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

        // Set up the window
        setTitle("Password Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        // Create the buttons
        savePasswordButton = new JButton("Save Password");
        viewPasswordsButton = new JButton("View Saved Passwords");

        // Add the buttons to the window
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 5, 5)); // Add spacing between buttons
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the buttons
        buttonPanel.add(savePasswordButton);
        buttonPanel.add(viewPasswordsButton);
        add(buttonPanel);

        // Add action listeners to the buttons
        savePasswordButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // Show the save password window
                SavePasswordWindow savePasswordWindow = new SavePasswordWindow(passwordDAO, username);
                savePasswordWindow.setVisible(true);
                dispose();
            }
        });
        viewPasswordsButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // Show the view saved passwords window
                ViewPasswordsWindow viewPasswordsWindow = new ViewPasswordsWindow(passwordDAO, username);
                viewPasswordsWindow.setVisible(true);
                dispose();
            }
        });

        // Show the window
        setVisible(true);
    }

}
