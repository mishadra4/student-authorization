package com.md.service;

import com.md.model.Lecture;

import java.util.List;

public interface LectureService {

    Lecture getLecture(Integer id);

    List<Lecture> getLecture(String lecturerUsername);

    void saveLecture(Lecture lecture);

}
