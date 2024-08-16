package com.bpr.bprbackend2.mapper;

import com.bpr.bprbackend2.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface CommentMapper {
    void addComment(Comment comment);
    ArrayList<Comment> getCommentList(int videoId);
    ArrayList<Comment> getCommentListByUser(int userId);
    void removeComment(int commentId);
    Comment getAComment(int commentId);
    void updateComment(Comment comment);
    void removeCommentByVideo(int videoId);
}
