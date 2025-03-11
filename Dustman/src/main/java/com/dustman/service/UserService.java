package com.dustman.service;

import com.dustman.dao.UserDAO;
import com.dustman.dto.user.*;
import com.dustman.model.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String registerUser(RegisterInfo registerInfo) {
        String result = userDAO.registerUser(registerInfo);
        if (result.equals("exist")) {
            return "User already exist";
        } else if (result.equals("true")) {
            return "User successfully registered";
        } else {
            return "Something went wrong! \nPlease try again";
        }
    }

    public List<UserDetails> getAllUser() {
        return userDAO.getAllUser();
    }

    public UserInfo getUserById(String id) {
        UserDetails userDetails = userDAO.getUserById(id);

        return new UserInfo(userDetails.getUserId(), userDetails.getUserName(), "Hide it for Security purpose", userDetails.getEmail(), userDetails.getImageUrl(), userDetails.getImageId(), userDetails.getRole(), userDetails.getUserRoc(), userDetails.getCreatedAt(), userDetails.getUpdatedAt());
    }

    public boolean checkUser(String id) {
        return userDAO.checkUser(id);

    }

    public String updateUser(UpdatedUserInfo updatedUserInfo) {
        boolean status = userDAO.updateUser(updatedUserInfo);
        return (status) ? "User successfully updated" : "Something went wrong! \nPlease try again";
    }

    public String updatePassword(UpdatePasswordInfo updatePasswordInfo) {
        if (!userDAO.checkUser(updatePasswordInfo.getUserId())) {
            return "User not found";
        }
        if (userDAO.checkPassword(updatePasswordInfo.getUserId(), updatePasswordInfo.getOldPassword())) {
            userDAO.updatePassword(updatePasswordInfo.getUserId(), updatePasswordInfo.getNewPassword());
            return "Password updated";
        } else {
            return "Old password is incorrect";
        }
    }

    public String updateRole(UpdateRole updateRole) {
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

    public String deleteUser(String id) {
        if (userDAO.deleteUser(id)) {
            return "User deleted successfully";
        } else {
            return "User not found";
        }
    }

}
