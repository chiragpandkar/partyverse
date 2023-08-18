package com.myproject.partyverse.controllers;

import com.myproject.partyverse.dos.UserDo;
import com.myproject.partyverse.http.HttpResponseDo;
import com.myproject.partyverse.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDo userDo) {
        try{
            String token = userService.registerUser(userDo);
            return ResponseEntity.ok(HttpResponseDo.success("User registered successfully. Token : " + token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST.value(),e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDo userDo){
        try{
            String token = userService.loginUser(userDo);
            return ResponseEntity.ok(token);
        } catch (InvalidCredentialsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

}
