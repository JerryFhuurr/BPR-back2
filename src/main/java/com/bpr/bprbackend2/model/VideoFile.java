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
    private String videoTitle;
    private String videoDescription;
    private String videoPath;
    private String videoUrl;
    private String fileUrl;
}
