package com.bpr.bprbackend2.unit.model;

import com.bpr.bprbackend2.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {

    private Course courseUnderTest;

    @BeforeEach
    void setUp() {
        courseUnderTest = new Course(0, "courseName");
    }

    @Test
    void testCourseIdGetterAndSetter() {
        final int courseId = 0;
        courseUnderTest.setCourseId(courseId);
        assertThat(courseUnderTest.getCourseId()).isEqualTo(courseId);
    }

    @Test
    void testCourseNameGetterAndSetter() {
        final String courseName = "courseName";
        courseUnderTest.setCourseName(courseName);
        assertThat(courseUnderTest.getCourseName()).isEqualTo(courseName);
    }

    @Test
    void testEquals() {
        assertThat(courseUnderTest.equals("o")).isFalse();
    }

    @Test
    void testToString() {
        assertThat(courseUnderTest.toString()).isEqualTo("result");
    }

    @Test
    void testBuilder() {
        // Setup
        // Run the test
        final Course.CourseBuilder result = Course.builder();

        // Verify the results
    }
}
