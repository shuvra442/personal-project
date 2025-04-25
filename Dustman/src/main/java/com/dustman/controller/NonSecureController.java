package com.dustman.controller;


import com.dustman.dto.Status;
import com.dustman.dto.UserDTO;
import com.dustman.service.EmailService;
import com.dustman.service.UserService;
import com.dustman.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api")
public class NonSecureController {
//    @Autowired
//    private EmailService emailService;
//
//    private final UserService userService;
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtil jwtUtil;
//    private final UserDetailsService userDetailsService;
//
//    public NonSecureController( UserDetailsService userDetailsService,UserService userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
//        this.userDetailsService= userDetailsService;
//        this.userService = userService;
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<Object> saveUser(@RequestBody UserDTO registerInfo) {
//        Status result = userService.registerUser(registerInfo);
//        return ResponseEntity.status(result.code()).body(result.data());
//    }
//
//    @PostMapping("/user_login")
//    public ResponseEntity<String> login(@RequestBody UserDTO loginInfo) {
//        String userName= loginInfo.getUsername();
//        String password= loginInfo.getPassword();
//        System.out.println("Enter into login");
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("username and Password is incorrect");
//        }
//
//        UserDetails userDetails= userDetailsService.loadUserByUsername(userName);
//        String jwt= jwtUtil.generateJWT(userDetails);
//
//        return ResponseEntity.ok(jwt);
//    }
//
//    @PatchMapping("/updaterole")
//    public ResponseEntity<String> updateRole(@RequestBody UserDTO updateRole) {
//        System.out.println(updateRole.getUserId());
//        return ResponseEntity.ok(userService.updateRole(updateRole));
//    }
//
//

}

