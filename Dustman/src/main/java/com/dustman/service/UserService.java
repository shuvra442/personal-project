package com.dustman.service;

import com.dustman.dao.UserDAO;
import com.dustman.dto.Status;
import com.dustman.dto.UserDTO;
import com.dustman.model.UserDetails;
import com.dustman.utils.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDAO userDAO;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;

    public UserService(UserDAO userDAO, EmailService emailService, JwtUtil jwtUtil) {
        this.userDAO = userDAO;
        this.emailService = emailService;
        this.jwtUtil = jwtUtil;
    }

    public Status registerUser(UserDTO registerInfo) {
        String result = userDAO.registerUser(registerInfo);
        if (result.equals("exist")) {
            emailService.sendEmail(registerInfo.getEmail(), "Register", "Someone tried to register with your email");
            return new Status(409, "User already exist");
        } else if (result.equals("true")) {
            emailService.sendEmail(registerInfo.getEmail(), "Register", "Registered successfully");
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
    public Status getUserById(String id, String jwtToken) {
        String email=jwtUtil.extractUserName(jwtToken);
        boolean isExist = userDAO.checkUser(id);
        if (isExist) {
            UserDetails userDetails = userDAO.getUserById(id);
            if (email.equals(userDetails.getEmail())){
                return new Status(200, userDetails);
            }
            return new Status(401);
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

    public Status updateUser(UserDTO updatedUserInfo, String jwtToken) {
        String email=jwtUtil.extractUserName(jwtToken);

        boolean isExist = userDAO.checkUser(updatedUserInfo.getUserId());
        if (isExist) {
            String dbEmail=userDAO.getEmailID(updatedUserInfo.getUserId());
            if (email.equals(dbEmail)){
                boolean status = userDAO.updateUser(updatedUserInfo);
                if (status) {
                    return new Status(200, "User successfully updated");
                }
            }else {
                return new Status(401);
            }
            return new Status(500);
        }
//
        return new Status(404, "Data not found for this user ID");
    }

    public Status updatePassword(UserDTO updatePasswordInfo,String jwtToken) {
        if (userDAO.checkUser(updatePasswordInfo.getUserId())) {
            String email = userDAO.getEmailID(updatePasswordInfo.getUserId());
            if (!email.equals(jwtUtil.extractUserName(jwtToken))){
                return new Status(401);
            }
            if (userDAO.checkPassword(updatePasswordInfo.getUserId(), updatePasswordInfo.getOldPassword())) {
                if (userDAO.updatePassword(updatePasswordInfo.getUserId(), updatePasswordInfo.getNewPassword())) {
                    emailService.sendEmail(email,"Password Update","Your password has been updated successfully");
                    return new Status(200, "Password Update Successfully");
                }
                return new Status(500);
            } else {
                emailService.sendEmail(email,"Password Update","Someone tried to update your password");
                return new Status(400, "Old Password is incorrect");
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

    public Status deleteUser(String id,String token) {
        String email = userDAO.getEmailID(id);
        if (!email.equals(jwtUtil.extractUserName(token))){
            return new Status(401);
        }
        if (userDAO.checkUser(id)) {
//            String email = userDAO.getEmailID(id);
            if (userDAO.deleteUser(id)) {
                emailService.sendEmail(email, "Account Deletion", "Your account has been deleted successfully");
                return new Status(200, "User deleted successfully");
            } else {
                return new Status(500);
            }
        }
        return new Status(404, "Data not found for this user ID");
    }

}
