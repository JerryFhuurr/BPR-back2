package com.bpr.bprbackend2.service.impl;

import com.bpr.bprbackend2.hanlders.UriHandler;
import com.bpr.bprbackend2.mapper.CommentMapper;
import com.bpr.bprbackend2.mapper.UserMapper;
import com.bpr.bprbackend2.mapper.VideoMapper;
import com.bpr.bprbackend2.model.Resource;
import com.bpr.bprbackend2.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Value("${upload.dir}")
    private String uploadDir;


    @Override
    public ArrayList<Resource> getResList(int courseId) {
        return videoMapper.getVideoList(courseId);
    }

    @Override
    public ArrayList<Resource> getResListByUser(int userId) {
        return videoMapper.getVideoListByUser(userId);
    }

    @Override
    public Resource getRes(int resId) {
        return videoMapper.getVideo(resId);
    }

    @Override
    public String saveRes(Resource video, MultipartFile[] files) {
        if (video.getRoleId() == 3) {
            return "Can only upload files by teacher or admin";
        } else {
            try {
                if (files != null && (files.length > 0 && files.length <= 2)) {
                    for (MultipartFile file : files) {
                        String fileName = file.getOriginalFilename();
                        if (fileName != null && (fileName.endsWith(".mp4") || fileName.endsWith(".avi"))) {
                            fileName = fileName.substring(0, fileName.length() - 4);
                            String fileNameN = fileName + video.getUserId() + System.currentTimeMillis() + ".mp4";
                            Path filePath = Paths.get(uploadDir, fileNameN);
                            Files.copy(file.getInputStream(), filePath);
                            video.setVideoPath(filePath.toString());
                            video.setVideoFileName("Video_" + fileName);
                            video.setVideoFileDownload(UriHandler.remoteBaseUri + "/video/downloadLocal?fileName=" + "Video_" + fileName);
                            video.setVideoSize(file.getSize());
                        } else {
                            String fileNameN = video.getUserId() + System.currentTimeMillis() +fileName;
                            Path filePath = Paths.get(uploadDir, fileNameN);
                            Files.copy(file.getInputStream(), filePath);
                            video.setFileUrl(filePath.toString());
                            video.setFileName("File_" + fileName);
                            video.setFileNameDownload(UriHandler.remoteBaseUri + "/video/downloadLocal?fileName=" + "File_" + fileName);
                            video.setFileSize(file.getSize());
                        }
                    }
                    videoMapper.addVideo(video);
                    return "Uploaded successfully";
                } else {
                    return "No file provided or too much files provided (Cannot more than 2 files)";
                }
            } catch (IOException e) {
                return "Something went wrong " + e.getMessage();
            }
        }
    }

    @Override
    public String removeRes(int resId) {
        Resource video = videoMapper.getVideo(resId);
        File file = new File(video.getVideoPath());
        if (video.getFileUrl() != null) {
            File file2 = new File(video.getFileUrl());
            file2.delete();
        }
        if (file.exists()) {
            file.delete();
            commentMapper.removeCommentByVideo(resId);
            videoMapper.removeVideo(resId);
            return "Deleted Successfully";
        } else {
            return "Cannot find the video file";
        }
    }

    @Override
    public String updateRes(int resId, int userID, String title, String description) {
        Resource oldVideo = videoMapper.getVideo(resId);
        if (oldVideo.getUserId() == userID || userMapper.getUserRoleById(userID).equals("admin")) {
            oldVideo.setResTitle(title);
            oldVideo.setResDescription(description);
            videoMapper.updateVideoInfo(oldVideo);
            return "Updated Successfully";
        } else {
            return "Wrong account, can edit by uploader or admin";
        }
    }

    @Override
    public String getVideoPathByName(String videoFileName) {
        Resource vf = videoMapper.getVideoPathByName(videoFileName);
        return vf.getVideoPath();
    }

    @Override
    public String getFilePathByName(String fileName) {
        Resource vf = videoMapper.getFilePathByName(fileName);
        return vf.getFileUrl();
    }


}
