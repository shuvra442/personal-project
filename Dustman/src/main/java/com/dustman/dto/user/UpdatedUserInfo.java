package com.dustman.dto.user;

public class UpdatedUserInfo {

    private String userId;
    private String username;
    private String image;
    private String imageId;
    private String userRoc;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserRoc() {
        return userRoc;
    }

    public void setUserRoc(String userRoc) {
        this.userRoc = userRoc;
    }

    public UpdatedUserInfo(String userId,String username, String image, String userRoc, String imageId) {
        this.userId = userId;
        this.username = username;
        this.image = image;
        this.userRoc = userRoc;
        this.imageId = imageId;
    }

    public UpdatedUserInfo() {
    }
}
