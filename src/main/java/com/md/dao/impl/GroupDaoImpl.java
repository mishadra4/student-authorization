package com.md.dao.impl;

import com.md.dao.GroupDao;
import com.md.model.Groups;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GroupDaoImpl implements GroupDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Groups groups){
        entityManager.persist(groups);
    }

    @Override
    public List<Groups> getAllGroups() {
        String query = "from Groups";
        TypedQuery<Groups> typedQuery = entityManager.createQuery(query, Groups.class);
        return typedQuery.getResultList();
    }

    @Override
    public Groups getGroup(String name) {
        String query= "from Groups where name = ?";
        TypedQuery<Groups> typedQuery = entityManager.createQuery(query, Groups.class);
        typedQuery.setParameter(1, name);
        return typedQuery.getSingleResult();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
