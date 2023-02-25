import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PasswordListFrame extends JFrame {

    private JTable passwordTable;
    private DefaultTableModel passwordTableModel;

    public PasswordListFrame(List<String> passwords) {

        JPanel panel = new JPanel();
        panel.setLayout(null);

        this.add(panel);
        this.setSize(800, 500);
        this.setTitle("Password List");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        passwordTableModel = new DefaultTableModel(new String[] {"Website URL", "Website Name", "Username", "Password"}, 0);
        passwordTable = new JTable(passwordTableModel);
        passwordTable.setBounds(10, 10, 780, 450);
        panel.add(passwordTable);


        String webURL = new String();
        String webName = new String();
        String webUName = new String();
        String webUPassword = new String();


        for (String password : passwords) {
            passwordTableModel.addRow(new String[] {webURL, webName, webUName, webUPassword, password});
        }

        this.setVisible(true);
    }

}



    





