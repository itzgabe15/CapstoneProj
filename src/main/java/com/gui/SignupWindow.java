package main.java.com.gui;

import javax.swing.*;

import main.java.com.app.Account;
import main.java.com.app.AccountDAO;
import main.java.com.app.AccountSecretQuestionDAO;
import main.java.com.app.PasswordDAO;

import java.awt.event.*;
import java.sql.SQLException;

public class SignupWindow extends JFrame {
    private JButton signupButton;
    private JTextField usernameField, firstNameField, lastNameField, secretQuestionField, secretAnswerField;
    private JPasswordField passwordField;
    private JPanel panel;
    private AccountDAO accountDAO;
    private PasswordDAO passwordDAO;
    private AccountSecretQuestionDAO accountSecretQuestionDAO;

    public SignupWindow(AccountDAO accountDAO, PasswordDAO passwordDAO, AccountSecretQuestionDAO accountSecretQuestionDAO) {
        this.accountDAO = accountDAO;
        this.passwordDAO = passwordDAO;
        this.accountSecretQuestionDAO = accountSecretQuestionDAO;

        panel = new JPanel();
        signupButton = new JButton("Sign up");
        usernameField = new JTextField(20);
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        secretQuestionField = new JTextField(20);
        secretAnswerField = new JTextField(20);

        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String secretQuestion = secretQuestionField.getText();
                String secretAnswer = secretAnswerField.getText();

                Account account = new Account(username, firstName, lastName, password, secretQuestion);

                try {
                    if (accountDAO.getAccount(username) != null) {
                        JOptionPane.showMessageDialog(null, "An account with that username already exists");
                    } else {
                        accountDAO.addAccount(account);
                        account = accountDAO.getAccount(username);
                        accountSecretQuestionDAO.addSecretQuestion(account, secretQuestion, secretAnswer);
                        JOptionPane.showMessageDialog(null, "Account created successfully");
                        LoginOrSignupWindow signupWindow = new LoginOrSignupWindow(accountDAO, passwordDAO, accountSecretQuestionDAO);
                
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
        panel.add(new JLabel("Secret Question: "));
        panel.add(secretQuestionField);
        panel.add(new JLabel("Secrete Answer: "));
        panel.add(secretAnswerField);
        panel.add(signupButton);
        add(panel);

        setTitle("Sign Up");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}