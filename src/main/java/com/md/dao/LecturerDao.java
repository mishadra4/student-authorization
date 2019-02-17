package com.md.dao;

import com.md.model.Lecturer;

import java.util.List;

public interface LecturerDao {

    void save(Lecturer lecturer);

    Lecturer getLecturer(String username);

    List<Lecturer> getLecturers();
}
