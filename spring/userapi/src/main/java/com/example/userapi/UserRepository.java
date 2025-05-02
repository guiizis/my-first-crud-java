package com.example.userapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private static final String FILE_NAME = "user.txt";

    private static void saveUsers(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : users) {
                writer.write(user.getId() + "," + user.getName() + "," + user.getAge());
                writer.newLine();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong when we tried to save users: " + e.getMessage());
        }
    }

    private static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return users;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while((line = reader.readLine()) != null) {
              String[] parts = line.split(",");
              if (parts.length == 3) {
                Long id = Long.parseLong(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                users.add(new User(id, name, age));
              }
            }
        } catch (Exception e) {
          System.out.println("Something went wrong when we tried to list users: " + e.getMessage());
        }
        return users;
    }
}
