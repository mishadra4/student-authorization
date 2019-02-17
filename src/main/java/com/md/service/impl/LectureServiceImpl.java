package com.md.service.impl;

import com.md.dao.LectureDao;
import com.md.model.Lecture;
import com.md.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureServiceImpl implements LectureService {

    @Autowired
    private LectureDao lectureDao;

    @Override
    public Lecture getLecture(Integer id) {
        return lectureDao.getLecture(id);
    }

    @Override
    public List<Lecture> getLecture(String lecturerUsername) {
        return lectureDao.getLectures(lecturerUsername);
    }

    @Override
    public void saveLecture(Lecture lecture) {
        lectureDao.saveLecture(lecture);
    }

    public LectureDao getLectureDao() {
        return lectureDao;
    }

    public void setLectureDao(LectureDao lectureDao) {
        this.lectureDao = lectureDao;
    }
}
