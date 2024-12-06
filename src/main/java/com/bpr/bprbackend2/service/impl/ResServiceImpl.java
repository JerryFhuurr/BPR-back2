package com.bpr.bprbackend2.service.impl;

import com.bpr.bprbackend2.hanlders.UriHandler;
import com.bpr.bprbackend2.mapper.CommentMapper;
import com.bpr.bprbackend2.mapper.UserMapper;
import com.bpr.bprbackend2.mapper.ResMapper;
import com.bpr.bprbackend2.model.Resource;
import com.bpr.bprbackend2.service.interfaces.ResService;
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
public class ResServiceImpl implements ResService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ResMapper resMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Value("${upload.dir}")
    private String uploadDir;


    @Override
    public ArrayList<Resource> getResList(int courseId) {
        return resMapper.getResList(courseId);
    }

    @Override
    public ArrayList<Resource> getResListByUser(int userId) {
        return resMapper.getResListByUser(userId);
    }

    @Override
    public Resource getRes(int resId) {
        return resMapper.getRes(resId);
    }

    @Override
    public String saveRes(Resource resource, MultipartFile[] files) {
        if (resource.getRoleId() == 3) {
            return "Can only upload files by teacher or admin";
        } else {
            try {
                if (files != null && (files.length > 0 && files.length <= 2)) {
                    for (MultipartFile file : files) {
                        String fileName = file.getOriginalFilename();
                        String fileNameN = resource.getUserId() + System.currentTimeMillis() + fileName;
                        Path filePath = Paths.get(uploadDir, fileNameN);
                        Files.copy(file.getInputStream(), filePath);
                        resource.setFileUrl(filePath.toString());
                        resource.setFileName("File_" + fileName);
                        resource.setFileNameDownload(UriHandler.remoteBaseUri + "/res/downloadLocal?fileName=" + "File_" + fileName);
                        resource.setFileSize(file.getSize());
                    }
                    resMapper.addRes(resource);
                    return "Uploaded successfully";
                } else {
                    return "No file provided or too much files provided (Cannot more than 2 files)";
                }
            } catch (IOException e) {
                return "E: " + e.toString();
            }
        }
    }

    @Override
    public String removeRes(int resId) {
        Resource resource = resMapper.getRes(resId);
        try {
            if (resource.getFileUrl() != null) {
                File file2 = new File(resource.getFileUrl());
                file2.delete();

                commentMapper.removeCommentByVideo(resId);
                resMapper.removeRes(resId);
                return "Deleted Successfully";
            } else {
                return "Cannot find the file";
            }
        } catch (NullPointerException e) {
            return "Cannot find the file or the resource is already removed";
        }
    }

    @Override
    public String updateRes(int resId, int userID, String title, String description) {
        Resource oldVideo = resMapper.getRes(resId);
        if (oldVideo.getUserId() == userID || userMapper.getUserRoleById(userID).equals("admin")) {
            oldVideo.setResTitle(title);
            oldVideo.setResDescription(description);
            resMapper.updateResInfo(oldVideo);
            return "Updated Successfully";
        } else {
            return "Wrong account, can edit by uploader or admin";
        }
    }

    @Override
    public String getFilePathByName(String fileName) {
        Resource vf = resMapper.getFilePathByName(fileName);
        return vf.getFileUrl();
    }


}
