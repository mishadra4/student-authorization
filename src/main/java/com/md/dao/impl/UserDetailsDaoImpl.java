package com.md.dao.impl;

import com.md.dao.UserDetailsDao;
import com.md.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class UserDetailsDaoImpl implements UserDetailsDao {
    private EntityManager entityManager;

    @Override
    public UserDetails getUser(String username) {
        String query = "from User where username = ?";
        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
        typedQuery.setParameter(1, username);
        return typedQuery.getResultList()
                .stream()
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
