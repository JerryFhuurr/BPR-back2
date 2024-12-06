package com.bpr.bprbackend2.unit.model;

import com.bpr.bprbackend2.model.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class CommentTest {

    private Comment commentUnderTest;

    @BeforeEach
    void setUp() {
        commentUnderTest = new Comment(0, 0, 0, 0, 0, 0, "senderName", "commentText", 0L, 0.0f);
    }

    @Test
    void testCommentIdGetterAndSetter() {
        final int commentId = 0;
        commentUnderTest.setCommentId(commentId);
        assertThat(commentUnderTest.getCommentId()).isEqualTo(commentId);
    }

    @Test
    void testUserIdGetterAndSetter() {
        final int userId = 0;
        commentUnderTest.setUserId(userId);
        assertThat(commentUnderTest.getUserId()).isEqualTo(userId);
    }

    @Test
    void testCourseIdGetterAndSetter() {
        final int courseId = 0;
        commentUnderTest.setCourseId(courseId);
        assertThat(commentUnderTest.getCourseId()).isEqualTo(courseId);
    }

    @Test
    void testResIdGetterAndSetter() {
        final int resId = 0;
        commentUnderTest.setResId(resId);
        assertThat(commentUnderTest.getResId()).isEqualTo(resId);
    }

    @Test
    void testRoleIdGetterAndSetter() {
        final int roleId = 0;
        commentUnderTest.setRoleId(roleId);
        assertThat(commentUnderTest.getRoleId()).isEqualTo(roleId);
    }

    @Test
    void testSenderIdGetterAndSetter() {
        final int senderId = 0;
        commentUnderTest.setSenderId(senderId);
        assertThat(commentUnderTest.getSenderId()).isEqualTo(senderId);
    }

    @Test
    void testSenderNameGetterAndSetter() {
        final String senderName = "senderName";
        commentUnderTest.setSenderName(senderName);
        assertThat(commentUnderTest.getSenderName()).isEqualTo(senderName);
    }

    @Test
    void testCommentTextGetterAndSetter() {
        final String commentText = "commentText";
        commentUnderTest.setCommentText(commentText);
        assertThat(commentUnderTest.getCommentText()).isEqualTo(commentText);
    }

    @Test
    void testCommentTimeGetterAndSetter() {
        final long commentTime = 0L;
        commentUnderTest.setCommentTime(commentTime);
        assertThat(commentUnderTest.getCommentTime()).isEqualTo(commentTime);
    }

    @Test
    void testCommentScoreGetterAndSetter() {
        final float commentScore = 0.0f;
        commentUnderTest.setCommentScore(commentScore);
        assertThat(commentUnderTest.getCommentScore()).isEqualTo(commentScore, within(0.0001f));
    }

    @Test
    void testEquals() {
        assertThat(commentUnderTest.equals("o")).isFalse();
    }


    @Test
    void testToString() {
        assertThat(commentUnderTest.toString()).isEqualTo("Comment(commentId=0, userId=0, courseId=0, resId=0, roleId=0, senderId=0, senderName=senderName, commentText=commentText, commentTime=0, commentScore=0.0)");
    }

    @Test
    void testBuilder() {
        // Setup
        // Run the test
        final Comment.CommentBuilder result = Comment.builder();

        // Verify the results
    }
}
