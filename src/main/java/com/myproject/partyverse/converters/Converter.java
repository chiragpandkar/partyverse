package com.myproject.partyverse.converters;

import com.myproject.partyverse.dbos.UserDbo;
import com.myproject.partyverse.dos.LoginSuccessDo;
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

  public static UserDo convertToUserDo(UserDbo userDbo) {
    UserDo userDo = new UserDo();
    userDo.setUsername(userDbo.getUsername());
    userDo.setEmail(userDbo.getEmail());
    userDo.setPassword(userDbo.getPassword());
    userDo.setName(userDbo.getName());
    userDo.setProfileAvatar(userDbo.getProfileAvatar());
    return userDo;
  }

  public static LoginSuccessDo convertToLoginSuccessDo(String token, UserDbo userDbo) {
    UserDo userDo = new UserDo();
    userDo.setId(userDbo.getId());
    userDo.setEmail(userDbo.getEmail());
    userDo.setUsername(userDbo.getUsername());
    userDo.setProfileAvatar(userDbo.getProfileAvatar());
    return new LoginSuccessDo(token, userDo);
  }
}
