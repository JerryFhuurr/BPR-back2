package com.bpr.bprbackend2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VideoFile {
    private int videoId;
    private int courseId;
    private int userId;
    private int roleId;
    private float videoScore;
    private String videoTitle;
    private String videoDescription;
    private String videoPath;
    private String fileUrl;
    private String videoFileName;
    private String fileName;
    private String videoFileDownload;
    private String fileNameDownload;
}
