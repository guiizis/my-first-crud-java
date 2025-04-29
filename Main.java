// copilot: disable

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.user.User;

public class Main {
  public static void main(String[] args) {
    List<User> users = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    while(running) {
      System.out.println("\n CRUD Menu:");
      System.out.println("1. Create User");
      System.out.println("2. Read Users");
      System.out.println("3. Update User");
      System.out.println("4. Delete User");
      System.out.println("5. Exit");
      System.out.print("Choose an option: ");

      int option = scanner.nextInt();
      scanner.nextLine();

      switch(option) {
        case 1:
          System.out.print("type a user's name: ");
          String name = scanner.nextLine();
          System.out.print("type a user's age: ");
          int age = scanner.nextInt();
          users.add(new User(name, String.valueOf(age)));
          System.out.println("User add with success");
      }
    }
  }
}