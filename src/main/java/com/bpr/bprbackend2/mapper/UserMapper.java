package com.bpr.bprbackend2.mapper;

import com.bpr.bprbackend2.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
    User loginGet(String username);
    User getUserInfo(String username);
    String getUserRole(String username);
    String getUserRoleById(int userId);
    void updateUserPassword(String username, String password);
    ArrayList<String> getUsernames();
    void updateUserInfo(User user);
    User getUserInfoByUserId(int id);
}
