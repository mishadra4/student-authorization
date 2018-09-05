package com.md.dao;

import com.md.model.Student;

import java.util.List;

public interface StudentDao {
    boolean enrollStudent(String username);
    Student getStudent(Integer id);
    Student getStudent(String username);
    List<Student> getAllStudents();

}
