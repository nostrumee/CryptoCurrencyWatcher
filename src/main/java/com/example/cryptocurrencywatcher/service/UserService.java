package com.example.cryptocurrencywatcher.service;

import com.example.cryptocurrencywatcher.model.dto.UserDto;
import com.example.cryptocurrencywatcher.model.jpa.User;

import java.util.List;

public interface UserService {

    User createUser(UserDto user);

    List<User> getAllUsers();

}
