package com.md.facade.converter;

import com.md.facade.dto.UserData;
import com.md.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements GenericConverter<User, UserData> {
    @Override
    public User convertToEntity(UserData dto) {
        return null;
    }

    @Override
    public UserData convertToDTO(User entity) {
        UserData userData = new UserData();
        userData.setUsername(entity.getUsername());
        userData.setFirstName(entity.getFirstName());
        userData.setLastName(entity.getLastName());
        return userData;
    }
}
