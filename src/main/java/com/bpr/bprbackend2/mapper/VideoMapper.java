package com.bpr.bprbackend2.mapper;

import com.bpr.bprbackend2.model.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface VideoMapper {
    ArrayList<Resource> getVideoList(int courseId);
    ArrayList<Resource> getVideoListByUser(int userId);
    Resource getVideo(int videoId);
    void addVideo(Resource resource);
    void removeVideo(int videoId);
    void updateVideoInfo(Resource resource);
    Resource getVideoPathByName(String videoFileName);
    Resource getFilePathByName(String fileName);
    void updateVideoScore(Resource resource);
    void updateUploaderToET(Resource resource);
}
