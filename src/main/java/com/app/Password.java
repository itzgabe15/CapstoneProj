package main.java.com.app;

public class Password {
    private String websiteUrl;
    private String websiteName;
    private String websiteUsername;
    private String websitePassword;
    private int id;

    public Password() {
    }

    public Password(int id, String websiteUrl, String websiteName, String websiteUsername, String websitePassword) {
        this.id = id;
        this.websiteUrl = websiteUrl;
        this.websiteName = websiteName;
        this.websiteUsername = websiteUsername;
        this.websitePassword = websitePassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getWebsiteUsername() {
        return websiteUsername;
    }

    public void setWebsiteUsername(String username) {
        this.websiteUsername = username;
    }

    public String getWebsitePassword() {
        return websitePassword;
    }

    public void setWebsitePassword(String password) {
        this.websitePassword = password;
    }
}
