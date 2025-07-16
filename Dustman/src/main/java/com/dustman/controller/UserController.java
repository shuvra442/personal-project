package com.dustman.controller;

import com.dustman.model.User;
import com.dustman.service.UserService;
import com.dustman.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;



    // READ: Single user by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        ResponseData responseData = userService.getUserById(id);
        return ResponseEntity.status(responseData.status()).body(responseData);
    }

    // READ: All users
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        ResponseData responseData = userService.getAllUsers();
        return ResponseEntity.status(responseData.status()).body(responseData);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        ResponseData  responseData = userService.updateUser(id, updatedUser);
        return ResponseEntity.status(responseData.status()).body(responseData);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        ResponseData  responseData = userService.deleteUser(id);
        return ResponseEntity.status(responseData.status()).body(responseData);
    }

}
