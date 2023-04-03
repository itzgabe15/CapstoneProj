package main.java.com.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.com.app.PasswordDAO;

public class SavePasswordWindow extends JFrame {
    private PasswordDAO passwordDAO;
    private String username;

    public SavePasswordWindow(PasswordDAO passwordDAO, String username) {
        this.passwordDAO = passwordDAO;
        this.username = username;
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        JLabel websiteUrlLabel = new JLabel("Website URL:");
        JTextField websiteUrlField = new JTextField();
        JLabel websiteNameLabel = new JLabel("Website Name:");
        JTextField websiteNameField = new JTextField();
        JLabel websiteUsernameLabel = new JLabel("Website Username:");
        JTextField websiteUsernameField = new JTextField();
        JLabel websitePasswordLabel = new JLabel("Website Password:");
        JTextField websitePasswordField = new JTextField();

        panel.add(websiteUrlLabel);
        panel.add(websiteUrlField);
        panel.add(websiteNameLabel);
        panel.add(websiteNameField);
        panel.add(websiteUsernameLabel);
        panel.add(websiteUsernameField);
        panel.add(websitePasswordLabel);
        panel.add(websitePasswordField);

        JButton saveButton = new JButton("Save Password");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String websiteUrl = websiteUrlField.getText();
                String websiteName = websiteNameField.getText();
                String websiteUsername = websiteUsernameField.getText();
                String websitePassword = websitePasswordField.getText();

                try {
                    passwordDAO.addUserPassword(websiteUrl, websiteName, websiteUsername, websitePassword, username);
                    JOptionPane.showMessageDialog(null, "Password saved successfully");

                    UserOptionsWindow userOptionsWindow = new UserOptionsWindow(passwordDAO, username);
                    userOptionsWindow.setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving password: " + ex.getMessage());
                }
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserOptionsWindow userOptionsWindow = new UserOptionsWindow(passwordDAO, username);
                userOptionsWindow.setVisible(true);
                dispose();
            }
        });

        panel.add(saveButton);
        panel.add(cancelButton);

        add(panel);
        setTitle("Save Password");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setSize(700, 600);
        setLocationRelativeTo(null);
    }
}
