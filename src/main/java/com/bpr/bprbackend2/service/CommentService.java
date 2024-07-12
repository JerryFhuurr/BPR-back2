package com.bpr.bprbackend2.service;

import com.bpr.bprbackend2.model.Comment;

import java.util.ArrayList;

public interface CommentService {
    String addComment(Comment comment);
    ArrayList<Comment> getCommentList(int videoId);
    String removeComment(int commentId);
}
