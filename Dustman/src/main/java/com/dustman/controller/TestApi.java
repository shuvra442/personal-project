package com.dustman.controller;

import com.dustman.dto.UserDto;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class TestApi {

    private static final Logger logger = LoggerFactory.getLogger(TestApi.class);

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JWTCreate jwtCreate;

    @GetMapping("/test")
    public String testLog() {
        logger.info("INFO: /api/test endpoint was called");
        logger.warn("WARN: Just a test warning log");
        logger.error("ERROR: Just a test error log");

        return "Log test completed!";
    }



    @PostMapping("/login")
    public ResponseEntity getLogin(@RequestBody UserDto userDto, HttpServletResponse httpServletResponse) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getEmail());
        String businessKey = jwtCreate.generateToken(userDetails);
        ResponseCookie responseCookie = ResponseCookie.from("set_cookie", businessKey).httpOnly(true).path("/").maxAge(24 * 60 * 60).build();
        logger.info("the cookie is ::-->", responseCookie);
        httpServletResponse.setHeader("Set-Cookie", responseCookie.toString());
        return ResponseEntity.ok().body("Working");
    }
}
