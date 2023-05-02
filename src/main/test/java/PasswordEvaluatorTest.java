package main.test.java;

import main.java.com.app.PasswordEvaluator;
import main.java.com.app.PasswordEvaluator.PasswordStrength;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordEvaluatorTest {

    @Test
    public void testEvaluatePasswordStrength() {
        // Test weak passwords
        assertEquals(PasswordStrength.WEAK, PasswordEvaluator.evaluatePasswordStrength("short"));
        assertEquals(PasswordStrength.WEAK, PasswordEvaluator.evaluatePasswordStrength("1234567"));

        // Test medium passwords
        assertEquals(PasswordStrength.MEDIUM, PasswordEvaluator.evaluatePasswordStrength("password123"));
        assertEquals(PasswordStrength.MEDIUM, PasswordEvaluator.evaluatePasswordStrength("Abcd1234"));

        // Test strong passwords
        assertEquals(PasswordStrength.STRONG, PasswordEvaluator.evaluatePasswordStrength("Pass$123"));
        assertEquals(PasswordStrength.STRONG, PasswordEvaluator.evaluatePasswordStrength("Str0ng@One"));

        // Test very strong passwords
        assertEquals(PasswordStrength.VERY_STRONG, PasswordEvaluator.evaluatePasswordStrength("Abc@123$Xyz789#"));
        assertEquals(PasswordStrength.VERY_STRONG, PasswordEvaluator.evaluatePasswordStrength("V3ry!Strong&Pa$$"));
    }
}