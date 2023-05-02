package main.test.java;

import org.junit.Test;
import static org.junit.Assert.*;
import main.java.com.app.Password;


public class PasswordTest {
    @Test
    public void testPassword() {
        int id = 1;
        String websiteUrl = "https://example.com";
        String websiteName = "Example Website";
        String websiteUsername = "user123";
        String websitePassword = "myp@ssw0rd";

        // Test constructor and getters
        Password password = new Password(id, websiteUrl, websiteName, websiteUsername, websitePassword);

        assertEquals(id, password.getId());
        assertEquals(websiteUrl, password.getWebsiteUrl());
        assertEquals(websiteName, password.getWebsiteName());
        assertEquals(websiteUsername, password.getWebsiteUsername());
        assertEquals(websitePassword, password.getWebsitePassword());

        // Test setters
        int newId = 2;
        String newWebsiteUrl = "https://example2.com";
        String newWebsiteName = "Example Website 2";
        String newWebsiteUsername = "user456";
        String newWebsitePassword = "newP@ssw0rd";

        password.setId(newId);
        password.setWebsiteUrl(newWebsiteUrl);
        password.setWebsiteName(newWebsiteName);
        password.setWebsiteUsername(newWebsiteUsername);
        password.setWebsitePassword(newWebsitePassword);

        assertEquals(newId, password.getId());
        assertEquals(newWebsiteUrl, password.getWebsiteUrl());
        assertEquals(newWebsiteName, password.getWebsiteName());
        assertEquals(newWebsiteUsername, password.getWebsiteUsername());
        assertEquals(newWebsitePassword, password.getWebsitePassword());
    }
}
