package com.md.dao.impl;

import com.md.dao.LabDao;
import com.md.model.Group;
import com.md.model.Lab;
import com.md.model.Lecture;
import com.md.model.Student;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LabDaoImpl implements LabDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createLab() {
        Lab lab = new Lab();
        lab.setLabId(3);
        entityManager.persist(lab);
    }

    @Override
    public Lab getLab(Integer id) {
        Student student = new Student();
        student.setFirstName("Mykhailo");
        student.setLastName("Drach");
        student.setGroup(new Group());
        Lab lab = new Lab();
        lab.setLabId(1);
        return lab;
    }

    @Override
    public List<Lab> getLabs(String lecturerUsername) {
        return null;
    }

    @Override
    public void saveLab(Lab lab) {
        entityManager.persist(lab);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
