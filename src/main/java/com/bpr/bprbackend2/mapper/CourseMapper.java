package com.bpr.bprbackend2.mapper;

import com.bpr.bprbackend2.model.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface CourseMapper {
    ArrayList<Course> getCourseByUser(String username);
}
