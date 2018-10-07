package com.example.giyeo.testbar;

public class UserStatus {

    private static long userId;
    private static String userName;
    private static String userImagePath;


    public static String getUserName() {
        return userName;
    }

    public static long getUserId() {
        return userId;
    }

    public static String getUserImagePath() {
        return userImagePath;
    }

    public static void setUserId(long userId) {
        UserStatus.userId = userId;
    }

    public static void setUserName(String userName) {
        UserStatus.userName = userName;
    }

    public static void setUserImagePath(String userImagePath) {
        UserStatus.userImagePath = userImagePath;
    }


}
