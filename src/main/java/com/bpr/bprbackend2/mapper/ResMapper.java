package com.bpr.bprbackend2.mapper;

import com.bpr.bprbackend2.model.Resource;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface ResMapper {
    ArrayList<Resource> getResList(int courseId);
    ArrayList<Resource> getResListByUser(int userId);
    Resource getRes(int resId);
    void addRes(Resource resource);
    void removeRes(int resId);
    void updateResInfo(Resource resource);
    Resource getFilePathByName(String fileName);
    void updateResScore(Resource resource);
}
