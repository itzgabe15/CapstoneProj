import java.sql.*;

public class App {

    private static String username = "admin";
    private static String password = "itsc4155group3";

    private static String connectionString = "jdbc:mysql://capstoneproject.ck3v6fmgoed4.us-east-2.rds.amazonaws.com/PasswordManager";
    private static Connection connection;
    private static Statement command;
    private static ResultSet data;
    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection(connectionString, username, password);
            command = connection.createStatement();
            command.execute("CREATE TABLE passwords (website VARCHAR(40) NOT NULL, username VARCHAR(40) NOT NULL, password VARCHAR(40) NOT NULL, PRIMARY KEY (website))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}  	