package com.md.dao;

import com.md.model.Group;

import java.util.List;

public interface GroupDao {

    void save(Group group);

    List<Group> getAllGroups();

    Group getGroup(String name);
}
