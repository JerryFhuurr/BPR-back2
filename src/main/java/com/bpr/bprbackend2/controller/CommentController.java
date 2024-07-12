package com.bpr.bprbackend2.controller;

import com.bpr.bprbackend2.model.Comment;
import com.bpr.bprbackend2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public String addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @GetMapping("/get/all")
    public ArrayList<Comment> getAllComments(@RequestParam int videoId) {
        return commentService.getCommentList(videoId);
    }

    @DeleteMapping("/remove")
    public String removeComment(@RequestParam int commentId) {
        return commentService.removeComment(commentId);
    }
}
