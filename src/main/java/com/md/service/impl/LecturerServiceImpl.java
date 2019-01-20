package com.md.service.impl;

import com.md.model.Lecturer;
import com.md.service.LecturerService;
import org.springframework.stereotype.Service;

@Service
public class LecturerServiceImpl implements LecturerService {

    @Override
    public Lecturer getLecturerByUsername(String username) {
        Lecturer lecturer = new Lecturer();
        lecturer.setUsername("admin@gmail.com");
        lecturer.setFirstName("Andrii");
        lecturer.setLastName("Ostapenko");
        return lecturer;
    }
}
