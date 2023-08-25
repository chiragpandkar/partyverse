package com.myproject.partyverse.dos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginSuccessDo {
  private String token;
  private UserDo user;
}
