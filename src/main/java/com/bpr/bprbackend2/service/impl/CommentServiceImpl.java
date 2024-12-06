package com.bpr.bprbackend2.service.impl;

import com.bpr.bprbackend2.mapper.CommentMapper;
import com.bpr.bprbackend2.mapper.UserMapper;
import com.bpr.bprbackend2.mapper.ResMapper;
import com.bpr.bprbackend2.model.Comment;
import com.bpr.bprbackend2.model.Resource;
import com.bpr.bprbackend2.service.interfaces.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ResMapper resMapper;
    @Autowired
    private UserMapper userMapper;


    @Override
    public String addComment(Comment comment) {
        int videoId = comment.getResId();
        Resource resource = resMapper.getRes(videoId);

        String senderName = userMapper.getUserInfoByUserId(comment.getSenderId()).getUsername();

        if (resource == null) {
            return "Cannot find video";
        }

        comment.setCommentTime(System.currentTimeMillis());
        ArrayList<Comment> comments = commentMapper.getCommentList(resource.getResId());
        for (Comment c : comments) {
            if (c.getSenderId() == comment.getSenderId()) {
                updateRemoveScore(1, comment.getCommentScore(), c.getCommentScore(), resource.getResId());
                commentMapper.updateComment(comment);
                return "Comment updated";
            }
        }
        comment.setUserId(resource.getUserId());
        comment.setCourseId(resource.getCourseId());
        comment.setRoleId(resource.getRoleId());
        comment.setSenderName(senderName);
        addVideoScore(comment.getCommentScore(), resource.getResId());
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
            updateRemoveScore(2, 0, comment.getCommentScore(), comment.getResId());
            commentMapper.removeComment(commentId);
            return "Comment removed";
        }
    }

    private void addVideoScore(float score, int videoId) {
        ArrayList<Comment> comments = commentMapper.getCommentList(videoId);
        Resource resource = resMapper.getRes(videoId);
        int commentSize = comments.size();
        float currentScore = resource.getResScore();
        float newScore = 0;
        newScore = (currentScore * commentSize + score) / (commentSize + 1);
        resource.setResScore(newScore);
        resMapper.updateResScore(resource);
    }

    private void updateRemoveScore(int type, float score, float oldScore, int videoId) {
        ArrayList<Comment> comments = commentMapper.getCommentList(videoId);
        Resource resource = resMapper.getRes(videoId);
        int commentSize = comments.size();
        float currentScore = resource.getResScore();
        float newScore = 0;
        switch (type) {
            case 1: // edit
                newScore = (currentScore * commentSize - oldScore + score) / (commentSize);
            case 2: // remove
                newScore = (currentScore * commentSize - oldScore) / (commentSize - 1);
        }
        resource.setResScore(newScore);
        resMapper.updateResScore(resource);
    }
}

