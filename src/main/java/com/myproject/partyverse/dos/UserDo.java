package com.myproject.partyverse.dos;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDo {

    private Boolean isActive;
    private Long id;
    private String status;
    private String name;
    private String username;
    private String password;
    private String email;
    private Integer profileAvatar;
    private Timestamp createdAt;
    private Timestamp updatedAt;


}

