package main.test.java;

import main.java.com.app.Account;
import main.java.com.app.AccountDAO;
import main.java.com.app.DatabaseConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountDAOTest {
    private AccountDAO accountDAO;

    @Mock
    private DatabaseConnection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @Before
    public void setUp() {
        accountDAO = new AccountDAO(mockConnection);
    }

    @Test
    public void testAddAccount() throws SQLException {
        Account account = new Account();
        account.setUsername("testuser");
        account.setFirstName("Test");
        account.setLastName("User");
        account.setPassword("Test@123");

        String sql = "INSERT INTO accountmanager (username, firstname, lastname, password) VALUES (?, ?, ?, ?)";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);

        accountDAO.addAccount(account);

        verify(mockPreparedStatement).setString(1, account.getUsername());
        verify(mockPreparedStatement).setString(2, account.getFirstName());
        verify(mockPreparedStatement).setString(3, account.getLastName());
        verify(mockPreparedStatement).setString(4, account.getPassword());
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testGetAccount() throws SQLException {
        String username = "testuser";
        String sql = "SELECT * FROM accountmanager WHERE username = ?";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("username")).thenReturn(username);
        when(mockResultSet.getString("firstname")).thenReturn("Test");
        when(mockResultSet.getString("lastname")).thenReturn("User");
        when(mockResultSet.getString("password")).thenReturn("Test@123");

        Account account = accountDAO.getAccount(username);

        assertNotNull(account);
        assertEquals(1, account.getId());
        assertEquals(username, account.getUsername());
        assertEquals("Test", account.getFirstName());
        assertEquals("User", account.getLastName());
        assertEquals("Test@123", account.getPassword());

        verify(mockPreparedStatement).setString(1, username);
    }

    @Test
    public void testDeleteAccount() throws SQLException {
        int accountId = 1;
        String sql = "DELETE FROM accountmanager WHERE id = ?";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);

        accountDAO.deleteAccount(accountId);

        verify(mockPreparedStatement).setInt(1, accountId);
        verify(mockPreparedStatement).executeUpdate();
    }
}

