package com.bpr.bprbackend2.controller;


import com.bpr.bprbackend2.model.Course;
import com.bpr.bprbackend2.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/get/user")
    public ArrayList<Course> getCourseByUser(@RequestParam String username) {
        return courseService.getCourseByUser(username);
    }

    @GetMapping("/get/all")
    public ArrayList<Course> getAllCourse() {
        return courseService.getCourseList();
    }
}
