package com.md.service;

import com.md.model.Group;
import com.md.model.Lecturer;

import java.util.List;

public interface LecturerService {

    Lecturer getLecturerByUsername(String username);

    void save(Lecturer lecturer);

    void saveAll(List<Lecturer> lecturers);
}
