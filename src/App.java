import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App {
    public String webURL, webName, webUName, webUPassword, PMUsername, PMPassword;
    public static void main(String[] args) {
        new App().start();
    }

    public void start() {
        PMFrame PMwindow = new PMFrame();
        PMwindow.button.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==PMwindow.button) {
                    webURL = PMwindow.urlText.getText();
                    webName = PMwindow.websiteNameText.getText();
                    webUName = PMwindow.webUsernameText.getText();
                    webUPassword = PMwindow.webPasswordText.getText();
                    PMUsername = PMwindow.PMUsernameText.getText();
                    PMPassword = PMwindow.PMPasswordText.getText();
                    SQLIntegration sqlIntegration = new SQLIntegration();
                    sqlIntegration.executeSQL(webURL, webName, webUName, webUPassword, PMUsername, PMPassword);
                }
            }
        });
    }
    
}

 