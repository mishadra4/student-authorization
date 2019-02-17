package com.md.dao.impl;

import com.md.dao.GroupDao;
import com.md.model.Group;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupDaoImpl implements GroupDao {
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
}
