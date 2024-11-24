package com.bpr.bprbackend2.controller;

import com.bpr.bprbackend2.model.User;
import com.bpr.bprbackend2.model.UserTestParam;
import com.bpr.bprbackend2.model.Resource;
import com.bpr.bprbackend2.service.UserService;
import com.bpr.bprbackend2.service.ResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ResService resService;

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
        ArrayList<Resource> resources = resService.getResListByUser(id);
        if (resources.size() > 0) {
            for (Resource resource : resources) {
                resService.removeRes(resource.getResId());
            }
        }

        return userService.removeUser(id);
    }

    @PostMapping("/add")
    public String add(@RequestBody UserTestParam userTestParam) {
        System.out.println(userTestParam);
        User user = new User();
        user.setUsername(userTestParam.getUsername());
        user.setPassword(userTestParam.getPassword());
        user.setRole(userTestParam.getRole());

        if (userTestParam.getRole().equals("admin")) {
            user.setRoleId(1);
            System.out.println("role set admin");
        } else if (userTestParam.getRole().equals("teacher")) {
            user.setRoleId(2);
            System.out.println("role set teacher");
        } else if (userTestParam.getRole().equals("student")) {
            user.setRoleId(3);
            System.out.println("role set student");
        } else {
            return "Please select a role";
        }

        return userService.addUser(user, userTestParam.getCourses());
    }

    @GetMapping("/get/all")
    public ArrayList<User> getAll() {
        return userService.getAll();
    }

    @PutMapping("/update/password/admin")
    public String updatePasswordAdmin(@RequestParam String username, @RequestParam String password) {
        return userService.updatePasswordAdmin(username, password);
    }
}
