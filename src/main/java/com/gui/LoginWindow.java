package main.java.com.gui;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginForm() {
        setTitle("Login Form");
        setModal(true);

        // create the username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        // create the password label and password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        // create the login button
        loginButton = new JButton("Login");

        // create the layout for the login form
        setLayout(new GridLayout(3, 2, 5, 5));

        // add the username label and text field
        add(usernameLabel);
        add(usernameField);

        // add the password label and password field
        add(passwordLabel);
        add(passwordField);

        // add the login button
        add(new JLabel());
        add(loginButton);

        // set the size and location of the login form
        setSize(300, 150);
        setLocationRelativeTo(null);
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void setLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
}
