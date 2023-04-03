package main.java.com.gui;

import javax.swing.*;

import main.java.com.app.Account;
import main.java.com.app.AccountDAO;
import main.java.com.app.PasswordDAO;

import java.awt.event.*;
import java.sql.SQLException;

public class SignupWindow extends JFrame {
    private JButton signupButton;
    private JTextField usernameField, firstNameField, lastNameField;
    private JPasswordField passwordField;
    private JPanel panel;
    private AccountDAO accountDAO;
    private PasswordDAO passwordDAO;

    public SignupWindow(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;

        panel = new JPanel();
        signupButton = new JButton("Sign up");
        usernameField = new JTextField(20);
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();

                Account account = new Account(username, firstName, lastName, password);

                try {
                    if (accountDAO.getAccount(username) != null) {
                        JOptionPane.showMessageDialog(null, "An account with that username already exists");
                    } else {
                        accountDAO.addAccount(account);
                        JOptionPane.showMessageDialog(null, "Account created successfully");
                        LoginOrSignupWindow signupWindow = new LoginOrSignupWindow(accountDAO, passwordDAO);
                
                        dispose();

                        signupWindow.setVisible(true);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error creating account: " + ex.getMessage());
                }
            }
        });

        panel.add(new JLabel("Username: "));
        panel.add(usernameField);
        panel.add(new JLabel("First Name: "));
        panel.add(firstNameField);
        panel.add(new JLabel("Last Name: "));
        panel.add(lastNameField);
        panel.add(new JLabel("Password: "));
        panel.add(passwordField);
        panel.add(signupButton);
        add(panel);

        setTitle("Sign Up");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
