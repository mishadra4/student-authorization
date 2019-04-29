package com.md.dao;

import com.md.model.Groups;

import java.util.List;

public interface GroupDao {

    void save(Groups groups);

    List<Groups> getAllGroups();

    Groups getGroup(String name);
}
