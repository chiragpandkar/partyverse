package com.myproject.partyverse.services;

import com.myproject.partyverse.converters.Converter;
import com.myproject.partyverse.dbos.UserDbo;
import com.myproject.partyverse.dos.UserDo;
import com.myproject.partyverse.http.HttpResponseCodes;
import com.myproject.partyverse.http.HttpResponseDo;
import com.myproject.partyverse.http.HttpResponseMessages;
import com.myproject.partyverse.repository.UserRepository;
import com.myproject.partyverse.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class UserService {

  private final Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private UserRepository userRepository;

  public ResponseEntity<?> register(UserDo userDo) {
    try {
      UserDbo exists = userRepository.findByEmailOrUsername(userDo.getEmail(),
        userDo.getUsername());
      if (exists != null) {
        return ResponseEntity.ok(HttpResponseDo.error(HttpResponseCodes.USER_ALREADY_EXISTS,
          HttpResponseMessages.USER_ALREADY_EXISTS));
      }
      userRepository.save(Converter.convertToUserDbo(userDo));
      return ResponseEntity.ok(HttpResponseDo.success(HttpResponseMessages.SUCCESSFUL_REGISTRATION));
    } catch (Exception e) {
      logger.error("Exception Occurred {}", StringUtils.printStackTrace(e));
      return ResponseEntity.ok(HttpResponseDo.error(HttpResponseCodes.SOMETHING_WENT_WRONG,
        HttpResponseMessages.SOMETHING_WENT_WRONG));
    }
  }

  public ResponseEntity<?> login(UserDo userDo) {
    try{
        UserDbo userDbo = userRepository.findByEmailOrUsername(userDo.getEmail(), userDo.getUsername());
        if(userDbo == null){
          return ResponseEntity.ok(HttpResponseDo.error(HttpResponseCodes.USER_DOES_NOT_EXISTS,
                  HttpResponseMessages.USER_DOES_NOT_EXISTS));
        }

        if(!userDbo.getPassword().equals(userDo.getPassword())){
          return ResponseEntity.ok(HttpResponseDo.error(HttpResponseCodes.INVALID_PASSWORD,
                  HttpResponseMessages.INVALID_PASSWORD));
        }

        String token = tokenService.generateJwtToken(userDbo);
        return ResponseEntity.ok(HttpResponseDo.success(token));

    } catch (Exception e){
      logger.error("Exception Occurred {}", StringUtils.printStackTrace(e));
      return ResponseEntity.ok(HttpResponseDo.error(HttpResponseCodes.SOMETHING_WENT_WRONG,
              HttpResponseMessages.SOMETHING_WENT_WRONG));
    }
  }

  public static ResponseEntity<?> search(String query) {
    try{
      List<UserDbo> searchResults = UserRepository.findByUsernameContainingIgnoreCaseOrNameContainingIgnoreCase(query, query);
      List<String> userNames = new ArrayList<>();
      for (UserDbo userDbo : searchResults){
        userNames.add(userDbo.getName());
      }
      return ResponseEntity.ok(HttpResponseDo.success(userNames));
    } catch(Exception e){
      logger.error("Exception Occurred {}", StringUtils.printStackTrace(e));
      return ResponseEntity.ok(HttpResponseDo.error(HttpResponseCodes.SOMETHING_WENT_WRONG,
              HttpResponseMessages.SOMETHING_WENT_WRONG));
    }
  }


  public ResponseEntity<?> getProfile(String username) {
    UserDbo userDbo = userRepository.findByUsername(username);
    try{
      if (userDbo == null){
        return ResponseEntity.ok(HttpResponseDo.error(HttpResponseCodes.USER_DOES_NOT_EXISTS,
                HttpResponseMessages.USER_DOES_NOT_EXISTS));
      }
      List<String> profileDetails = Arrays.asList(userDbo.getName(), userDbo.getUsername(), userDbo.getEmail(), String.valueOf(userDbo.getId()));
      return ResponseEntity.ok(HttpResponseDo.success(profileDetails));
    } catch (Exception e){
        logger.error("Exception occurred {}",StringUtils.printStackTrace(e));
        return ResponseEntity.ok(HttpResponseDo.error(HttpResponseCodes.SOMETHING_WENT_WRONG,
                HttpResponseMessages.SOMETHING_WENT_WRONG));
    }

  }
}
