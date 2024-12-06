package com.bpr.bprbackend2.unit.model;

import com.bpr.bprbackend2.model.UserTestParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTestParamTest {

    private UserTestParam userTestParamUnderTest;

    @BeforeEach
    void setUp() {
        userTestParamUnderTest = new UserTestParam();
    }

    @Test
    void testCoursesGetterAndSetter() {
        final int[] courses = new int[]{0};
        userTestParamUnderTest.setCourses(courses);
        assertThat(userTestParamUnderTest.getCourses()).isEqualTo(courses);
    }

    @Test
    void testUsernameGetterAndSetter() {
        final String username = "username";
        userTestParamUnderTest.setUsername(username);
        assertThat(userTestParamUnderTest.getUsername()).isEqualTo(username);
    }

    @Test
    void testPasswordGetterAndSetter() {
        final String password = "password";
        userTestParamUnderTest.setPassword(password);
        assertThat(userTestParamUnderTest.getPassword()).isEqualTo(password);
    }

    @Test
    void testRoleGetterAndSetter() {
        final String role = "role";
        userTestParamUnderTest.setRole(role);
        assertThat(userTestParamUnderTest.getRole()).isEqualTo(role);
    }

    @Test
    void testEquals() {
        assertThat(userTestParamUnderTest.equals("o")).isFalse();
    }

    @Test
    void testToString() {
        assertThat(userTestParamUnderTest.toString()).isEqualTo("result");
    }
}
