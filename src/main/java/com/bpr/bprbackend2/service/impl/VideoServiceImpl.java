package com.bpr.bprbackend2.service.impl;

import com.bpr.bprbackend2.mapper.VideoMapper;
import com.bpr.bprbackend2.model.VideoFile;
import com.bpr.bprbackend2.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;
    @Value("${upload.dir}")
    private String uploadDir;


    @Override
    public ArrayList<VideoFile> getVideoList(int courseId) {
        return videoMapper.getVideoList(courseId);
    }

    @Override
    public ArrayList<VideoFile> getVideoListByUser(int userId) {
        return videoMapper.getVideoListByUser(userId);
    }

    @Override
    public VideoFile getVideo(int videoId) {
        return videoMapper.getVideo(videoId);
    }

    @Override
    public String saveVideo(VideoFile video, MultipartFile file) {
        if (video.getRoleId() == 3) {
            return "Can only upload files by teacher or admin";
        } else {
            try {
                String fileName = file.getOriginalFilename();
                if (fileName != null && fileName.endsWith(".mp4")) {
                    fileName = fileName.substring(0, fileName.length() - 4);
                }
                String fileNameN = fileName + video.getUserId() + System.currentTimeMillis() + ".mp4";
                Path filePath = Paths.get(uploadDir, fileNameN);
                Files.copy(file.getInputStream(), filePath);
                video.setVideoPath(filePath.toString());
                videoMapper.addVideo(video);
                return "Uploaded successfully";
            } catch (IOException e) {
                return "Something went wrong " + e.getMessage();
            }
        }
    }


}
