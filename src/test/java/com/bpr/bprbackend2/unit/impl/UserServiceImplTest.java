package com.bpr.bprbackend2.unit.impl;

import com.bpr.bprbackend2.mapper.CommentMapper;
import com.bpr.bprbackend2.mapper.HistoryMapper;
import com.bpr.bprbackend2.mapper.UserMapper;
import com.bpr.bprbackend2.model.Comment;
import com.bpr.bprbackend2.model.User;
import com.bpr.bprbackend2.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserMapper mockMapper;
    @Mock
    private CommentMapper mockCommentMapper;
    @Mock
    private HistoryMapper mockHistoryMapper;

    @InjectMocks
    private UserServiceImpl userServiceImplUnderTest;

    @Test
    void testLoginGet_UserMapperReturnsNull() {
        // Setup
        when(mockMapper.loginGet("username")).thenReturn(null);

        // Run the test
        final String result = userServiceImplUnderTest.loginGet("username", "password");

        // Verify the results
        assertThat(result).isEqualTo("Login failed - username is incorrect");
    }

    @Test
    void testGetUserInfo() {
        // Setup
        final User expectedResult = User.builder()
                .userId(0)
                .username("username")
                .password("password")
                .role("role")
                .build();
        when(mockMapper.getUserRole("username")).thenReturn("role");

        // Configure UserMapper.getUserInfo(...).
        final User user = User.builder()
                .userId(0)
                .username("username")
                .password("password")
                .role("role")
                .build();
        when(mockMapper.getUserInfo("username")).thenReturn(user);

        // Run the test
        final User result = userServiceImplUnderTest.getUserInfo("username");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdateUserInfo() {
        // Setup
        final User user = User.builder()
                .userId(0)
                .username("username")
                .password("password")
                .role("role")
                .build();

        // Configure UserMapper.getUserInfoByUserId(...).
        final User user1 = User.builder()
                .userId(0)
                .username("username")
                .password("password")
                .role("role")
                .build();
        when(mockMapper.getUserInfoByUserId(0)).thenReturn(user1);

        //when(mockMapper.getUsernames()).thenReturn(new ArrayList<>(List.of("value")));

        // Run the test
        final String result = userServiceImplUnderTest.updateUserInfo(user);

        // Verify the results
        assertThat(result).isEqualTo("User updated successfully");
        verify(mockMapper).updateUserInfo(User.builder()
                .userId(0)
                .username("username")
                .password("password")
                .role("role")
                .build());
    }

    @Test
    void testUpdateUserInfo_UserMapperGetUsernamesReturnsNoItems() {
        // Setup
        final User user = User.builder()
                .userId(0)
                .username("username")
                .password("password")
                .role("role")
                .build();

        // Configure UserMapper.getUserInfoByUserId(...).
        final User user1 = User.builder()
                .userId(0)
                .username("username")
                .password("password")
                .role("role")
                .build();
        when(mockMapper.getUserInfoByUserId(0)).thenReturn(user1);

        //when(mockMapper.getUsernames()).thenReturn(new ArrayList<>());

        // Run the test
        final String result = userServiceImplUnderTest.updateUserInfo(user);

        // Verify the results
        assertThat(result).isEqualTo("User updated successfully");
        verify(mockMapper).updateUserInfo(User.builder()
                .userId(0)
                .username("username")
                .password("password")
                .role("role")
                .build());
    }

    @Test
    void testGetAll() {
        // Setup
        final ArrayList<User> expectedResult = new ArrayList<>(List.of(User.builder()
                .userId(0)
                .username("username")
                .password("password")
                .role("role")
                .build()));

        // Configure UserMapper.getAll(...).
        final ArrayList<User> users = new ArrayList<>(List.of(User.builder()
                .userId(0)
                .username("username")
                .password("password")
                .role("role")
                .build()));
        when(mockMapper.getAll()).thenReturn(users);

        // Run the test
        final ArrayList<User> result = userServiceImplUnderTest.getAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAll_UserMapperReturnsNoItems() {
        // Setup
        when(mockMapper.getAll()).thenReturn(new ArrayList<>());

        // Run the test
        final ArrayList<User> result = userServiceImplUnderTest.getAll();

        // Verify the results
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @Test
    void testRemoveUser() {
        // Setup
        // Configure CommentMapper.getCommentListByUser(...).
        final ArrayList<Comment> comments = new ArrayList<>(List.of(Comment.builder()
                .commentId(0)
                .build()));
        when(mockCommentMapper.getCommentListByUser(0)).thenReturn(comments);

        // Run the test
        final String result = userServiceImplUnderTest.removeUser(0);

        // Verify the results
        assertThat(result).isEqualTo("User successfully removed");
        verify(mockHistoryMapper).removeHistoryByUser(0);
        verify(mockCommentMapper).removeComment(0);
        verify(mockMapper).removeUserCourse(0);
        verify(mockMapper).removeUser(0);
    }

    @Test
    void testRemoveUser_CommentMapperGetCommentListByUserReturnsNoItems() {
        // Setup
        when(mockCommentMapper.getCommentListByUser(0)).thenReturn(new ArrayList<>());

        // Run the test
        final String result = userServiceImplUnderTest.removeUser(0);

        // Verify the results
        assertThat(result).isEqualTo("User successfully removed");
        verify(mockHistoryMapper).removeHistoryByUser(0);
        verify(mockMapper).removeUserCourse(0);
        verify(mockMapper).removeUser(0);
    }

    @Test
    void testAddUser() {
        // Setup
        final User user = User.builder()
                .userId(0)
                .username("username")
                .password("password")
                .role("role")
                .build();

        // Configure UserMapper.getAll(...).
        final ArrayList<User> users = new ArrayList<>(List.of(User.builder()
                .userId(0)
                .username("username")
                .password("password")
                .role("role")
                .build()));
        when(mockMapper.getAll()).thenReturn(users);

        // Configure UserMapper.getUserInfo(...).
        final User user1 = User.builder()
                .userId(0)
                .username("username")
                .password("password")
                .role("role")
                .build();
        //when(mockMapper.getUserInfo("username")).thenReturn(user1);

        //when(mockMapper.getUserRoleById2(0)).thenReturn(0);

        // Run the test
        final String result = userServiceImplUnderTest.addUser(user, new int[]{0});

        // Verify the results
        assertThat(result).isEqualTo("Username already in use");
        //verify(mockMapper).addUser(User.builder()
        //        .userId(0)
        //        .username("username")
        //        .password("password")
        //        .role("role")
        //        .build());
        //verify(mockMapper).addUserCourse(0, 0, 0);
    }

    @Test
    void testAddUser_UserMapperGetAllReturnsNoItems() {
        // Setup
        final User user = User.builder()
                .userId(0)
                .username("username")
                .password("password")
                .role("role")
                .build();
        when(mockMapper.getAll()).thenReturn(new ArrayList<>());

        // Configure UserMapper.getUserInfo(...).
        final User user1 = User.builder()
                .userId(0)
                .username("username")
                .password("password")
                .role("role")
                .build();
        when(mockMapper.getUserInfo("username")).thenReturn(user1);

        when(mockMapper.getUserRoleById2(0)).thenReturn(0);

        // Run the test
        final String result = userServiceImplUnderTest.addUser(user, new int[]{0});

        // Verify the results
        assertThat(result).isEqualTo("User successfully added");
        //verify(mockMapper).addUser(User.builder()
        //        .userId(0)
        //        .username("username")
        //        .password("password")
        //        .role("role")
        //        .build());
        //verify(mockMapper).addUserCourse(0, 0, 0);
    }

    @Test
    void testUpdatePasswordAdmin() {
        // Setup
        // Run the test
        final String result = userServiceImplUnderTest.updatePasswordAdmin("username", "newPassword");

        // Verify the results
        assertThat(result).isEqualTo("Password updated successfully");
        //verify(mockMapper).updateUserPassword("username", "password");
    }
}
