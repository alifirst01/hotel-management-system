package com.roux.service;

import com.roux.model.User;
import com.roux.model.Userroles;

import java.util.ArrayList;

/**
 * Created by Ali on 11/1/2016.
 */
public interface UserService {
    public void save(User user);
    public User getUser(int id);
    public int getUserCount();
    public void addUserRole(int Uid, int Rid);
    public Userroles validateUser(String Username, String Password);
    public int updateUser(User U);
    public void verifyUser(String username);
    String getUserbyUsername(String username);
    String checkEmailAva(String email);
}
