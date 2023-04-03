package main.java.com.app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    private DatabaseConnection connection;

    public AccountDAO(DatabaseConnection connection) {
        this.connection = connection;
    }

    public void addAccount(Account account) throws SQLException {
        String sql = "INSERT INTO accountmanager (username, firstname, lastname, password) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, account.getUsername());
        statement.setString(2, account.getFirstName());
        statement.setString(3, account.getLastName());
        statement.setString(4, account.getPassword());
        statement.executeUpdate();
    }

    public Account getAccount(String username) throws SQLException {
        String sql = "SELECT * FROM accountmanager WHERE username = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Account account = new Account();
            account.setId(resultSet.getInt("id"));
            account.setUsername(resultSet.getString("username"));
            account.setFirstName(resultSet.getString("firstname"));
            account.setLastName(resultSet.getString("lastname"));
            account.setPassword(resultSet.getString("password"));
            return account;
        } else {
            return null;
        }
    }

    public void deleteAccount(int accountId) throws SQLException {
        String sql = "DELETE FROM accountmanager WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, accountId);
        statement.executeUpdate();
    }

}

