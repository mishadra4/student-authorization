package com.md.service;

import com.md.model.Groups;

import java.util.List;

public interface GroupService {

    List<Groups> getAllGroups();

    Groups getGroup(String name);

    void save(Groups groups);

    void saveAll(List<Groups> groups);
}