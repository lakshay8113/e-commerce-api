package com.example.ecommerce.service;

import java.util.List;

import com.example.ecommerce.entity.User;

public interface UserService {

    List<User> getAllUsers();

    User addUser(User user);

    User getUserById(String id);

    User updateUser(String id, User updatedUser);

    boolean deleteUser(String id);
}
