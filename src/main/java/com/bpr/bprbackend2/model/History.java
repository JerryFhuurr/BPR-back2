package com.bpr.bprbackend2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class History {
    private int hId;
    private int userId;
    private int courseId;
    private int videoId;
    private int roleId;
    private int watcherId;
    private long watchTime;
}
