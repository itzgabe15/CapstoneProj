package main.test.java;

import main.java.com.app.DatabaseConnection;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseConnectionTest {

    private DatabaseConnection databaseConnection;

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @Before
    public void setUp() {
        databaseConnection = new DatabaseConnection();
    }

    private void setPrivateField(Object object, String fieldName, Object value) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }

    @Test
    public void testPrepareStatement() throws SQLException, NoSuchFieldException, IllegalAccessException {
        String sql = "SELECT * FROM users";
        when(mockConnection.prepareStatement(sql)).thenReturn(mockPreparedStatement);

        setPrivateField(databaseConnection, "connection", mockConnection);

        PreparedStatement preparedStatement = databaseConnection.prepareStatement(sql);

        assertEquals(mockPreparedStatement, preparedStatement);
        verify(mockConnection).prepareStatement(sql);
    }

    @Test
    public void testExecuteQuery() throws SQLException, NoSuchFieldException, IllegalAccessException {
        String query = "SELECT * FROM users";
        when(mockConnection.createStatement()).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery(query)).thenReturn(mockResultSet);

        setPrivateField(databaseConnection, "connection", mockConnection);

        ResultSet resultSet = databaseConnection.executeQuery(query);

        assertEquals(mockResultSet, resultSet);
        verify(mockPreparedStatement).executeQuery(query);
    }

    @Test
    public void testCloseConnection() throws SQLException, NoSuchFieldException, IllegalAccessException {
        setPrivateField(databaseConnection, "connection", mockConnection);
        setPrivateField(databaseConnection, "statement", mockPreparedStatement);
        setPrivateField(databaseConnection, "resultSet", mockResultSet);

        databaseConnection.closeConnection();

        verify(mockResultSet).close();
        verify(mockPreparedStatement).close();
        verify(mockConnection).close();
    }
}
