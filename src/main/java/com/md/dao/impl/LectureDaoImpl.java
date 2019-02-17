package com.md.dao.impl;

import com.md.dao.LectureDao;
import com.md.model.Group;
import com.md.model.Lecture;
import com.md.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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
        Lecture lecture = new Lecture();
        lecture.setLectureId(1);
        lecture.setName("Lecture 1");
        return lecture;
    }

    @Override
    public List<Lecture> getLectures(String lecturerUsername) {
        String query = "select l from lecture as l left join l.lecturer as lr where lr.username =? order by l.ordinalNumber";
        TypedQuery<Lecture> typedQuery = entityManager.createQuery(query, Lecture.class);
        typedQuery.setParameter(1, lecturerUsername);
        return typedQuery.getResultList();
    }

    @Transactional
    @Override
    public void saveLecture(Lecture lecture) {
        entityManager.merge(lecture);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
