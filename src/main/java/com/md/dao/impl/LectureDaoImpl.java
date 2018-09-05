package com.md.dao.impl;

import com.md.dao.LectureDao;
import org.springframework.stereotype.Repository;
import com.md.model.Lecture;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class LectureDaoImpl implements LectureDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createLecture() {
        Lecture lecture = new Lecture();
        lecture.setLectureId(3);
        entityManager.persist(lecture);
    }
}
