package com.dustman.controller;

import com.dustman.dto.UserDto;
import com.dustman.service.CloudinaryService;
import com.dustman.service.UserService;
import com.dustman.utils.ResponseData;
import com.dustman.utils.jwt.JWTCreate;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController()
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;
    @Autowired
    JWTCreate jwtCreate;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    CloudinaryService cloudinaryService;

    @PostMapping("/login")
    public ResponseEntity<?> getLogin(@RequestBody UserDto userDto, HttpServletResponse httpServletResponse) {
        System.out.println("userDto=>"+userDto.toString());
        try{
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getEmail());
            logger.info("User Email -->", userDetails);

            ResponseData responseData = userService.login(userDetails,userDto);

            String businessKey = jwtCreate.generateToken(userDetails);
            if (responseData.status() == 200) {
                ResponseCookie responseCookie = ResponseCookie
                        .from("AccessToken", businessKey)
                        .sameSite("Lax")
                        .httpOnly(true)
                        .path("/")
                        .maxAge(24 * 60 * 60).build();
                System.out.println("the cookie is ::-->" + responseCookie);
                logger.info("the cookie is ::-->", responseCookie);
                httpServletResponse.setHeader("Set-Cookie", responseCookie.toString());
            }

            return ResponseEntity.status(responseData.status()).body(responseData.data());
        } catch (UsernameNotFoundException e) {
            logger.error("Error in loginController -->", e.getMessage());
            return ResponseEntity.status(400).body("User Not Found");
        }

    }

    // User CREATE
    @PostMapping("/register")
    public ResponseEntity<?> createUser(@ModelAttribute UserDto userDto) {
        ResponseData responseData =  userService.createUser(userDto);
        return ResponseEntity.status(responseData.status()).body(responseData.data());
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestBody String  file) {
        try{
            String responseData = cloudinaryService.deleteFile(file);
            return ResponseEntity.ok(responseData);
        } catch (IOException e) {
            return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).body(e.getMessage());
        }
    }
}
