package com.example.giyeo.testbar;

public class UserStatus {

    private static long userId;
    private static String userName;
    private static String userImagePath;
    private static String userLv;
    private static int userTime;
    private static int userCalorie;
    private static int userExp;
    private static int allTime;
    private static int allExp;
    private static int allCalorie;




    public static String getUserName() {
        return userName;
    }

    public static long getUserId() {
        return userId;
    }

    public static String getUserImagePath() {
        return userImagePath;
    }

    public static String getUserLv() { return userLv; }

    public static int getUserTime() { return userTime; }

    public static int getUserCalorie() { return userCalorie; }

    public static int getUserExp() { return userExp; }

    public static void setUserId(long userId) {
        UserStatus.userId = userId;
    }

    public static void setUserName(String userName) {
        UserStatus.userName = userName;
    }

    public static void setUserImagePath(String userImagePath) { UserStatus.userImagePath = userImagePath; }

    public static void setUserLv(String userLv) { UserStatus.userLv = userLv; }

    public static void setUserTime(int userTime) { UserStatus.userTime = userTime; }

    public static void setUserCalorie(int userCalorie) { UserStatus.userCalorie = userCalorie; }

    public static void setUserExp(int userExp) { UserStatus.userExp = userExp; }

    public static int getAllTime() {
        return allTime;
    }

    public static void setAllTime(int allTime) {
        UserStatus.allTime = allTime;
    }

    public static int getAllExp() {
        return allExp;
    }

    public static void setAllExp(int allExp) {
        UserStatus.allExp = allExp;
    }

    public static int getAllCalorie() {
        return allCalorie;
    }

    public static void setAllCalorie(int allCalorie) {
        UserStatus.allCalorie = allCalorie;
    }
}
