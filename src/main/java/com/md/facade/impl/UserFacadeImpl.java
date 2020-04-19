package com.md.facade.impl;

import com.md.facade.UserFacade;
import com.md.facade.converter.UserConverter;
import com.md.facade.dto.UserData;
import com.md.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl implements UserFacade {
    @Autowired
    private UserService userService;
    @Autowired
    private UserConverter userConverter;

    @Override
    public UserData getUser(String username) {
        return userConverter.convertToDTO(userService.getUser(username));
    }

    @Override
    public UserData authenticate() {
        return null;
    }


}
