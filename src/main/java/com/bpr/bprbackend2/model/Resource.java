package com.bpr.bprbackend2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resource {
    private int resId;
    private int courseId;
    private int userId;
    private int roleId;
    private float resScore;
    private String resTitle;
    private String resDescription;
    private String fileUrl;
    private String fileName;
    private String fileNameDownload;
    private long fileSize;
    private String type;
}
