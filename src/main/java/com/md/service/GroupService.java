package com.md.service;

import com.md.model.Group;

import java.util.List;

public interface GroupService {

    List<Group> getAllGroups();

    Group getGroup(String name);
}