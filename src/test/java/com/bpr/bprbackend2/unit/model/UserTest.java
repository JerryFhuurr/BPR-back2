package com.bpr.bprbackend2.unit.model;

import com.bpr.bprbackend2.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    private User userUnderTest;

    @BeforeEach
    void setUp() {
        userUnderTest = new User(0, 0, "username", "password", "email", "phone", LocalDate.of(2020, 1, 1), "role");
    }

    @Test
    void testUserIdGetterAndSetter() {
        final int userId = 0;
        userUnderTest.setUserId(userId);
        assertThat(userUnderTest.getUserId()).isEqualTo(userId);
    }

    @Test
    void testRoleIdGetterAndSetter() {
        final int roleId = 0;
        userUnderTest.setRoleId(roleId);
        assertThat(userUnderTest.getRoleId()).isEqualTo(roleId);
    }

    @Test
    void testUsernameGetterAndSetter() {
        final String username = "username";
        userUnderTest.setUsername(username);
        assertThat(userUnderTest.getUsername()).isEqualTo(username);
    }

    @Test
    void testPasswordGetterAndSetter() {
        final String password = "password";
        userUnderTest.setPassword(password);
        assertThat(userUnderTest.getPassword()).isEqualTo(password);
    }

    @Test
    void testEmailGetterAndSetter() {
        final String email = "email";
        userUnderTest.setEmail(email);
        assertThat(userUnderTest.getEmail()).isEqualTo(email);
    }

    @Test
    void testPhoneGetterAndSetter() {
        final String phone = "phone";
        userUnderTest.setPhone(phone);
        assertThat(userUnderTest.getPhone()).isEqualTo(phone);
    }

    @Test
    void testBirthGetterAndSetter() {
        final LocalDate birth = LocalDate.of(2020, 1, 1);
        userUnderTest.setBirth(birth);
        assertThat(userUnderTest.getBirth()).isEqualTo(birth);
    }

    @Test
    void testRoleGetterAndSetter() {
        final String role = "role";
        userUnderTest.setRole(role);
        assertThat(userUnderTest.getRole()).isEqualTo(role);
    }

    @Test
    void testEquals() {
        assertThat(userUnderTest.equals("o")).isFalse();
    }

    @Test
    void testToString() {
        assertThat(userUnderTest.toString()).isEqualTo("result");
    }

    @Test
    void testBuilder() {
        // Setup
        // Run the test
        final User.UserBuilder result = User.builder();

        // Verify the results
    }
}
