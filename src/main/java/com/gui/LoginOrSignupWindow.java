package main.java.com.gui;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import main.java.com.example.Account;
import main.java.com.example.AccountDAO;
import main.java.com.example.PasswordDAO;


public class LoginOrSignupWindow extends JFrame{
    private JButton loginButton, signupButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPanel panel;
    private AccountDAO accountDAO;
    private PasswordDAO passwordDAO;

    public LoginOrSignupWindow(AccountDAO accountDAO, PasswordDAO passwordDAO){
        this.passwordDAO = passwordDAO;
        this.accountDAO = accountDAO;

        panel = new JPanel();
        loginButton = new JButton("Login");
        signupButton = new JButton("Sign up");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // get the entered username and password
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // check if the username and password are valid
                try {
                    Account account = accountDAO.getAccount(username);
                    if (account != null && account.getPassword().equals(password)) {
                        JOptionPane.showMessageDialog(null, "Login successful");

                        // Show the user options window
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
        
        signupButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                // Create a new signup window and make it visible
                SignupWindow signupWindow = new SignupWindow(accountDAO);
                signupWindow.setVisible(true);
                
                // Close the current window
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
