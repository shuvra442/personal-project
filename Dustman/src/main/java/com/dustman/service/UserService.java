package com.dustman.service;

import com.dustman.dao.UserDAO;
import com.dustman.dto.Status;
import com.dustman.dto.UserDTO;
import com.dustman.model.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Status registerUser(UserDTO registerInfo) {
        String result = userDAO.registerUser(registerInfo);
        if (result.equals("exist")) {
            return new Status(409, "User already exist");
        } else if (result.equals("true")) {
            return new Status(201, "User successfully registered");
        } else {
            return new Status(500, "Internal server error");
        }
    }

    public Status getAllUser() {
        List<UserDetails> userDetails = userDAO.getAllUser();
        if (!userDetails.isEmpty()) {
            return new Status(200, userDetails);
        }
        return new Status(204, "No user found");
    }

    //todo add Status
    public Status getUserById(String id) {
        boolean isExist = userDAO.checkUser(id);
        if (isExist) {
            UserDetails userDetails = userDAO.getUserById(id);
            return new Status(200, userDetails);
        }
        return new Status(404, "use not found with this ID");
    }

    public Status checkUser(String id) {
        boolean state = userDAO.checkUser(id);
        if (state) {
            return new Status(200, "User found");
        } else {
            return new Status(404, "Data not found");
        }
    }

    public Status updateUser(UserDTO updatedUserInfo) {
        boolean isExist = userDAO.checkUser(updatedUserInfo.getUserId());
        if (isExist) {
            boolean status = userDAO.updateUser(updatedUserInfo);
            if (status) {
                return new Status(200, "User successfully updated");
            }
            return new Status(500);
        }
//
        return new Status(404, "Data not found for this user ID");
    }

    public Status updatePassword(UserDTO updatePasswordInfo) {
        if (userDAO.checkUser(updatePasswordInfo.getUserId())) {
            if (userDAO.checkPassword(updatePasswordInfo.getUserId(), updatePasswordInfo.getOldPassword())) {
                userDAO.updatePassword(updatePasswordInfo.getUserId(), updatePasswordInfo.getNewPassword());
                return new Status(200,"Password Update Successfully");
            } else {
                return new Status(400,"Old Password is incorrect");
            }
        }
        return new Status(404, "Data not found for this user ID");
    }

    public String updateRole(UserDTO updateRole) {
        try {
            if (!userDAO.checkUser(updateRole.getUserId())) {
                return "User not found";
            }
            if (userDAO.updateRole(updateRole)) {

                return "Role updated successfully";
            } else {
                return "Something went wrong! \nPlease try again";
            }
        } catch (IllegalArgumentException e) {
            return "Invalid role provided";
        }
    }

    public Status deleteUser(String id) {
        if (userDAO.checkUser(id)){
            if (userDAO.deleteUser(id)) {
                return new Status(200,"User deleted successfully");
            } else {
                return new Status(500);
            }
        }
        return new Status(404,"Data not found for this user ID");
    }

}
