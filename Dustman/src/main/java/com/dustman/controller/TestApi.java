package com.dustman.controller;

import com.dustman.dto.UserDto;
import com.dustman.model.User;
import com.dustman.service.UserService;
import com.dustman.utils.ResponseData;
import com.dustman.utils.jwt.JWTCreate;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.angus.mail.iap.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController()
public class TestApi {

    private static final Logger logger = LoggerFactory.getLogger(TestApi.class);

    @Autowired
    UserService userService;
    @Autowired
    JWTCreate jwtCreate;
    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("/test")
    public String testLog() {
        logger.info("INFO: /api/test endpoint was called");
        logger.warn("WARN: Just a test warning log");
        logger.error("ERROR: Just a test error log");

        return "Log test completed!";
    }


    @PostMapping("/login")
    public ResponseEntity<?> getLogin(@RequestBody UserDto userDto, HttpServletResponse httpServletResponse) {
        System.out.println("userDto=>"+userDto.toString());
        try{
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getEmail());

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
            return ResponseEntity.status(400).body("User Not Fount");
        }

    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        ResponseData responseData =  userService.createUser(user);
        return ResponseEntity.status(responseData.status()).body(responseData.data());
    }
}
