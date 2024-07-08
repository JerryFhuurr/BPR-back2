package com.bpr.bprbackend2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Date birth;
    private String role;
}
