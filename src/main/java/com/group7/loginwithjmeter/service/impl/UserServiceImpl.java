package com.group7.loginwithjmeter.service.impl;

import com.group7.loginwithjmeter.model.User;
import com.group7.loginwithjmeter.repository.UserRepository;
import com.group7.loginwithjmeter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password).get();
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }
}
