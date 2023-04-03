package main.java.com.app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasswordDAO {
    private DatabaseConnection connection;

    public PasswordDAO(DatabaseConnection connection) {
        this.connection = connection;
    }

    public void addUserPassword(String websiteUrl, String websiteName, String websiteUsername, String websitePassword, String username) throws SQLException {
        String sql = "INSERT INTO userPasswords (websiteUrl, websiteName, websiteUsername, websitePassword, userId) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, websiteUrl);
        statement.setString(2, websiteName);
        statement.setString(3, websiteUsername);
        statement.setString(4, websitePassword);
        statement.setString(5, username);
        statement.executeUpdate();
    }

    public List<Password> getUserPasswords(String username) throws SQLException {
        String sql = "SELECT * FROM userPasswords WHERE userId = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet resultSet = statement.executeQuery();

        List<Password> passwords = new ArrayList<>();
        while (resultSet.next()) {
            Password password = new Password();
            password.setId(resultSet.getInt("id"));
            password.setWebsiteUrl(resultSet.getString("websiteUrl"));
            password.setWebsiteName(resultSet.getString("websiteName"));
            password.setWebsiteUsername(resultSet.getString("websiteUsername"));
            password.setWebsitePassword(resultSet.getString("websitePassword"));
            passwords.add(password);
        }

        return passwords;
    }

    public void updateUserPassword(Password password) throws SQLException {
        String sql = "UPDATE userPasswords SET websiteUrl=?, websiteName=?, websiteUsername=?, websitePassword=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, password.getWebsiteUrl());
        statement.setString(2, password.getWebsiteName());
        statement.setString(3, password.getWebsiteUsername());
        statement.setString(4, password.getWebsitePassword());
        statement.setInt(5, password.getId());
        statement.executeUpdate();
    }
    

    public void deleteUserPassword(int passwordId) throws SQLException {
        String sql = "DELETE FROM userPasswords WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, passwordId);
        statement.executeUpdate();
    }
}
