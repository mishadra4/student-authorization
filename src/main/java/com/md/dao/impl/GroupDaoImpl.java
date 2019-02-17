package com.md.dao.impl;

import com.md.dao.GroupDao;
import com.md.model.Group;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GroupDaoImpl implements GroupDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Group group){
        entityManager.persist(group);
    }

    @Override
    public List<Group> getAllGroups() {
        return null;
    }

    @Override
    public Group getGroup(String name) {
        Group group = new Group();
        group.setName(name);
        return group;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
