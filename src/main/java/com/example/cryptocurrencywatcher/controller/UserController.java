package com.example.cryptocurrencywatcher.controller;

import com.example.cryptocurrencywatcher.model.dto.UserDto;
import com.example.cryptocurrencywatcher.model.jpa.User;
import com.example.cryptocurrencywatcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/notify")
    public ResponseEntity<User> notify(@RequestBody UserDto user) {

        System.out.println(user);
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }
}
