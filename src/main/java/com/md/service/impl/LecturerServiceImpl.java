package com.md.service.impl;

import com.md.dao.LecturerDao;
import com.md.model.Lecturer;
import com.md.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturerServiceImpl implements LecturerService {

    @Autowired
    private LecturerDao lecturerDao;

    @Override
    public Lecturer getLecturerByUsername(String username) {
       return lecturerDao.getLecturer(username);
    }

    @Override
    public void save(Lecturer lecturer) {
        getLecturerDao().save(lecturer);
    }

    @Override
    public void saveAll(List<Lecturer> lecturers) {
        lecturers.forEach(this::save);
    }

    public LecturerDao getLecturerDao() {
        return lecturerDao;
    }

    public void setLecturerDao(LecturerDao lecturerDao) {
        this.lecturerDao = lecturerDao;
    }
}
