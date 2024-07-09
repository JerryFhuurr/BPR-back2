package com.bpr.bprbackend2.mapper;

import com.bpr.bprbackend2.model.VideoFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface VideoMapper {
    ArrayList<VideoFile> getVideoList(int courseId);
    ArrayList<VideoFile> getVideoListByUser(int userId);
    VideoFile getVideo(int videoId);
    void addVideo(VideoFile videoFile);
    void removeVideo(int videoId);
}
