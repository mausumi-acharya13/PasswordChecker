import java.util.*;
import java.util.regex.*;

public class PasswordStrengthChecker {

    private static final Set<String> COMMON_PASSWORDS = Set.of(
        "password", "123456", "12345678", "qwerty", "abc123", "letmein", "iloveyou", "admin", "welcome"
    );

    public static String evaluatePassword(String password) {
        List<String> issues = new ArrayList<>();
        int score = 0;

        if (password.length() < 8) {
            issues.add("Too short (less than 8 characters)");
        } else if (password.length() >= 12) {
            score += 2;
        } else {
            score += 1;
        }

        if (password.matches(".*[A-Z].*")) score++;
        else issues.add("Missing uppercase letters");

        if (password.matches(".*[a-z].*")) score++;
        else issues.add("Missing lowercase letters");

        if (password.matches(".*\\d.*")) score++;
        else issues.add("Missing numbers");

        if (password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) score++;
        else issues.add("Missing special characters");

        if (COMMON_PASSWORDS.contains(password.toLowerCase())) {
            issues.add("Password is too common");
            score = 0;
        }

        if (containsDictionaryWord(password)) {
            issues.add("Contains dictionary word");
        }

        if (hasRepeatedPatterns(password)) {
            issues.add("Contains repeated patterns");
        }

        
        String strength;
        if (score <= 2) strength = "Weak";
        else if (score <= 4) strength = "Moderate";
        else strength = "Strong";

        
        System.out.println("Password Strength: " + strength);
        if (!issues.isEmpty()) {
            System.out.println("Weaknesses detected:");
            for (String issue : issues) {
                System.out.println("- " + issue);
            }
        } else {
            System.out.println("No major weaknesses detected.");
        }

        System.out.println("Recommendations:");
        if (score < 5) {
            System.out.println("- Use at least 12 characters.");
            System.out.println("- Include uppercase, lowercase, numbers, and special symbols.");
            System.out.println("- Avoid common or predictable patterns.");
        } else {
            System.out.println("- Great job! Keep using strong, unique passwords.");
        }

        return strength;
    }

    
    private static boolean containsDictionaryWord(String password) {
        String[] dictionary = {"love", "admin", "welcome", "god", "hello"};
        for (String word : dictionary) {
            if (password.toLowerCase().contains(word)) {
                return true;
            }
        }
        return false;
    }

    
    private static boolean hasRepeatedPatterns(String password) {
        return Pattern.compile("(\\w{2,})\\1+").matcher(password.toLowerCase()).find();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your password for strength evaluation:");
        String password = scanner.nextLine();
        evaluatePassword(password);
    }
}