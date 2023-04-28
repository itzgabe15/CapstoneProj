package main.java.com.gui;

import javax.swing.*;

import main.java.com.app.Account;
import main.java.com.app.AccountDAO;
import main.java.com.app.PasswordDAO;

import java.awt.event.*;
import java.sql.SQLException;

public class LoginOrSignupWindow extends JFrame {
    private JButton loginButton, signupButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPanel panel;
    private AccountDAO accountDAO;
    private PasswordDAO passwordDAO;

    public LoginOrSignupWindow(AccountDAO accountDAO, PasswordDAO passwordDAO) {
        this.passwordDAO = passwordDAO;
        this.accountDAO = accountDAO;

        panel = new JPanel();
        loginButton = new JButton("Login");
        signupButton = new JButton("Sign up");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    Account account = accountDAO.getAccount(username);
                    if (account != null && account.getPassword().equals(password)) {
                        JOptionPane.showMessageDialog(null, "Login successful");

                        UserOptionsWindow userOptionsWindow = new UserOptionsWindow(passwordDAO, username);
                        userOptionsWindow.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error checking login: " + ex.getMessage());
                }
            }
        });

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignupWindow signupWindow = new SignupWindow(accountDAO);
                signupWindow.setVisible(true);

                dispose();
            }
        });

        panel.add(new JLabel("Username: "));
        panel.add(usernameField);
        panel.add(new JLabel("Password: "));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signupButton);
        add(panel);

        setTitle("Login or Signup");
        setSize(350, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
