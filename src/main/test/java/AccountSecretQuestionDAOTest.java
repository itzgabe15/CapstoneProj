package main.test.java;

import main.java.com.app.Account;
import main.java.com.app.AccountSecretQuestionDAO;
import main.java.com.app.DatabaseConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountSecretQuestionDAOTest {

    private AccountSecretQuestionDAO accountSecretQuestionDAO;

    @Mock
    private DatabaseConnection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @Before
    public void setUp() {
        accountSecretQuestionDAO = new AccountSecretQuestionDAO(mockConnection);
    }

    @Test
    public void testAddSecretQuestion() throws SQLException {
        Account account = new Account();
        account.setId(1);
        String secretQuestion = "What is your favorite color?";
        String secretAnswer = "Blue";

        String sql = "INSERT INTO account_secret_question (account_id, secret_question, secret_answer) VALUES (?, ?, ?)";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);

        accountSecretQuestionDAO.addSecretQuestion(account, secretQuestion, secretAnswer);

        verify(mockPreparedStatement).setInt(1, account.getId());
        verify(mockPreparedStatement).setString(2, secretQuestion);
        verify(mockPreparedStatement).setString(3, secretAnswer);
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testGetSecretQuestion() throws SQLException {
        Account account = new Account();
        account.setId(1);
        String secretQuestion = "What is your favorite color?";

        String sql = "SELECT secret_question FROM account_secret_question WHERE account_id = ?";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("secret_question")).thenReturn(secretQuestion);

        String result = accountSecretQuestionDAO.getSecretQuestion(account);

        assertEquals(secretQuestion, result);
        verify(mockPreparedStatement).setInt(1, account.getId());
    }

    @Test
    public void testCheckSecretAnswer() throws SQLException {
        Account account = new Account();
        account.setId(1);
        String secretAnswer = "Blue";

        String sql = "SELECT secret_answer FROM account_secret_question WHERE account_id = ?";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("secret_answer")).thenReturn(secretAnswer);

        boolean isCorrect = accountSecretQuestionDAO.checkSecretAnswer(account, secretAnswer);

        assertTrue(isCorrect);
        verify(mockPreparedStatement).setInt(1, account.getId());

        isCorrect = accountSecretQuestionDAO.checkSecretAnswer(account, "Red");

        assertFalse(isCorrect);
    }
}

