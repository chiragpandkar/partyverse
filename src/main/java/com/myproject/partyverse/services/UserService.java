package com.myproject.partyverse.services;

import com.myproject.partyverse.converters.Converter;
import com.myproject.partyverse.dbos.UserDbo;
import com.myproject.partyverse.dos.UserDo;
import com.myproject.partyverse.http.HttpResponseCodes;
import com.myproject.partyverse.http.HttpResponseDo;
import com.myproject.partyverse.http.HttpResponseMessages;
import com.myproject.partyverse.repository.UserRepository;
import com.myproject.partyverse.utils.StringUtils;
import jakarta.persistence.Convert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

  private final Logger logger = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TokenService tokenService;

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
          return ResponseEntity.ok(HttpResponseDo.error(HttpResponseCodes.INCORRECT_PASSWORD,
                  HttpResponseMessages.INCORRECT_PASSWORD));
        }

        String token = tokenService.generateToken(userDbo.getId());
        return ResponseEntity.ok(HttpResponseDo.success(Converter.convertToLoginSuccessDo(token, userDbo)));
    } catch (Exception e){
      logger.error("Exception Occurred {}", StringUtils.printStackTrace(e));
      return ResponseEntity.ok(HttpResponseDo.error(HttpResponseCodes.SOMETHING_WENT_WRONG,
              HttpResponseMessages.SOMETHING_WENT_WRONG));
    }
  }

  public ResponseEntity<?> search(String username) {
    try{
      List<UserDbo> searchResults = userRepository.findByUsernameStartingWith(username);
      return ResponseEntity.ok(HttpResponseDo.success(searchResults));
    } catch(Exception e){
      logger.error("Exception Occurred {}", StringUtils.printStackTrace(e));
      return ResponseEntity.ok(HttpResponseDo.error(HttpResponseCodes.SOMETHING_WENT_WRONG,
              HttpResponseMessages.SOMETHING_WENT_WRONG));
    }
  }


  public ResponseEntity<?> get(Long userId) {
    Optional<UserDbo> userDbo = userRepository.findById(userId);
    try{
      if (userDbo.isEmpty()){
        return ResponseEntity.ok(HttpResponseDo.error(HttpResponseCodes.USER_DOES_NOT_EXISTS,
                HttpResponseMessages.USER_DOES_NOT_EXISTS));
      }
      return ResponseEntity.ok(HttpResponseDo.success(Converter.convertToUserDo(userDbo.get())));
    } catch (Exception e){
        logger.error("Exception occurred {}",StringUtils.printStackTrace(e));
        return ResponseEntity.ok(HttpResponseDo.error(HttpResponseCodes.SOMETHING_WENT_WRONG,
                HttpResponseMessages.SOMETHING_WENT_WRONG));
    }

  }
}
