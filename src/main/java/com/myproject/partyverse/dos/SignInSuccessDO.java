package com.myproject.partyverse.dos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInSuccessDO {
  private String token;
  private UserDo user;
}
