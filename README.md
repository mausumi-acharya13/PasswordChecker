# PasswordChecker
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class PasswordStrengthChecker {
   private static final Set<String> COMMON_PASSWORDS = Set.of("password", "123456", "12345678", "qwerty", "abc123", "letmein", "iloveyou", "admin", "welcome");

   public PasswordStrengthChecker() {
   }

   public static String evaluatePassword(String var0) {
      ArrayList var1 = new ArrayList();
      int var2 = 0;
      if (var0.length() < 8) {
         var1.add("Too short (less than 8 characters)");
      } else if (var0.length() >= 12) {
         var2 += 2;
      } else {
         ++var2;
      }

      if (var0.matches(".*[A-Z].*")) {
         ++var2;
      } else {
         var1.add("Missing uppercase letters");
      }

      if (var0.matches(".*[a-z].*")) {
         ++var2;
      } else {
         var1.add("Missing lowercase letters");
      }

      if (var0.matches(".*\\d.*")) {
         ++var2;
      } else {
         var1.add("Missing numbers");
      }

      if (var0.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
         ++var2;
      } else {
         var1.add("Missing special characters");
      }

      if (COMMON_PASSWORDS.contains(var0.toLowerCase())) {
         var1.add("Password is too common");
         var2 = 0;
      }

      if (containsDictionaryWord(var0)) {
         var1.add("Contains dictionary word");
      }

      if (hasRepeatedPatterns(var0)) {
         var1.add("Contains repeated patterns");
      }

      String var3;
      if (var2 <= 2) {
         var3 = "Weak";
      } else if (var2 <= 4) {
         var3 = "Moderate";
      } else {
         var3 = "Strong";
      }

      System.out.println("Password Strength: " + var3);
      if (!var1.isEmpty()) {
         System.out.println("Weaknesses detected:");
         Iterator var4 = var1.iterator();

         while(var4.hasNext()) {
            String var5 = (String)var4.next();
            System.out.println("- " + var5);
         }
      } else {
         System.out.println("No major weaknesses detected.");
      }

      System.out.println("Recommendations:");
      if (var2 < 5) {
         System.out.println("- Use at least 12 characters.");
         System.out.println("- Include uppercase, lowercase, numbers, and special symbols.");
         System.out.println("- Avoid common or predictable patterns.");
      } else {
         System.out.println("- Great job! Keep using strong, unique passwords.");
      }

      return var3;
   }

   private static boolean containsDictionaryWord(String var0) {
      String[] var1 = new String[]{"love", "admin", "welcome", "god", "hello"};
      String[] var2 = var1;
      int var3 = var1.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String var5 = var2[var4];
         if (var0.toLowerCase().contains(var5)) {
            return true;
         }
      }

      return false;
   }

   private static boolean hasRepeatedPatterns(String var0) {
      return Pattern.compile("(\\w{2,})\\1+").matcher(var0.toLowerCase()).find();
   }

   public static void main(String[] var0) {
      Scanner var1 = new Scanner(System.in);
      System.out.println("Enter your password for strength evaluation:");
      String var2 = var1.nextLine();
      evaluatePassword(var2);
   }
}
