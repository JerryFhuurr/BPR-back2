package com.bpr.bprbackend2.unit.impl;

import com.bpr.bprbackend2.mapper.CourseMapper;
import com.bpr.bprbackend2.model.Course;
import com.bpr.bprbackend2.service.impl.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseMapper mockCourseMapper;

    @InjectMocks
    private CourseServiceImpl courseServiceImplUnderTest;

    @Test
    void testGetCourseByUser() {
        // Setup
        final ArrayList<Course> expectedResult = new ArrayList<>(List.of(Course.builder().build()));

        // Configure CourseMapper.getCourseByUser(...).
        final ArrayList<Course> courses = new ArrayList<>(List.of(Course.builder().build()));
        when(mockCourseMapper.getCourseByUser("username")).thenReturn(courses);

        // Run the test
        final ArrayList<Course> result = courseServiceImplUnderTest.getCourseByUser("username");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetCourseByUser_CourseMapperReturnsNoItems() {
        // Setup
        when(mockCourseMapper.getCourseByUser("username")).thenReturn(new ArrayList<>());

        // Run the test
        final ArrayList<Course> result = courseServiceImplUnderTest.getCourseByUser("username");

        // Verify the results
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @Test
    void testGetCourseList() {
        // Setup
        final ArrayList<Course> expectedResult = new ArrayList<>(List.of(Course.builder().build()));

        // Configure CourseMapper.getCourseList(...).
        final ArrayList<Course> courses = new ArrayList<>(List.of(Course.builder().build()));
        when(mockCourseMapper.getCourseList()).thenReturn(courses);

        // Run the test
        final ArrayList<Course> result = courseServiceImplUnderTest.getCourseList();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetCourseList_CourseMapperReturnsNoItems() {
        // Setup
        when(mockCourseMapper.getCourseList()).thenReturn(new ArrayList<>());

        // Run the test
        final ArrayList<Course> result = courseServiceImplUnderTest.getCourseList();

        // Verify the results
        assertThat(result).isEqualTo(new ArrayList<>());
    }
}
