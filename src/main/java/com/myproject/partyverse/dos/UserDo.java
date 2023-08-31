package com.myproject.partyverse.dos;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDo {

    private Long id;
    private Boolean isActive;
    private String username;
    private String password;
    private String email;
    private String name;
    private Integer profileAvatar;
    private Timestamp createdAt;
    private Timestamp updatedAt;


}

