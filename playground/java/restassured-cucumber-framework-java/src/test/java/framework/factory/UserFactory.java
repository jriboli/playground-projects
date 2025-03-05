package framework.factory;

import framework.playloads.bookstore.User;

import java.util.Random;

public class UserFactory {

    private static final Random random = new Random();

    public static User staticUser() {
        return new User("Buddy", "4VzkgDzpqtEa5kA5B3Rf3p3uJ1!", "0d285499-fde7-4850-83bc-b079a7db30d0");
    }

    public static User createRandomUser() {
        String username = getUserName();
        String password = getPassword();

        return new User(username, password, null);
    }

    private static String getUserName() {
        String[] names = {"Buddy", "Rover", "Charlie", "Max", "Hunter", "Shadow", "Drax", "Lucy", "Bella"};
        return names[random.nextInt(names.length)];
    }

    private static String getPassword() {
        // Generate a random length between 5 and 50
        int passwordLength = 8 + random.nextInt(43);  // 5 + (0 to 42)

        // Characters to use in the password
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // StringBuilder to create the password
        StringBuilder password = new StringBuilder(passwordLength);

        // Populate the password with random letters and numbers
        for (int i = 0; i < passwordLength; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString() + "!";
    }
}
