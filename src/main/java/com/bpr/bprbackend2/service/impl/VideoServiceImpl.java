package com.bpr.bprbackend2.service.impl;

import com.bpr.bprbackend2.mapper.VideoMapper;
import com.bpr.bprbackend2.model.VideoFile;
import com.bpr.bprbackend2.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;


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
}
