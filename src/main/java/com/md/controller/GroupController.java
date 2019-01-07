package com.md.controller;

import com.md.model.Group;
import com.md.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GroupController {

    @Autowired
    GroupService groupService;

    public List<Group> getAllGroups(){
        return groupService.getAllGroups();
    }

}
