import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PasswordEditFrame extends JFrame implements ActionListener {
    JLabel urlLabel;
    JTextField urlText;
    JLabel websiteNameLabel;
    JTextField websiteNameText;
    JLabel webUsernameLabel;
    JTextField webUsernameText;
    JLabel webPasswordLabel;
    JPasswordField webPasswordText;
    JLabel PMUsernameLabel;
    JTextField PMUsernameText;
    JButton button;
    JLabel PMPasswordLabel;
    JPasswordField PMPasswordText;
    String website;
    String username;

    PasswordEditFrame(String URL, String website, String username, String password) {
        this.website = website;
        this.username = username;
        JPanel panel = new JPanel();
        panel.setLayout(null);

        this.add(panel);
        this.setSize(600, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Password Manager - Password editor");
        this.setVisible(true);

        urlLabel = new JLabel("Website URL: ");
        urlLabel.setBounds(10, 20, 120, 25);
        panel.add(urlLabel);

        urlText = new JTextField(20);
        urlText.setBounds(200, 20, 300, 25);
        urlText.setText(URL);
        panel.add(urlText);

        websiteNameLabel = new JLabel("Website Name: ");
        websiteNameLabel.setBounds(10, 50, 120, 25);
        panel.add(websiteNameLabel);

        websiteNameText = new JTextField(20);
        websiteNameText.setBounds(200, 50, 300, 25);
        websiteNameText.setText(website);
        panel.add(websiteNameText);

        webUsernameLabel = new JLabel("Website Username: ");
        webUsernameLabel.setBounds(10, 80, 120, 25);
        panel.add(webUsernameLabel);

        webUsernameText = new JTextField(20);
        webUsernameText.setBounds(200, 80, 300, 25);
        webUsernameText.setText(username);
        panel.add(webUsernameText);

        webPasswordLabel = new JLabel("Website Password: ");
        webPasswordLabel.setBounds(10, 110, 120, 25);
        panel.add(webPasswordLabel);

        webPasswordText = new JPasswordField();
        webPasswordText.setBounds(200, 110, 300, 25);
        webPasswordText.setText(password);
        panel.add(webPasswordText);

        button = new JButton("Save Edited Password");
        button.setBounds(10, 210, 140, 25);
        panel.add(button);
        panel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            String webUrl = urlText.getText();
            String webName = websiteNameText.getText();
            String webUName = webUsernameText.getText();
            char[] password = webPasswordText.getPassword();
            String webPassword = new String(password);
            SQLIntegration.updateSQL(webUrl, webName, webUName, webPassword, website, username);
        }

    }
}