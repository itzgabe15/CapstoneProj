package main.test.java;

import main.java.com.app.DatabaseConnection;
import main.java.com.app.Password;
import main.java.com.app.PasswordDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PasswordDAOTest {
    private PasswordDAO passwordDAO;

    @Mock
    private DatabaseConnection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @Before
    public void setUp() {
        passwordDAO = new PasswordDAO(mockConnection);
    }

    @Test
    public void testAddUserPassword() throws SQLException {
        String sql = "INSERT INTO userPasswords (websiteUrl, websiteName, websiteUsername, websitePassword, userId) VALUES (?, ?, ?, ?, ?)";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);

        passwordDAO.addUserPassword("https://example.com", "Example", "user", "pass", "username");

        verify(mockPreparedStatement).setString(1, "https://example.com");
        verify(mockPreparedStatement).setString(2, "Example");
        verify(mockPreparedStatement).setString(3, "user");
        verify(mockPreparedStatement).setString(4, "pass");
        verify(mockPreparedStatement).setString(5, "username");
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testGetUserPasswords() throws SQLException {
        String sql = "SELECT * FROM userPasswords WHERE userId = ?";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);

        Password mockPassword = new Password();
        mockPassword.setId(1);
        mockPassword.setWebsiteUrl("https://example.com");
        mockPassword.setWebsiteName("Example");
        mockPassword.setWebsiteUsername("user");
        mockPassword.setWebsitePassword("pass");

        when(mockResultSet.getInt("id")).thenReturn(mockPassword.getId());
        when(mockResultSet.getString("websiteUrl")).thenReturn(mockPassword.getWebsiteUrl());
        when(mockResultSet.getString("websiteName")).thenReturn(mockPassword.getWebsiteName());
        when(mockResultSet.getString("websiteUsername")).thenReturn(mockPassword.getWebsiteUsername());
        when(mockResultSet.getString("websitePassword")).thenReturn(mockPassword.getWebsitePassword());

        List<Password> passwords = passwordDAO.getUserPasswords("username");

        assertEquals(1, passwords.size());
    }

    @Test
    public void testUpdateUserPassword() throws SQLException {
        Password password = new Password();
        password.setId(1);
        password.setWebsiteUrl("https://example.com");
        password.setWebsiteName("Example");
        password.setWebsiteUsername("user");
        password.setWebsitePassword("pass");

        String sql = "UPDATE userPasswords SET websiteUrl=?, websiteName=?, websiteUsername=?, websitePassword=? WHERE id=?";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);

        passwordDAO.updateUserPassword(password);

        verify(mockPreparedStatement).setString(1, password.getWebsiteUrl());
        verify(mockPreparedStatement).setString(2, password.getWebsiteName());
        verify(mockPreparedStatement).setString(3, password.getWebsiteUsername());
        verify(mockPreparedStatement).setString(4, password.getWebsitePassword());
        verify(mockPreparedStatement).setInt(5, password.getId());
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testDeleteUserPassword() throws SQLException {
        int passwordId = 1;
        String sql = "DELETE FROM userPasswords WHERE id = ?";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);
    
        passwordDAO.deleteUserPassword(passwordId);
    
        verify(mockPreparedStatement).setInt(1, passwordId);
        verify(mockPreparedStatement).executeUpdate();
    }
}    

