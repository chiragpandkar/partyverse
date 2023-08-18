package com.myproject.partyverse.services;

import com.myproject.partyverse.dos.UserDo;
import com.myproject.partyverse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(UserDo userDo) {
        UserDo user = new UserDo();
        user.setUsername(userDo.getUsername());
        user.setPassword(userDo.getPassword());
        user.setEmail(userDo.getEmail());

        UserRepository.save(user);

        return "token";
    }
}
