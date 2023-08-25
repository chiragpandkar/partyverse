package com.myproject.partyverse.utils;

import java.util.regex.Pattern;

public class ValidationUtils {

  private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

  public static boolean isValidEmail(String email){
    if (StringUtils.isNullOrEmpty(email)){
      return false;
    }
    return Pattern.matches(EMAIL_REGEX, email);
  }

  public static boolean isValidId(Long id){
    return id != null && id > 0;
  }
}
