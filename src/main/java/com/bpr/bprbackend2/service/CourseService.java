package com.bpr.bprbackend2.service;


import com.bpr.bprbackend2.model.Course;

import java.util.ArrayList;

public interface CourseService {
    ArrayList<Course> getCourseByUser(String username);
}
