package com.bpr.bprbackend2.service.impl;

import com.bpr.bprbackend2.mapper.UserMapper;
import com.bpr.bprbackend2.model.User;
import com.bpr.bprbackend2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;

    @Override
    public String loginGet(String username, String password) {
        User userGet = mapper.loginGet(username);
        if (userGet != null) {
            if (userGet.getPassword().equals(password)) {
                return "Login successful";
            } else return "Login failed - password does not match";
        } else return "Login failed - username is incorrect";
    }

    @Override
    public User getUserInfo(String username) {
        String role = mapper.getUserRole(username);
        User user = mapper.getUserInfo(username);
        user.setRole(role);
        return user;
    }

    @Override
    public String updateUserPassword(String username, String newPassword, String oldPassword) {
        String currentPassword = mapper.loginGet(username).getPassword();
        if (!currentPassword.equals(oldPassword)) {
            return "Password does not match";
        } else if (newPassword.length() < 6) {
            return "Password must be at least 6 characters";
        } else {
            mapper.updateUserPassword(username, newPassword);
            return "Password updated successfully";
        }
    }

    @Override
    public String updateUserInfo(User user) {
        if (mapper.getUserInfoByUserId(user.getUserId()).getUsername().equals(user.getUsername())) { // check if username is changed
            mapper.updateUserInfo(user);
            return "User updated successfully";
        } else {
            int count = 0;
            ArrayList<String> usernames = mapper.getUsernames();
            for (String username : usernames) {
                if (username.equals(user.getUsername())) {
                    count++;
                }
            }
            if (count == 0) {
                mapper.updateUserInfo(user);
                return "User successfully updated";
            } else return "Username already in use";
        }

    }

}
