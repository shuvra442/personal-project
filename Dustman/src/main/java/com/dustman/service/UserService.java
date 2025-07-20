package com.dustman.service;

import com.dustman.dto.FileDto;
import com.dustman.dto.UserDto;
import com.dustman.model.User;
import com.dustman.repository.UserRepo;
import com.dustman.utils.ResponseData;
import com.dustman.utils.enums.Roles;
import com.dustman.utils.jwt.JWTCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JWTCreate jwtCreate;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    CloudinaryService cloudinaryService;

    public ResponseData createUser(UserDto userDto) {

        User user=this.userDtoToUser(userDto);
        if (user.getEmail() != null) {
            Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                return new ResponseData(409, "Email already exists");
            }
        }
        try{
           FileDto fileDto= cloudinaryService.uploadFile(userDto.getFile());
           user.setUserImage(fileDto.ImageURL());
           user.setUserImageId(fileDto.ImageID());
        } catch (IOException e) {
            return new ResponseData(400, e.getMessage());
        }
        if (user.getRole() == null) {
            user.setRole(Roles.USER);
        }
        User userCreated = userRepo.save(user);
        return new ResponseData(200, userCreated);
    }


    public ResponseData getUserById(int id) {
        return userRepo.findById(id)
                .map(user -> new ResponseData(200, user))
                .orElseGet(() -> new ResponseData(400, "User Not Found"));

    }


    public ResponseData getAllUsers() {
        List<User> all = userRepo.findAll();
        return new ResponseData(200, all);
    }

    public ResponseData updateUser(int id, User updatedUser) {
        Optional<User> existingUserOpt = userRepo.findById(id);

        if (existingUserOpt.isEmpty()) {
            return new ResponseData(404, "User not found");
        }

        User existingUser = existingUserOpt.get();

        // Update only non-null fields
        if (updatedUser.getEmail() != null) existingUser.setEmail(updatedUser.getEmail());
        if (updatedUser.getPhoneNo() != null) existingUser.setPhoneNo(updatedUser.getPhoneNo());
        if (updatedUser.getPassword() != null) existingUser.setPassword(updatedUser.getPassword());
        if (updatedUser.getAddress() != null) existingUser.setAddress(updatedUser.getAddress());
        if (updatedUser.getUserImage() != null) existingUser.setUserImage(updatedUser.getUserImage());
        if (updatedUser.getUserImageId() != null) existingUser.setUserImageId(updatedUser.getUserImageId());
        if (updatedUser.getRole() != null) existingUser.setRole(updatedUser.getRole());

        User savedUser = userRepo.save(existingUser);
        return new ResponseData(200, savedUser);
    }


    public ResponseData deleteUser(int id) {
        Optional<User> userOpt = userRepo.findById(id);

        if (userOpt.isEmpty()) {
            return new ResponseData(404, "User not found");
        }

        userRepo.deleteById(id);
        return new ResponseData(200, "User deleted successfully");
    }

    public ResponseData login(UserDetails userDetails, UserDto userDto) {
        try {

            User user = userRepo.findByEmail(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User doesn't Exist"));
            System.out.println("passwordEncoder.matches(user.getPassword(), userDetails.getPassword())=>" + passwordEncoder.matches(user.getPassword(), userDetails.getPassword()));
            if (passwordEncoder.matches(userDto.getPassword(), userDetails.getPassword())) {
                return new ResponseData(200, user);
            }

            return new ResponseData(400, "Wrong Password");
        } catch (UsernameNotFoundException e) {
            return new ResponseData(400, e.getMessage());
        } catch (Exception e) {
            return new ResponseData(500, e.getMessage());
        }

    }

    private User userDtoToUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAddress(userDto.getAddress());
        user.setCreatedAt(new Date());

        return user;
    }

}
