package com.roux.dao;

import com.roux.model.User;
import com.roux.model.Userroles;
import org.hibernate.*;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("UserData")
    public class UserDataImpl extends Abstract<Integer, User> implements UserData {
    static final Logger logger = LoggerFactory.getLogger(UserDataImpl.class);
    private Connection Conn;
    private Statement statement;
    PreparedStatement addUserRoles = null;
    PreparedStatement updateUserData = null;

    @Override
    public boolean AddUserRoles(int UID, int RID) throws HibernateException {
        int Ans = -1;
        try {
            Conn = ((SessionImpl) getSession()).connection();
            statement = Conn.createStatement();
            addUserRoles = Conn.prepareStatement("insert into userroles values(?,?);");
            addUserRoles.setInt(1, UID);
            addUserRoles.setInt(2, RID);
            Ans = addUserRoles.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (Ans == -1)
            return false;
        return true;
    }

    @Override
    public Userroles validate(String Username, String Password) {

        SQLQuery ids = getSession().createSQLQuery("select * from user;");
        ids.addEntity(User.class);
        List<User> U = ids.list();
        Userroles userroles = new Userroles();

        for(int i = 0; i < U.size(); i++) {
            if (U.get(i).getUsername().compareTo(Username) == 0 && U.get(i).getPassword().compareTo(Password) == 0) {
                SQLQuery UR = getSession().createSQLQuery("select * from userroles where UID = " + Integer.toString(U.get(i).getId()));
                UR.addEntity(Userroles.class);
                List<Userroles> R = UR.list();
                userroles.setUid(R.get(0).getUid());
                userroles.setRid(R.get(0).getRid());
                return userroles;
            }
        }
        userroles.setRid(-1);
        return userroles;
    }

    @Override
    public User getUser(int id) {
        SQLQuery U = getSession().createSQLQuery("select * from user where ID = " + Integer.toString(id));
        U.addEntity(User.class);
        List<User> R = U.list();
        return R.get(0);
    }

    @Override
    public int updateUser(User U) {
        int Ans = -1;
        try {
            Conn = ((SessionImpl) getSession()).connection();
            statement = Conn.createStatement();
            updateUserData = Conn.prepareStatement("UPDATE user set first_name = COALESCE(?, first_name), last_name = COALESCE(?, last_name), username = COALESCE(?, username), password = COALESCE(?, password), contact = COALESCE(?, contact), email = COALESCE(?, email), country = COALESCE(?, country), zip_code = COALESCE(?, zip_code) where ID = ?;");
            updateUserData.setString(1, U.getFirstName());
            updateUserData.setString(2, U.getLastName());
            updateUserData.setString(3, U.getUsername());
            updateUserData.setString(4, U.getPassword());
            updateUserData.setString(5, U.getContact());
            updateUserData.setString(6, U.getEmail());
            updateUserData.setString(7, U.getCountry());
            updateUserData.setString(8, U.getZipCode());
            updateUserData.setInt(9, U.getId());
            Ans = updateUserData.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Ans;
    }

    @Override
    public int getUserCount() {
        SQLQuery U = getSession().createSQLQuery("select * from user");
        U.addEntity(User.class);
        List<User> R = U.list();
        return R.size();
    }

    @Override
    public int verifyUser(String username) {
        int Ans = -1;
        try {
            Conn = ((SessionImpl) getSession()).connection();
            statement = Conn.createStatement();
            PreparedStatement setToken = Conn.prepareStatement("UPDATE user set token = 'Verified' where username = ?");
            setToken.setString(1, username);
            Ans = setToken.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Ans;
    }

    @Override
    public String getUserbyUsername(String username) {
        SQLQuery U = getSession().createSQLQuery("select * from user where username = '" + username + "'");
        U.addEntity(User.class);
        List<User> R = U.list();
        String message;
        if(R.size() == 0)
            message = "Success";
        else
            message = "Failure";
        return message;
    }

    @Override
    public String checkEmail(String email) {
        SQLQuery U = getSession().createSQLQuery("select * from user where email = '" + email + "'");
        U.addEntity(User.class);
        List<User> R = U.list();
        String message;
        if(R.size() == 0)
            message = "Success";
        else
            message = "Failure";
        return message;
    }

}
