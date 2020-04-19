package com.md.dao;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsDao {
    UserDetails getUser(String username);
}
