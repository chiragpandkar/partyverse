package com.myproject.partyverse.http;

import com.myproject.partyverse.config.ConfigProps;

public class HttpResponseMessages {
  public static final String NULL_USER_DO = "User Is Null.";
  public static final String INVALID_EMAIL = "Please Enter Valid Email.";
  public static final String NULL_PASSWORD = "Password Is Empty";
  public static final String INVALID_PASSWORD = "Password must be less than " +
                                                ConfigProps.MAX_PASSWORD_LENGTH +
                                                " and greater than " + ConfigProps.MIN_PASSWORD_LENGTH;
  public static final String SOMETHING_WENT_WRONG = "Something Went Wrong.";
  public static final String NULL_NAME = "Name Is Empty.";


  public static final String NULL_USERNAME = "Username is empty";
  public static final String INVALID_USERNAME = "Username must be less than " +
                                               ConfigProps.MAX_USERNAME_LENGTH +
                                               " and greater than " + ConfigProps.MIN_USERNAME_LENGTH;
  public static final String NULL_PROFILE_AVATAR = "Profile avatar is empty";
  public static final String USER_ALREADY_EXISTS = "Email Or Username Already Exists.";
  public static final Object SUCCESSFUL_REGISTRATION = "User Registration Successful.";
    public static final String USER_DOES_NOT_EXISTS = "User Does Not exists";
  public static final String SUCCESSFUL_LOGIN = "User Login Successful.";
  public static final String INVALID_ID = "User ID Invalid";
  public static final String INCORRECT_PASSWORD = "Password is incorrect!";
}
