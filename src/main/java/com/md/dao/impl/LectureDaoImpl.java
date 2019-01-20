package com.md.dao.impl;

import com.md.dao.LectureDao;
import com.md.model.Group;
import com.md.model.Student;
import org.springframework.stereotype.Repository;
import com.md.model.Lecture;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;

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

    @Override
    public Lecture getLecture(Integer id) {
        Student student = new Student();
        student.setFirstName("Mykhailo");
        student.setLastName("Drach");
        student.setGroup(new Group());
        student.setPresent(false);
        Lecture lecture = new Lecture();
        lecture.setLectureId(1);
        return lecture;
    }

    @Override
    public void saveLecture(Lecture lecture) {

    }
}
