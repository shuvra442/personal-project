package com.dustman.service;

import com.dustman.model.User;
import com.dustman.repository.UserRepo;
import com.dustman.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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


    public ResponseData<?> getUserById(int id) {
        return userRepo.findById(id)
                .map(user -> new ResponseData<>(200, "Fetch data", user ))
                .orElseGet(()-> new ResponseData<>(400, "User Not Found", null));

    }


    public ResponseData<List<User>> getAllUsers() {
        List<User> all = userRepo.findAll();
        return new ResponseData<>(200, "Fetch data", all);
    }

    public ResponseData<User> updateUser(int id, User updatedUser) {
        Optional<User> existingUserOpt = userRepo.findById(id);

        if (existingUserOpt.isEmpty()) {
            return new ResponseData<>(404, "User not found", null);
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
        return new ResponseData<>(200, "User updated successfully", savedUser);
    }


    public ResponseData<?> deleteUser(int id) {
        Optional<User> userOpt = userRepo.findById(id);

        if (userOpt.isEmpty()) {
            return new ResponseData<>(404, "User not found", null);
        }

        userRepo.deleteById(id);
        return new ResponseData<>(200, "User deleted successfully", null);
    }

}
