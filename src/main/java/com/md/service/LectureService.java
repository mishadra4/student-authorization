package com.md.service;

import com.md.model.Lecture;

public interface LectureService {

    Lecture getLecture(Integer id);

    void saveLecture(Lecture lecture);

}
