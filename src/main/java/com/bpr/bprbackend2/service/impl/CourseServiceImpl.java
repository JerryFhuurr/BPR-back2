package com.bpr.bprbackend2.service.impl;

import com.bpr.bprbackend2.mapper.CourseMapper;
import com.bpr.bprbackend2.model.Course;
import com.bpr.bprbackend2.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public ArrayList<Course> getCourseByUser(String username) {
        return courseMapper.getCourseByUser(username);
    }

    @Override
    public ArrayList<Course> getCourseList() {
        return courseMapper.getCourseList();
    }
}
