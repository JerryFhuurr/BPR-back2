package com.bpr.bprbackend2.controller;

import com.bpr.bprbackend2.model.VideoFile;
import com.bpr.bprbackend2.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/upload/")
    public String uploadVideo(@RequestParam("file") MultipartFile file,
                              @RequestParam("courseId") int courseId,
                              @RequestParam("userId") int userId,
                              @RequestParam("roleId") int roleId,
                              @RequestParam("videoTitle") String videoTitle,
                              @RequestParam("videoDescription") String videoDescription) {

            VideoFile video = new VideoFile();
            video.setCourseId(courseId);
            video.setUserId(userId);
            video.setRoleId(roleId);
            video.setVideoTitle(videoTitle);
            video.setVideoDescription(videoDescription);
            return videoService.saveVideo(video, file);

    }

    @DeleteMapping("/remove")
    public String removeVideo(@RequestParam int videoId) {
        return videoService.removeVideo(videoId);
    }

    @PutMapping("/update/info")
    public String updateVideoInfo(@RequestParam int videoId, @RequestParam int userId,  @RequestParam String videoTitle, @RequestParam String videoDescription) {
        return videoService.updateVideo(videoId,userId, videoTitle, videoDescription);
    }
}
