package com.md.facade;

import com.md.facade.dto.UserData;

public interface UserFacade {

    UserData authenticate();

    UserData getUser(String username);

}
