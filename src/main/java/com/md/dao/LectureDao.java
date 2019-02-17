package com.md.dao;

import com.md.model.Lecture;

import java.util.List;


public interface LectureDao {
    void createLecture();

    Lecture getLecture(Integer id);

    List<Lecture> getLectures(String lecturerUsername);

    void saveLecture(Lecture lecture);
}
