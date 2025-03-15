package com.dustman.controller;


import com.dustman.dto.Status;
import com.dustman.dto.UserDTO;
import com.dustman.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class NonSecureController {

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


    @PutMapping("/updatepassword")
    public ResponseEntity<?> forgotPassword() {
        return ResponseEntity.ok("Password updated successfully");
    }

}

