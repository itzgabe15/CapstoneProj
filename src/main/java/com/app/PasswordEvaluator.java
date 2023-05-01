package main.java.com.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordEvaluator {

    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 64;

    private static final int MIN_LOWERCASE = 1;
    private static final int MIN_UPPERCASE = 1;
    private static final int MIN_SPECIAL_CHARS = 1;

    // Enumeration to represent password strength levels
    public enum PasswordStrength {
        WEAK, MEDIUM, STRONG, VERY_STRONG
    }

    public static PasswordStrength evaluatePasswordStrength(String password) {
        int length = password.length();

        // Check if password length is within allowed range
        if (length < MIN_PASSWORD_LENGTH || length > MAX_PASSWORD_LENGTH) {
            return PasswordStrength.WEAK;
        }


        // Count the number of lowercase, uppercase, and special characters
        int lowercaseCount = countMatches("[a-z]", password);
        int uppercaseCount = countMatches("[A-Z]", password);
        int specialCharCount = countMatches("[^a-zA-Z0-9]", password);


        // Determine password strength based on the counts of character types
        if (lowercaseCount >= MIN_LOWERCASE && uppercaseCount >= MIN_UPPERCASE && specialCharCount >= MIN_SPECIAL_CHARS) {
            return length >= 12 ? PasswordStrength.VERY_STRONG : PasswordStrength.STRONG;
        } else if (lowercaseCount > 0 || uppercaseCount > 0 || specialCharCount > 0) {
            return PasswordStrength.MEDIUM;
        } else {
            return PasswordStrength.WEAK;
        }
    }

    private static int countMatches(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }
}