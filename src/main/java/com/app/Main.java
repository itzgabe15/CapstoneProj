package main.java.com.app;

import main.java.com.gui.LoginOrSignupWindow;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        dbConnection.connect();
        AccountDAO accountDAO = new AccountDAO(dbConnection);
        PasswordDAO passwordDAO = new PasswordDAO(dbConnection);

        LoginOrSignupWindow window = new LoginOrSignupWindow(accountDAO, passwordDAO);

    }
}
