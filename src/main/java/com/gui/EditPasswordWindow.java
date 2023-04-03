package main.java.com.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.com.app.Password;
import main.java.com.app.PasswordDAO;

public class EditPasswordWindow extends JFrame {
    private PasswordDAO passwordDAO;
    private Password password;
    private String username;
    private JTextField websiteUrlField;
    private JTextField websiteNameField;
    private JTextField websiteUsernameField;
    private JTextField websitePasswordField;

    public EditPasswordWindow(PasswordDAO passwordDAO, Password password, String username) {
        this.passwordDAO = passwordDAO;
        this.password = password;
        this.username = username;
        initComponents();
        populateFields();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel fieldsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    
        websiteUrlField = new JTextField();
        websiteNameField = new JTextField();
        websiteUsernameField = new JTextField();
        websitePasswordField = new JTextField();
    
        fieldsPanel.add(new JLabel("Website URL:"));
        fieldsPanel.add(websiteUrlField);
    
        fieldsPanel.add(new JLabel("Website Name:"));
        fieldsPanel.add(websiteNameField);
    
        fieldsPanel.add(new JLabel("Website Username:"));
        fieldsPanel.add(websiteUsernameField);
    
        fieldsPanel.add(new JLabel("Website Password:"));
        fieldsPanel.add(websitePasswordField);
    
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    password.setWebsiteUrl(websiteUrlField.getText());
                    password.setWebsiteName(websiteNameField.getText());
                    password.setWebsiteUsername(websiteUsernameField.getText());
                    password.setWebsitePassword(websitePasswordField.getText());
        
                    passwordDAO.updateUserPassword(password);
        
                    JOptionPane.showMessageDialog(null, "Password updated successfully!");
        
                    ViewPasswordsWindow viewPasswordsWindow = new ViewPasswordsWindow(passwordDAO, username);
                    viewPasswordsWindow.setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating password!");
                }
            }
        });
    
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ViewPasswordsWindow viewPasswordsWindow = new ViewPasswordsWindow(passwordDAO, username);
                viewPasswordsWindow.setVisible(true);
                dispose();
            }
        });
    
        buttonsPanel.add(saveButton);
        buttonsPanel.add(cancelButton);
    
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        panel.add(fieldsPanel, BorderLayout.CENTER);
        panel.add(buttonsPanel, BorderLayout.SOUTH);
    
        add(panel);
        setTitle("Edit Password");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
    }
    

    private void populateFields() {
        websiteUrlField.setText(password.getWebsiteUrl());
        websiteNameField.setText(password.getWebsiteName());
        websiteUsernameField.setText(password.getWebsiteUsername());
        websitePasswordField.setText(password.getWebsitePassword());
    }
}
