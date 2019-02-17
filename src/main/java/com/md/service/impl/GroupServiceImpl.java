package com.md.service.impl;

import com.md.dao.GroupDao;
import com.md.model.Group;
import com.md.model.Student;
import com.md.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Override
    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();

        Group KN310 = new Group();
        KN310.setCourseNumber(3);
        KN310.setName("KH-310");
        KN310.setStudents(createStudents());

        Group KN311 = new Group();
        KN311.setCourseNumber(3);
        KN311.setName("KH-311");
        KN311.setStudents(createStudents());

        groups.add(KN310);
        groups.add(KN311);

        return groups;
    }

    @Override
    public Group getGroup(String name) {
        Group group = groupDao.getGroup(name);
        group.setStudents(createStudents());
        group.getStudents().forEach(student -> student.setGroup(group));
        return group;
    }

    private List<Student> createStudents(){
        List<Student> students = new ArrayList<>();
        Student student1 = new Student();
        student1.setFirstName("Mykhailo");
        student1.setLastName("Drach");

        Student student2 = new Student();
        student2.setFirstName("Mykhailo");
        student2.setLastName("Drach2");

        students.add(student1);
        students.add(student2);
        return students;
    }

    public GroupDao getGroupDao() {
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }
}
