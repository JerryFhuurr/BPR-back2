package com.bpr.bprbackend2.service.impl;

import cn.hutool.core.date.DateTime;
import com.bpr.bprbackend2.mapper.CommentMapper;
import com.bpr.bprbackend2.mapper.UserMapper;
import com.bpr.bprbackend2.mapper.VideoMapper;
import com.bpr.bprbackend2.model.Comment;
import com.bpr.bprbackend2.model.VideoFile;
import com.bpr.bprbackend2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private UserMapper userMapper;


    @Override
    public String addComment(Comment comment) {
                int videoId = comment.getVideoId();
        VideoFile videoFile = videoMapper.getVideo(videoId);

        String senderName = userMapper.getUserInfoByUserId(comment.getSenderId()).getUsername();

        if (videoFile == null) {
            return "Cannot find video";
        }

        comment.setCommentTime(System.currentTimeMillis());
        ArrayList<Comment> comments = commentMapper.getCommentList(videoFile.getVideoId());
        for (Comment c : comments) {
            if (c.getSenderId() == comment.getSenderId()) {
                commentMapper.updateComment(comment);
                return "Comment updated";
            }
        }
        comment.setUserId(videoFile.getUserId());
        comment.setCourseId(videoFile.getCourseId());
        comment.setRoleId(videoFile.getRoleId());
        comment.setSenderName(senderName);

        commentMapper.addComment(comment);
        return "Comment added";
    }

    @Override
    public ArrayList<Comment> getCommentList(int videoId) {
        return commentMapper.getCommentList(videoId);
    }

    @Override
    public String removeComment(int commentId) {
        Comment comment = commentMapper.getAComment(commentId);
        if (comment == null) {
            return "Cannot find comment";
        } else {
            commentMapper.removeComment(commentId);
            return "Comment removed";
        }
    }
}
