package main.test.java;

import org.junit.*;

import main.java.com.app.Account;

import static org.junit.Assert.*;

public class AccountTest {
    
    private Account account;
    
    @Before
    public void setUp() {
        account = new Account("testuser", "John", "Doe", "password", "What is your favorite color?");
        account.setId(1);
    }
    
    @Test
    public void testGetId() {
        assertEquals(1, account.getId());
    }
    
    @Test
    public void testGetUsername() {
        assertEquals("testuser", account.getUsername());
    }
    
    @Test
    public void testGetFirstName() {
        assertEquals("John", account.getFirstName());
    }
    
    @Test
    public void testGetLastName() {
        assertEquals("Doe", account.getLastName());
    }
    
    @Test
    public void testGetPassword() {
        assertEquals("password", account.getPassword());
    }
    
    @Test
    public void testGetSecretQuestion() {
        assertEquals("What is your favorite color?", account.getSecretQuestion());
    }
    
    @Test
    public void testSetId() {
        account.setId(2);
        assertEquals(2, account.getId());
    }
    
    @Test
    public void testSetUsername() {
        account.setUsername("newuser");
        assertEquals("newuser", account.getUsername());
    }
    
    @Test
    public void testSetFirstName() {
        account.setFirstName("Jane");
        assertEquals("Jane", account.getFirstName());
    }
    
    @Test
    public void testSetLastName() {
        account.setLastName("Smith");
        assertEquals("Smith", account.getLastName());
    }
    
    @Test
    public void testSetPassword() {
        account.setPassword("newpassword");
        assertEquals("newpassword", account.getPassword());
    }
    
    @Test
    public void testSetSecretQuestion() {
        account.setSecretQuestion("What is your mother's maiden name?");
        assertEquals("What is your mother's maiden name?", account.getSecretQuestion());
    }
}
