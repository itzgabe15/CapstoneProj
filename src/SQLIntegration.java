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
    
    public void executeSQL(String webURL, String webName, String webUName, String webUPassword, String PMUsername, String PMPassword) {
        try {
            Connection connection = DriverManager.getConnection(connectionString, username, password);
            
            // Insert into 'usersForPM' table using a prepared statement
            String insertUserSql = "INSERT INTO usersForPM (usernameForPM, userPasswordForPM) VALUES (?, ?)";
            PreparedStatement insertUserStmt = connection.prepareStatement(insertUserSql);
            insertUserStmt.setString(1, PMUsername);
            insertUserStmt.setString(2, PMPassword);
            insertUserStmt.executeUpdate();
            
            // Insert into 'userPasswords' table using a prepared statement
            String insertPasswordSql = "INSERT INTO userPasswords (websiteURL, websiteName, webUsername, webPassword, usernameForPM) VALUES (?, ?, ?, ?, ?)";
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
}