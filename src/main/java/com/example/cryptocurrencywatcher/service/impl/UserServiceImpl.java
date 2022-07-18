package com.example.cryptocurrencywatcher.service.impl;

import com.example.cryptocurrencywatcher.model.dto.UserDto;
import com.example.cryptocurrencywatcher.model.jpa.User;
import com.example.cryptocurrencywatcher.repository.UserRepository;
import com.example.cryptocurrencywatcher.service.CurrencyService;
import com.example.cryptocurrencywatcher.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CurrencyService currencyService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrencyService currencyService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currencyService = currencyService;
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        String price = currencyService.getCurrencyPrice(userDto.getSymbol());
        user.setPrice(price);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
