package user;

import java.awt.datatransfer.SystemFlavorMap;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.user.User;

public class UserRepository {

    private static final String FILE_NAME = "users.txt";

    public static void saveUsers(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : users) {
                writer.write(user.getName() + "," + user.getAge());
                writer.newLine();
            }
        } catch (IOException exception) {
            System.out.println("Error when tried to save users " + exception.getMessage());
        }
    }

    public static List<User> loadUsers() {
        List<User> usersList = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return usersList;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                  String name = parts[0];
                  String age = parts[1];
                  usersList.add(new User(name, age));
                }
            }
        } catch (IOException exception) {
          System.out.println("Error when tried to load the user: " + "," + exception.getMessage());
        }
        return usersList;
    }
}
