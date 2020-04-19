package com.md.service.impl;

import com.md.dao.UserDao;
import com.md.model.User;
import com.md.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public User getUser(final String username) {
        return userDao.getUser(username);
    }
}
