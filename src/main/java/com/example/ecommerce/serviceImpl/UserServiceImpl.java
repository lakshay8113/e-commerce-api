package com.example.ecommerce.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    public User addUser(User user) {
        User userToSend = new User();

        userToSend.setFirstName(user.getFirstName());
        userToSend.setLastName(user.getLastName());
        userToSend.setEmail(user.getEmail());
        userToSend.setPhoneNo(user.getPhoneNo());
        userToSend.setAddresses(user.getAddresses());

        userToSend.setCreatedAt(new Date());
        userToSend.setUpdatedAt(new Date());
        userToSend.setDeleted(false);

        return userRepository.save(userToSend);
    }

    @Override
    public User getUserById(String myId) {
        return userRepository.findUserById(myId).orElse(null);
    }

    @Override
    public User updateUser(String id, User updatedUser) {
        User userFromDB = userRepository.findUserById(id).orElse(null);

        if (userFromDB != null) {
            if (updatedUser.getFirstName() != null) {
                userFromDB.setFirstName(updatedUser.getFirstName());
            }

            if (updatedUser.getLastName() != null) {
                userFromDB.setLastName(updatedUser.getLastName());
            }

            if (updatedUser.getEmail() != null) {
                userFromDB.setEmail(updatedUser.getEmail());
            }

            if (updatedUser.getPhoneNo() != 0) {
                userFromDB.setPhoneNo(updatedUser.getPhoneNo());
            }

            if (updatedUser.getAddresses() != null) {
                userFromDB.setAddresses(updatedUser.getAddresses());
            }

            userFromDB.setUpdatedAt(new Date());

            return userRepository.save(userFromDB);
        }

        return null;
    }

    @Override
    public boolean deleteUser(String id) {
        User userFromDB = userRepository.findUserById(id).orElse(null);

        if (userFromDB != null) {
            userFromDB.setDeleted(true);
            userFromDB.setUpdatedAt(new Date());
            userRepository.save(userFromDB);
            return true;
        }

        return false;
    }
}
