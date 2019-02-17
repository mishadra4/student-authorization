package com.md.dao;

import com.md.model.Group;

import java.util.List;

public interface GroupDao {

    List<Group> getAllGroups();

    Group getGroup(String name);
}
