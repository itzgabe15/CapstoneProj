package main.test.java;

import org.junit.*;

import main.java.com.app.DatabaseConnection;

public class MainTest {
    
    private DatabaseConnection dbConnection;

    
    @Before
    public void setUp() {
        dbConnection = new DatabaseConnection();
        dbConnection.connect();
    }
    
    @After
    public void tearDown() {
        dbConnection.closeConnection();
    }
    
    @Test
    public void testConnection() {
        dbConnection.getConnection();
    }
    
}
