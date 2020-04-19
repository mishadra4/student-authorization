package com.md.dao.impl;

import com.md.dao.UserDao;
import com.md.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUser(String username) {
        String query= "from User where username = ?";
        TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);
        typedQuery.setParameter(1, username);
        return typedQuery.getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }
}
