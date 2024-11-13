package com.bpr.bprbackend2.service;

import com.bpr.bprbackend2.model.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface VideoService {
    ArrayList<Resource> getResList(int courseId);
    ArrayList<Resource> getResListByUser(int userId);
    Resource getRes(int resId);
    String saveRes(Resource video, MultipartFile[] files);
    String removeRes(int resId);
    String updateRes(int resId, int userID, String title, String description);
    String getVideoPathByName(String videoFileName);
    String getFilePathByName(String fileName);
}
