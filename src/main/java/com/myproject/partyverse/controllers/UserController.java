package com.myproject.partyverse.controllers;

import com.myproject.partyverse.converters.Converter;
import com.myproject.partyverse.dbos.UserDbo;
import com.myproject.partyverse.dos.UserDo;
import com.myproject.partyverse.exceptions.ValidRequestBodyException;
import com.myproject.partyverse.http.HttpResponseCodes;
import com.myproject.partyverse.http.HttpResponseDo;
import com.myproject.partyverse.http.HttpResponseMessages;
import com.myproject.partyverse.repository.UserRepository;
import com.myproject.partyverse.services.UserService;
import com.myproject.partyverse.utils.StringUtils;
import com.myproject.partyverse.validator.Validator;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody UserDo userDo) {
    try {
      Validator.registerUserRequest(userDo);
      return userService.register(userDo);
    } catch (Exception e) {
      if (e instanceof ValidRequestBodyException) {
        return ResponseEntity.ok(((ValidRequestBodyException) e).getValidationError());
      }
      return ResponseEntity.ok(HttpResponseDo.error(HttpResponseCodes.SOMETHING_WENT_WRONG,
        HttpResponseMessages.SOMETHING_WENT_WRONG));
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody UserDo userDo){
    try{
      Validator.loginUserRequest(userDo);
      return userService.login(userDo);
    } catch (Exception e){
      if(e instanceof ValidRequestBodyException){
        return ResponseEntity.ok(((ValidRequestBodyException) e).getValidationError());
      }
      return ResponseEntity.ok(HttpResponseDo.error(HttpResponseCodes.SOMETHING_WENT_WRONG,
              HttpResponseMessages.SOMETHING_WENT_WRONG));
    }
  }

  @GetMapping("/search")
  public ResponseEntity<?> search(@RequestParam String query){
    try{
      return UserService.search(query);
    } catch(Exception e){
        return ResponseEntity.ok(HttpResponseDo.error(HttpResponseCodes.SOMETHING_WENT_WRONG,
                HttpResponseMessages.SOMETHING_WENT_WRONG));
    }

  }

  @GetMapping("/profile")
  public ResponseEntity<?> profile(@AuthenticationPrincipal UserDbo userDbo){
    try {
      String username = userDbo.getUsername();
      ResponseEntity<?> profileDetails = userService.getProfile(username);
      return ResponseEntity.ok(profileDetails);
    } catch (Exception e){
        return ResponseEntity.ok(HttpResponseDo.error(HttpResponseCodes.SOMETHING_WENT_WRONG,
                HttpResponseMessages.SOMETHING_WENT_WRONG));
    }
  }


  /*
              main branch -------------------------------------------------------------->
                  |
                  v
                  dev -------------------------------------------  merge --------->
                  |                                 PR (review) /
                  v                               /
                  feat/user -------- code remote |
                                            ^ git push
                                            |
                              local -> commited changes
                           git commit  -m "Message" -> Added Utils
                           git add
                  git status   staged(green) and unstaged(red)


        remote -> GitHub
        local -> git

   */


}
