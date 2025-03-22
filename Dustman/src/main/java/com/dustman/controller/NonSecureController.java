package com.dustman.controller;


import com.dustman.dto.Status;
import com.dustman.dto.UserDTO;
import com.dustman.service.EmailService;
import com.dustman.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NonSecureController {
    @Autowired
    private EmailService emailService;

    private final UserService userService;

    public NonSecureController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> saveUser(@RequestBody UserDTO registerInfo) {
        Status result = userService.registerUser(registerInfo);
        return ResponseEntity.status(result.code()).body(result.data());
    }

    @PostMapping("/login")
    public boolean login(@RequestBody UserDTO loginInfo) {
        return true;
    }



}

