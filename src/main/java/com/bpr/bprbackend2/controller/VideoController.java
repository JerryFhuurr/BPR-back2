package com.bpr.bprbackend2.controller;

import cn.hutool.core.io.FileUtil;
import com.bpr.bprbackend2.model.VideoFile;
import com.bpr.bprbackend2.service.VideoService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/get/list")
    public ArrayList<VideoFile> getList(@RequestParam int courseId) {
        return videoService.getVideoList(courseId);
    }

    @GetMapping("/get")
    public VideoFile getVideo(@RequestParam int videoId) {
        return videoService.getVideo(videoId);
    }

    @GetMapping("get/list/user")
    public ArrayList<VideoFile> getListUser(@RequestParam int userId) {
        return videoService.getVideoListByUser(userId);
    }

    @PostMapping("/upload/")
    public String uploadVideo(@RequestParam("files") MultipartFile[] files,
                              @RequestParam("courseId") int courseId,
                              @RequestParam("userId") int userId,
                              @RequestParam("roleId") int roleId,
                              @RequestParam("videoTitle") String videoTitle,
                              @RequestParam("videoDescription") String videoDescription) {

            VideoFile video = new VideoFile();
            video.setCourseId(courseId);
            video.setUserId(userId);
            video.setRoleId(roleId);
            video.setVideoTitle(videoTitle);
            video.setVideoDescription(videoDescription);
            return videoService.saveVideo(video, files);
    }

    @DeleteMapping("/remove")
    public String removeVideo(@RequestParam int videoId) {
        return videoService.removeVideo(videoId);
    }

    @PutMapping("/update/info")
    public String updateVideoInfo(@RequestParam int videoId, @RequestParam int userId,  @RequestParam String videoTitle, @RequestParam String videoDescription) {
        return videoService.updateVideo(videoId,userId, videoTitle, videoDescription);
    }

    /**
     * @param path     指想要下载的文件的路径
     * @param response
     * @功能描述 下载文件:将输入流中的数据循环写入到响应输出流中，而不是一次性读取到内存
     */
    @PostMapping("/downloadLocal")
    public void downloadLocal(@RequestParam String fileName, HttpServletResponse response) throws IOException {
        //        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));  // 附件下载
        // 默认格式就是预览，浏览器会根据格式进行判断，如果可以就预览，不可以就下载
//        response.addHeader("Content-Disposition", "inline;filename=" + URLEncoder.encode(fileName, "UTF-8"));  // 附件预览
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
        outputStream.write(bytes);    // 数组是一个字节数组，也就是文件的字节流数组
        outputStream.flush();
        outputStream.close();
    }

}
