package com.bpr.bprbackend2.service;

import com.bpr.bprbackend2.model.User;

import java.util.ArrayList;

public interface UserService {
    String loginGet(String username, String password);
    User getUserInfo(String username);
    String updateUserPassword(String username, String newPassword, String oldPassword);
    String updateUserInfo(User user);
    ArrayList<User> getAll();
    String removeUser(int id);
    String addUser(User user, int[] courses);
}
