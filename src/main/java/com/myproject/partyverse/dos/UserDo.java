package com.myproject.partyverse.dos;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDo {

    private Integer id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String profileAvatar;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String status;
    private boolean isActive;

}

