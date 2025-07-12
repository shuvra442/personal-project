package com.dustman.service;

import com.dustman.model.User;
import com.dustman.repository.UserRepo;
import com.dustman.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;


    public ResponseData<?> createUser(User user) {
        if (user.getEmail() != null) {
            Optional<User> existingUser = userRepo.findByEmail(user.getEmail());
            if (existingUser.isPresent()) {
                return new ResponseData<>(409, "Email already exists", null);
            }
        }

        User userCreated = userRepo.save(user);
        return new ResponseData<>(200, "User Created", userCreated);
    }



}
