package com.md.dao.impl;

import com.md.dao.AuthorityDao;
import com.md.model.Authority;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class AuthorityDaoImpl implements AuthorityDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Authority authority) {
        entityManager.merge(authority);
    }
}
