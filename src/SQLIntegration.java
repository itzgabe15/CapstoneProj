/*
import java.sql.*;

public class SQLIntegration {

    private static String username = "admin";
    private static String password = "itsc4155group3";

    private static String connectionString = "jdbc:mysql://capstoneproject.ck3v6fmgoed4.us-east-2.rds.amazonaws.com/PasswordManager";
    private static Connection connection;
    private static Statement command;
    private static ResultSet data;
    public void executeSQL(String webURL, String webName, String webUName, String webUPassword, String PMUsername, String PMPassword) {
        try {
            connection = DriverManager.getConnection(connectionString, username, password);
            command = connection.createStatement();
            command.execute("INSERT INTO usersForPM VALUES (" + PMUsername + ", " + PMPassword + ")");
            command.execute("INSERT INTO userPasswords VALUES (" + webURL + ", " + webName + ", " + webUName + ", " + webUPassword + ", " + PMUsername + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}  	
*/
import java.sql.*;

public class SQLIntegration {

    private static String username = "admin";
    private static String password = "itsc4155group3";
    private static String connectionString = "jdbc:mysql://capstoneproject.ck3v6fmgoed4.us-east-2.rds.amazonaws.com/PasswordManager";
    
    public void readSQLpasswords() {
        try {
            Connection connection = DriverManager.getConnection(connectionString, username, password);

            String query = "SELECT * FROM PasswordManager.userPasswords";

            Statement command = connection.createStatement();
            ResultSet data = command.executeQuery(query);

            while (data.next()) {
                int id = data.getInt(
                    "id");
                String websiteUrl = data.getString("websiteUrl");
                String websiteName = data.getString("websiteName");
                String userName = data.getString("userName");
                String userPassword = data.getString("Password");
                System.out.println(id + "\t" + websiteUrl + "\t" + websiteName + "\t" + userName + "\t" + userPassword);
            }

            data.close();
            command.close();
            connection.close();
        } catch (Exception e) {
            System.err.println("Error");
        }
    }
    
    public void executeSQL(String webURL, String webName, String webUName, String webUPassword, String PMUsername, String PMPassword) {
        try {
            Connection connection = DriverManager.getConnection(connectionString, username, password);
            
            // Insert into 'userPasswords' table using a prepared statement
            String insertPasswordSql = "INSERT INTO userPasswords (websiteUrl, websiteName, userName, Password, userid) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertPasswordStmt = connection.prepareStatement(insertPasswordSql);
            insertPasswordStmt.setString(1, webURL);
            insertPasswordStmt.setString(2, webName);
            insertPasswordStmt.setString(3, webUName);
            insertPasswordStmt.setString(4, webUPassword);
            insertPasswordStmt.setString(5, PMUsername);
            insertPasswordStmt.executeUpdate();
            
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateSQL(String webURL, String webName, String webUName, String webUPassword, String prevWebsite, String prevUsername) {
        try {
            Connection connection = DriverManager.getConnection(connectionString, username, password);

            // Insert into 'userPasswords' table using a prepared statement
            String updatePasswordSql = "UPDATE userPasswords SET websiteUrl = ?, websiteName = ?, userName = ?, Password = ? WHERE websiteName = "
                + prevWebsite + " AND userName = " + prevUsername;
            PreparedStatement insertPasswordStmt = connection.prepareStatement(updatePasswordSql);
            insertPasswordStmt.setString(1, webURL);
            insertPasswordStmt.setString(2, webName);
            insertPasswordStmt.setString(3, webUName);
            insertPasswordStmt.setString(4, webUPassword);
            insertPasswordStmt.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePassword(int passwordID) {
        try {
            Connection connection = DriverManager.getConnection(connectionString, username, password);

            //Delete password based on its ID
            String deletePasswordSql = "DELETE FROM usersForPM WHERE id = ?";
            PreparedStatement deletePasswordStmt = connection.prepareStatement(deletePasswordSql);
            deletePasswordStmt.setInt(1, passwordID);
            deletePasswordStmt.executeUpdate();

            deletePasswordStmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}