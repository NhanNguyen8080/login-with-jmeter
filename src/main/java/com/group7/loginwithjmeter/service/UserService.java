package com.group7.loginwithjmeter.service;

import com.group7.loginwithjmeter.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUsers();

    public boolean login(String username, String password);

    public Optional<User> getUserByUsername(String username);
}
