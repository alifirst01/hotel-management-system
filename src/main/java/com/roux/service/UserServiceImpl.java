package com.roux.service;

import com.roux.dao.UserData;
import com.roux.model.User;
import com.roux.model.Userroles;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserData UD;

    @Autowired
    private SessionFactory sessionFactory;

    public void save(User user){
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public User getUser(int id) {
        return UD.getUser(id);
    }

    @Override
    public int getUserCount() {
        return UD.getUserCount();
    }

    @Override
    public void addUserRole(int Uid, int Rid) throws HibernateException{
        UD.AddUserRoles(Uid, Rid);
    }

    @Override
    public Userroles validateUser(String Username, String Password){
        return UD.validate(Username, Password);
    }

    @Override
    public int updateUser(User U) {
        return UD.updateUser(U);
    }

    @Override
    public void verifyUser(String username) {
        UD.verifyUser(username);
    }

    @Override
    public String getUserbyUsername(String username) {
        return UD.getUserbyUsername(username);
    }

    @Override
    public String checkEmailAva(String email) {
        return UD.checkEmail(email);
    }
}
