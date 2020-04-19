package com.md.service;

import com.md.model.Lab;

import java.util.List;

public interface LabService {

    Lab getLab(Integer id);

    List<Lab> getLab(String lecturerUsername);

    void saveLab(Lab lab);

}
