package main.java.com.app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountSecretQuestionDAO {
    private DatabaseConnection connection;

    public AccountSecretQuestionDAO(DatabaseConnection connection) {
        this.connection = connection;
    }

    public void addSecretQuestion(Account account, String secretQuestion, String secretAnswer) throws SQLException {
        String sql = "INSERT INTO account_secret_question (account_id, secret_question, secret_answer) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, account.getId());
        statement.setString(2, secretQuestion);
        statement.setString(3, secretAnswer);
        statement.executeUpdate();
    }

    public String getSecretQuestion(Account account) throws SQLException {
        String sql = "SELECT secret_question FROM account_secret_question WHERE account_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, account.getId());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString("secret_question");
        } else {
            return null;
        }
    }

    public boolean checkSecretAnswer(Account account, String secretAnswer) throws SQLException {
        String sql = "SELECT secret_answer FROM account_secret_question WHERE account_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, account.getId());
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString("secret_answer").equals(secretAnswer);
        } else {
            return false;
        }
    }
}