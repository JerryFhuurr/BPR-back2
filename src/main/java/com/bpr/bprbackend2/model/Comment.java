package com.bpr.bprbackend2.model;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    private int commentId;
    // ↓ these are info from video (not sender!!)
    private int userId;
    private int courseId;
    private int resId;
    private int roleId;
    // ↑
    private int senderId;
    private String senderName;
    private String commentText;
    private long commentTime;
    private float commentScore;
}
