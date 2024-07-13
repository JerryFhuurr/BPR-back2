package com.bpr.bprbackend2.model;

import lombok.Data;

@Data
public class UserTestParam {
    private int[] courses;
    private String username;
    private String password;
    private String role;
}
