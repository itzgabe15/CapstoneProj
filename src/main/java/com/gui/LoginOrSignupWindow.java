package main.java.com.gui;

import javax.swing.*;
import java.awt.event.*;

public class LoginOrSignupWindow extends JFrame{
    private JButton loginButton, signupButton;
    private JPanel panel;
    public LoginOrSignupWindow(){
        panel = new JPanel();
        loginButton = new JButton("Login");
        signupButton = new JButton("Sign up");
        
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // code to handle login button click
            }
        });
        
        signupButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // code to handle signup button click
            }
        });
        
        panel.add(loginButton);
        panel.add(signupButton);
        add(panel);
        
        setTitle("Login or Signup");
        setSize(300, 100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}