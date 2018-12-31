package com.md.dao;

import com.md.model.Lecture;

public interface LectureDao {
    void createLecture();

    Lecture getLecture(Integer id);
}
