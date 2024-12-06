package com.bpr.bprbackend2.unit.impl;

import com.bpr.bprbackend2.mapper.CommentMapper;
import com.bpr.bprbackend2.mapper.ResMapper;
import com.bpr.bprbackend2.mapper.UserMapper;
import com.bpr.bprbackend2.model.Comment;
import com.bpr.bprbackend2.model.Resource;
import com.bpr.bprbackend2.model.User;
import com.bpr.bprbackend2.service.impl.CommentServiceImpl;
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
class CommentServiceImplTest {

    @Mock
    private CommentMapper mockCommentMapper;
    @Mock
    private ResMapper mockResMapper;
    @Mock
    private UserMapper mockUserMapper;

    @InjectMocks
    private CommentServiceImpl commentServiceImplUnderTest;

    @Test
    void testAddComment() {
        // Setup
        final Comment comment = Comment.builder()
                .userId(0)
                .courseId(0)
                .resId(0)
                .roleId(0)
                .senderId(0)
                .senderName("senderName")
                .commentTime(0L)
                .commentScore(0.0f)
                .build();

        // Configure ResMapper.getRes(...).
        final Resource resource = Resource.builder()
                .resId(0)
                .courseId(0)
                .userId(0)
                .roleId(0)
                .resScore(0.0f)
                .build();
        when(mockResMapper.getRes(0)).thenReturn(resource);

        // Configure UserMapper.getUserInfoByUserId(...).
        final User user = User.builder()
                .username("senderName")
                .build();
        when(mockUserMapper.getUserInfoByUserId(0)).thenReturn(user);

        // Configure CommentMapper.getCommentList(...).
        final ArrayList<Comment> comments = new ArrayList<>(List.of(Comment.builder()
                .userId(0)
                .courseId(0)
                .resId(0)
                .roleId(0)
                .senderId(0)
                .senderName("senderName")
                .commentTime(0L)
                .commentScore(0.0f)
                .build()));
        when(mockCommentMapper.getCommentList(0)).thenReturn(comments);

        // Run the test
        final String result = commentServiceImplUnderTest.addComment(comment);

        // Verify the results
        assertThat(result).isEqualTo("Comment updated");
    }

    @Test
    void testAddComment_ResMapperGetResReturnsNull() {
        // Setup
        final Comment comment = Comment.builder()
                .userId(0)
                .courseId(0)
                .resId(0)
                .roleId(0)
                .senderId(0)
                .senderName("senderName")
                .commentTime(0L)
                .commentScore(0.0f)
                .build();
        when(mockResMapper.getRes(0)).thenReturn(null);

        // Configure UserMapper.getUserInfoByUserId(...).
        final User user = User.builder()
                .username("senderName")
                .build();
        when(mockUserMapper.getUserInfoByUserId(0)).thenReturn(user);

        // Run the test
        final String result = commentServiceImplUnderTest.addComment(comment);

        // Verify the results
        assertThat(result).isEqualTo("Cannot find video");
    }

    @Test
    void testAddComment_CommentMapperGetCommentListReturnsNoItems() {
        // Setup
        final Comment comment = Comment.builder()
                .userId(0)
                .courseId(0)
                .resId(0)
                .roleId(0)
                .senderId(0)
                .senderName("senderName")
                .commentTime(0L)
                .commentScore(0.0f)
                .build();

        // Configure ResMapper.getRes(...).
        final Resource resource = Resource.builder()
                .resId(0)
                .courseId(0)
                .userId(0)
                .roleId(0)
                .resScore(0.0f)
                .build();
        when(mockResMapper.getRes(0)).thenReturn(resource);

        // Configure UserMapper.getUserInfoByUserId(...).
        final User user = User.builder()
                .username("senderName")
                .build();
        when(mockUserMapper.getUserInfoByUserId(0)).thenReturn(user);

        when(mockCommentMapper.getCommentList(0)).thenReturn(new ArrayList<>());

        // Run the test
        final String result = commentServiceImplUnderTest.addComment(comment);

        // Verify the results
        assertThat(result).isEqualTo("Comment added");
    }

    @Test
    void testGetCommentList() {
        // Setup
        final ArrayList<Comment> expectedResult = new ArrayList<>(List.of(Comment.builder()
                .userId(0)
                .courseId(0)
                .resId(0)
                .roleId(0)
                .senderId(0)
                .senderName("senderName")
                .commentTime(0L)
                .commentScore(0.0f)
                .build()));

        // Configure CommentMapper.getCommentList(...).
        final ArrayList<Comment> comments = new ArrayList<>(List.of(Comment.builder()
                .userId(0)
                .courseId(0)
                .resId(0)
                .roleId(0)
                .senderId(0)
                .senderName("senderName")
                .commentTime(0L)
                .commentScore(0.0f)
                .build()));
        when(mockCommentMapper.getCommentList(0)).thenReturn(comments);

        // Run the test
        final ArrayList<Comment> result = commentServiceImplUnderTest.getCommentList(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetCommentList_CommentMapperReturnsNoItems() {
        // Setup
        when(mockCommentMapper.getCommentList(0)).thenReturn(new ArrayList<>());

        // Run the test
        final ArrayList<Comment> result = commentServiceImplUnderTest.getCommentList(0);

        // Verify the results
        assertThat(result).isEqualTo(new ArrayList<>());
    }

    @Test
    void testRemoveComment() {
        // Setup
        // Configure CommentMapper.getAComment(...).
        final Comment comment = Comment.builder()
                .userId(0)
                .courseId(0)
                .resId(0)
                .roleId(0)
                .senderId(0)
                .senderName("senderName")
                .commentTime(0L)
                .commentScore(0.0f)
                .build();
        when(mockCommentMapper.getAComment(0)).thenReturn(comment);

        // Configure CommentMapper.getCommentList(...).
        final ArrayList<Comment> comments = new ArrayList<>(List.of(Comment.builder()
                .userId(0)
                .courseId(0)
                .resId(0)
                .roleId(0)
                .senderId(0)
                .senderName("senderName")
                .commentTime(0L)
                .commentScore(0.0f)
                .build()));
        when(mockCommentMapper.getCommentList(0)).thenReturn(comments);

        // Configure ResMapper.getRes(...).
        final Resource resource = Resource.builder()
                .resId(0)
                .courseId(0)
                .userId(0)
                .roleId(0)
                .resScore(0.0f)
                .build();
        when(mockResMapper.getRes(0)).thenReturn(resource);

        // Run the test
        final String result = commentServiceImplUnderTest.removeComment(0);

        // Verify the results
        assertThat(result).isEqualTo("Comment removed");
        //verify(mockResMapper).updateResScore(Resource.builder()
        //        .resId(0)
        //        .courseId(0)
        //        .userId(0)
        //        .roleId(0)
        //        .resScore(0.0f)
        //        .build());
        //verify(mockCommentMapper).removeComment(0);
    }

    @Test
    void testRemoveComment_CommentMapperGetACommentReturnsNull() {
        // Setup
        when(mockCommentMapper.getAComment(0)).thenReturn(null);

        // Run the test
        final String result = commentServiceImplUnderTest.removeComment(0);

        // Verify the results
        assertThat(result).isEqualTo("Cannot find comment");
    }

    @Test
    void testRemoveComment_CommentMapperGetCommentListReturnsNoItems() {
        // Setup
        // Configure CommentMapper.getAComment(...).
        final Comment comment = Comment.builder()
                .userId(0)
                .courseId(0)
                .resId(0)
                .roleId(0)
                .senderId(0)
                .senderName("senderName")
                .commentTime(0L)
                .commentScore(0.0f)
                .build();
        when(mockCommentMapper.getAComment(0)).thenReturn(comment);

        when(mockCommentMapper.getCommentList(0)).thenReturn(new ArrayList<>());

        // Configure ResMapper.getRes(...).
        final Resource resource = Resource.builder()
                .resId(0)
                .courseId(0)
                .userId(0)
                .roleId(0)
                .resScore(0.0f)
                .build();
        when(mockResMapper.getRes(0)).thenReturn(resource);

        // Run the test
        final String result = commentServiceImplUnderTest.removeComment(0);

        // Verify the results
        assertThat(result).isEqualTo("Comment removed");
        //verify(mockResMapper).updateResScore(Resource.builder()
        //        .resId(0)
        //        .courseId(0)
        //        .userId(0)
        //        .roleId(0)
        //        .resScore(0.0f)
        //        .build());
        //verify(mockCommentMapper).removeComment(0);
    }
}
