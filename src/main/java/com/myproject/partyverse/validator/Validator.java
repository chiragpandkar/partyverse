package com.myproject.partyverse.validator;

import com.myproject.partyverse.config.ConfigProps;
import com.myproject.partyverse.dos.UserDo;
import com.myproject.partyverse.exceptions.ValidRequestBodyException;
import com.myproject.partyverse.http.HttpResponseCodes;
import com.myproject.partyverse.http.HttpResponseDo;
import com.myproject.partyverse.http.HttpResponseMessages;
import com.myproject.partyverse.utils.StringUtils;
import com.myproject.partyverse.utils.ValidationUtils;

import javax.xml.bind.ValidationException;

public class Validator {

  public static void registerUserRequest(UserDo userDo) {
    if (userDo == null){
      throw new ValidRequestBodyException(HttpResponseDo.error(HttpResponseCodes.NULL_USER_DO, HttpResponseMessages.NULL_USER_DO));
    }
    validateEmail(userDo.getEmail());
    validatePassword(userDo.getPassword());
    validateName(userDo.getName());
    validateUsername(userDo.getUsername());
    // todo user can have empty avatar also validateProfileAvatar(userDo.getProfileAvatar());
  }

  public static void loginUserRequest(UserDo userDo) {
    if(userDo == null){
      throw new ValidRequestBodyException(HttpResponseDo.error(HttpResponseCodes.NULL_USER_DO, HttpResponseMessages.NULL_USER_DO));
    }
    validateEmail(userDo.getEmail());
    validateUsername(userDo.getUsername());
    validatePassword(userDo.getPassword());
  }

  private static void validateProfileAvatar(Integer profileAvatar){
    if (profileAvatar == null) {
      throw new ValidRequestBodyException(
        HttpResponseDo.error(HttpResponseCodes.NULL_PROFILE_AVATAR, HttpResponseMessages.NULL_PROFILE_AVATAR));
    }
  }

  private static void validateUsername(String username) {
    if (StringUtils.isNullOrEmpty(username)) {
      throw new ValidRequestBodyException(
        HttpResponseDo.error(HttpResponseCodes.NULL_USERNAME, HttpResponseMessages.NULL_USERNAME));
    }
    if (username.length() > ConfigProps.MAX_USERNAME_LENGTH || username.length() < ConfigProps.MIN_USERNAME_LENGTH){
      throw new ValidRequestBodyException(HttpResponseDo.error(HttpResponseCodes.INVALID_USERNAME, HttpResponseMessages.INVALID_USERNAME));
    }
  }

  private static void validateName(String name) {
    if (StringUtils.isNullOrEmpty(name)) {
      throw new ValidRequestBodyException(HttpResponseDo.error(HttpResponseCodes.NULL_NAME, HttpResponseMessages.NULL_NAME));
    }
  }

  private static void validateEmail(String email) {
    if (!ValidationUtils.isValidEmail(email)){
      throw new ValidRequestBodyException(HttpResponseDo.error(HttpResponseCodes.INVALID_EMAIL, HttpResponseMessages.INVALID_EMAIL));
    }
  }

  private static void validatePassword(String password){
    if (StringUtils.isNullOrEmpty(password)) {
      throw new ValidRequestBodyException(HttpResponseDo.error(HttpResponseCodes.NULL_PASSWORD, HttpResponseMessages.NULL_PASSWORD));
    }
    if (password.length() > ConfigProps.MAX_PASSWORD_LENGTH || password.length() < ConfigProps.MIN_PASSWORD_LENGTH){
      throw new ValidRequestBodyException(HttpResponseDo.error(HttpResponseCodes.INVALID_PASSWORD, HttpResponseMessages.INVALID_PASSWORD));
    }
  }
}
