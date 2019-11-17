package com.md.service;

import com.md.model.Lecture;
import com.md.model.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LectureService {

    Lecture getLecture(Integer id);

    List<Lecture> getLecture(String lecturerUsername);

    void saveLecture(Lecture lecture);

    void enrollStudent(final int lectureId, final Student student);

    void unEnrollStudent(int lectureId, Student student);
}
