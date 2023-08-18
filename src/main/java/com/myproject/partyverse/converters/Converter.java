package com.myproject.partyverse.converters;

import com.myproject.partyverse.dbos.UserDbo;
import com.myproject.partyverse.dos.UserDo;

public class Converter {

  public static UserDbo convertToUserDbo(UserDo userDo){
    UserDbo userDbo = new UserDbo();
    userDbo.setUsername(userDo.getUsername());
    userDbo.setEmail(userDo.getEmail());
    userDbo.setPassword(userDo.getPassword());
    userDbo.setName(userDo.getName());
    userDbo.setProfileAvatar(userDo.getProfileAvatar());
    return userDbo;
  }
}
