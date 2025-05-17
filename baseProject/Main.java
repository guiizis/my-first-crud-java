// copilot: disable

import java.util.List;
import java.util.Scanner;
import model.user.User;

public class Main {

    public static void main(String[] args) {
        List<User> users = UserTestRepository.loadUsers();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n CRUD Menu:");
            System.out.println("1. Create User");
            System.out.println("2. Read Users");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.print("type a user's name: ");
                    String name = scanner.nextLine();
                    System.out.print("type a user's age: ");
                    int age = scanner.nextInt();
                    users.add(new User(name, String.valueOf(age)));
                    UserTestRepository.saveUsers(users);
                    System.out.println("User add with success");
                }
                case 2 -> {
                    System.out.println("\n Users registered");
                    for (int i = 0; i < users.size(); i++) {
                        User user = users.get(i);
                        System.out.println(i + ": " + user.getName() + " (" + user.getAge() + " years old)");
                    }
                }
                case 3 -> {
                    System.out.println("select an user to update");
                    int indexToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    if (indexToUpdate >= 0 && indexToUpdate < users.size()) {
                        System.out.print("type the new user's name: ");
                        String newName = scanner.nextLine();
                        System.out.print("type the new user's age: ");
                        int newAge = scanner.nextInt();
                        User userToUpdate = users.get(indexToUpdate);
                        userToUpdate.setName(newName);
                        userToUpdate.setAge(String.valueOf(newAge));
												UserTestRepository.saveUsers(users);
                        System.out.println("User updated");
                    } else {
                        System.out.println("User not found");
                    }
                }
                case 4 -> {
                    System.out.println("type user's index to delete");
                    int indexToRemove = scanner.nextInt();
                    if (indexToRemove >= 0 && indexToRemove <= users.size()) {
                        users.remove(indexToRemove);
												UserTestRepository.saveUsers(users);
                        System.out.println("user deleted");
                    } else {
                        System.out.println("user not found, try again");
                    }
                }
                case 5 -> {
                    running = false;
                    System.out.println("Closing ...");
                }
                default ->
                    System.out.println("choose another option");
            }
        }
    }
}
