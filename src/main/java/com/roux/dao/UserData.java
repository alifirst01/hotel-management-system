package com.roux.dao;

import com.roux.model.User;
import com.roux.model.Userroles;

import java.util.ArrayList;

public interface UserData {
    boolean AddUserRoles(int UID, int RID);
    Userroles validate(String Username, String Password);
    public User getUser(int id);
    public int updateUser(User U);
    public int getUserCount();
    int verifyUser(String username);
    String getUserbyUsername(String username);
    String checkEmail(String email);
}
