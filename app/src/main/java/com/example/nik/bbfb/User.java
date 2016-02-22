package com.example.nik.bbfb;

/**
 * Created by nik on 21/02/2016.
 */
public class User {
    public static String uName;
    public static String ID;

    public static String getuName() {
        return uName;
    }

    public static void setuName(String uName) {
        User.uName = uName;
    }

    public static String getID() {
        return ID;
    }

    public static void setID(String ID) {
        User.ID = ID;
    }

    public static String getBalance() {
        return balance;
    }

    public static void setBalance(String balance) {
        User.balance = balance;
    }

    public static String balance;
}
