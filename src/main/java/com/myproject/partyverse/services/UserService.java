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
}
