package com.bpr.bprbackend2.controller;

import com.bpr.bprbackend2.model.User;
import com.bpr.bprbackend2.model.VideoFile;
import com.bpr.bprbackend2.service.UserService;
import com.bpr.bprbackend2.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private VideoService videoService;

    @GetMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if (username.equals("ET")) {
            return "Cannot login with this account !";
        } else {
            return userService.loginGet(username, password);
        }
    }

    @GetMapping("/getinfo")
    public User getInfo(@RequestParam String username) {
        return userService.getUserInfo(username);
    }

    @PutMapping("/update/password")
    public String updatePassword(@RequestParam String username, @RequestParam String oldPassword, @RequestParam String newPassword) {
        return userService.updateUserPassword(username, newPassword, oldPassword);
    }

    @PutMapping("update/info")
    public String updateInfo(@RequestBody User user) {
        return userService.updateUserInfo(user);
    }

    @DeleteMapping("/remove")
    public String remove(@RequestParam int id) {
        ArrayList<VideoFile> videoFiles = videoService.getVideoListByUser(id);
        if (videoFiles.size() > 0) {
            for (VideoFile videoFile : videoFiles) {
                videoService.removeVideo(videoFile.getVideoId());
            }
        }

        return userService.removeUser(id);
    }

    @PostMapping("/add")
    public String add(@RequestParam (value = "courses" )int[] courses,
                      @RequestParam String username, @RequestParam String password, @RequestParam String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        switch (role) {
            case "admin":
                user.setRoleId(1);
            case "teacher":
                user.setRoleId(2);
            case "student":
                user.setRoleId(3);
        }
        return userService.addUser(user, courses);
    }

}
