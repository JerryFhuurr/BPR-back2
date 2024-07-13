package com.bpr.bprbackend2.service.impl;

import com.bpr.bprbackend2.mapper.CommentMapper;
import com.bpr.bprbackend2.mapper.HistoryMapper;
import com.bpr.bprbackend2.mapper.UserMapper;
import com.bpr.bprbackend2.mapper.VideoMapper;
import com.bpr.bprbackend2.model.Comment;
import com.bpr.bprbackend2.model.User;
import com.bpr.bprbackend2.model.VideoFile;
import com.bpr.bprbackend2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private HistoryMapper historyMapper;
    @Autowired
    private VideoMapper videoMapper;

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

    @Override
    public ArrayList<User> getAll() {
        return mapper.getAll();
    }

    @Override
    public String removeUser(int id) {
        try {
            historyMapper.removeHistoryByUser(id);
            if (commentMapper.getCommentListByUser(id).size() != 0) {
                ArrayList<Comment> comments = commentMapper.getCommentListByUser(id);
                for (Comment comment : comments) {
                    commentMapper.removeComment(comment.getCommentId());
                }
            }
            mapper.removeUserCourse(id);
            mapper.removeUser(id);

            return "User successfully removed";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

}
