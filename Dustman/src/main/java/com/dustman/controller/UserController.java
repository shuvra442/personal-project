package com.dustman.controller;

import com.dustman.model.User;
import com.dustman.service.UserService;
import com.dustman.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody User user) {
      ResponseData responseData =  userService.createUser(user);
//      ResponseData responseData = new ResponseData();
//      responseData.setMessage("User Created");
      return ResponseEntity.status(responseData.getStatus()).body(responseData.getData());

    }

}
