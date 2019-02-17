package com.md.dao;

import com.md.model.Lab;
import com.md.model.Lecture;

import java.util.List;

public interface LabDao {
    void createLab();

    Lab getLab(Integer id);

    List<Lab> getLabs(String lecturerUsername);

    void saveLab(Lab lab);
}
