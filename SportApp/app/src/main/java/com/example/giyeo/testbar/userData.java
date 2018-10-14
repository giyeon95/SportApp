package com.example.giyeo.testbar;

import java.io.Serializable;

public class userData implements Serializable{

    private String userName;
    private String userLv;
    private String userTime;
    private String userCalorie;
    private String userImage;

    public userData() {}

    public userData(String userName, String userLv, String userTime, String userCalorie, String userImage) {
        this.userName = userName;
        this.userLv = userLv;
        this.userTime = userTime;
        this.userCalorie = userCalorie;
        this.userImage = userImage;
    }


    public String getUserName() {
        return userName;
    }

    public String getUserLv() {
        return userLv;
    }

    public String getUserTime() {
        return userTime;
    }

    public String getUserCalorie() {
        return userCalorie;
    }

    public String getUserImage() {
        return userImage;
    }
}
