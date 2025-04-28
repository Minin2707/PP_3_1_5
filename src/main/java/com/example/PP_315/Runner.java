package com.example.PP_315;

import com.example.PP_315.entity.User;
import com.example.PP_315.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final UserService userService;

    public Runner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.getAllUsers(); // Получаем пользователей и сохраняем sessionId

        User newUser = new User(3L, "James", "Brown", (byte) 25);
        userService.saveUser(newUser); // Сохраняем нового пользователя


        User updatedUser = new User(3L, "Thomas", "Shelby", (byte) 25);
        userService.updateUser(updatedUser);


        userService.deleteUser(3L);

    }
}
