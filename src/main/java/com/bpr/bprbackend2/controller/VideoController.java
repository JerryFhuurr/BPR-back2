package com.bpr.bprbackend2.controller;

import cn.hutool.core.io.FileUtil;
import com.bpr.bprbackend2.model.Resource;
import com.bpr.bprbackend2.service.VideoService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/get/list")
    public ArrayList<Resource> getList(@RequestParam int courseId) {
        return videoService.getResList(courseId);
    }

    @GetMapping("/get")
    public Resource getVideo(@RequestParam int resId) {
        return videoService.getRes(resId);
    }

    @GetMapping("get/list/user")
    public ArrayList<Resource> getListUser(@RequestParam int userId) {
        return videoService.getResListByUser(userId);
    }

    @PostMapping("/upload/")
    public String uploadVideo(@RequestParam("files") MultipartFile[] files,
                              @RequestParam("courseId") int courseId,
                              @RequestParam("userId") int userId,
                              @RequestParam("roleId") int roleId,
                              @RequestParam("videoTitle") String resTitle,
                              @RequestParam("videoDescription") String resDescription) {

            Resource res = new Resource();
            res.setCourseId(courseId);
            res.setUserId(userId);
            res.setRoleId(roleId);
            res.setResTitle(resTitle);
            res.setResDescription(resDescription);
            return videoService.saveRes(res, files);
    }

    @DeleteMapping("/remove")
    public String removeVideo(@RequestParam int resId) {
        return videoService.removeRes(resId);
    }

    @PutMapping("/update/info")
    public String updateVideoInfo(@RequestParam int resId, @RequestParam int userId,
                                  @RequestParam String resTitle, @RequestParam String resDescription) {
        return videoService.updateRes(resId,userId, resTitle, resDescription);
    }

    /**
     * @param fileName     path of files user wants to download
     * @param response
     *  Download files: Write the data in the input stream to the response output stream in a loop instead of reading it into memory all at once
     */
    @GetMapping("/downloadLocal")
    public void downloadLocal(@RequestParam String fileName, HttpServletResponse response) throws IOException {
        //        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));  //  download
        // The default format is preview. The browser will judge based on the format. If it is possible, it will be previewed. If not, it will be downloaded.
//        response.addHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(fileName, "UTF-8"));  // preview
        String filePath = "";
        if (fileName.contains("Video_")) {
            filePath = videoService.getVideoPathByName(fileName);
        } else if (fileName.contains("File_")) {
            filePath = videoService.getFilePathByName(fileName);
        }
        if(!FileUtil.exist(filePath)){
            return;
        }
        byte[] bytes = FileUtil.readBytes(filePath);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);    // The array is a byte array, that is, the byte stream array of the file
        outputStream.flush();
        outputStream.close();
    }

}
