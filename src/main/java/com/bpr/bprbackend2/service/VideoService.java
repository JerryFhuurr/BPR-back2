package com.bpr.bprbackend2.service;

import com.bpr.bprbackend2.model.VideoFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface VideoService {
    ArrayList<VideoFile> getVideoList(int courseId);
    ArrayList<VideoFile> getVideoListByUser(int userId);
    VideoFile getVideo(int videoId);
    String saveVideo(VideoFile video, MultipartFile[] files);
    String removeVideo(int videoId);
    String updateVideo(int videoId, int userID, String title, String description);
    String getVideoPathByName(String videoFileName);
    String getFilePathByName(String fileName);
}
