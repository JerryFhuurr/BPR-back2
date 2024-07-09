package com.bpr.bprbackend2.controller;

import com.bpr.bprbackend2.model.VideoFile;
import com.bpr.bprbackend2.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/get/list")
    public ArrayList<VideoFile> getList(@RequestParam int courseId) {
        return videoService.getVideoList(courseId);
    }

    @GetMapping("/get")
    public VideoFile getVideo(@RequestParam int videoId) {
        return videoService.getVideo(videoId);
    }

    @GetMapping("get/list/user")
    public ArrayList<VideoFile> getListUser(@RequestParam int userId) {
        return videoService.getVideoListByUser(userId);
    }
}
