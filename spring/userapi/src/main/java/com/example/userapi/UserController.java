package com.example.userapi;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private List<User> users = UserRepository.getUsers();
    private AtomicLong idCounter = new AtomicLong(getMaxId());

    private long getMaxId() {
        return users.stream().mapToLong(User::getId).max().orElse(0L);
    }

    @GetMapping
    public List<User> listUsers() {
        return users;
    }

    @PostMapping
    public User createUser(User newUser) {
        newUser.setId(idCounter.incrementAndGet());
        users.add(newUser);
        UserRepository.saveUsers(users);
        return newUser;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                user.setName(updatedUser.getName());
                user.setAge(updatedUser.getAge());
                UserRepository.saveUsers(users);
                return updatedUser;
            }
        }
        throw new RuntimeException("we not found a user with this ID");
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        users.removeIf(user -> user.getId().equals(id));
        UserRepository.saveUsers(users);
        System.out.println("User deleted");
    }
}
