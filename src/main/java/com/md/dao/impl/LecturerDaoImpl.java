package com.md.dao.impl;

import com.md.dao.LecturerDao;
import com.md.model.Lecturer;
import com.md.model.Student;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
@Transactional
public class LecturerDaoImpl implements LecturerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Lecturer getLecturer(String username) {
        String query= "from lecturer where username = ?";
        TypedQuery<Lecturer> typedQuery = entityManager.createQuery(query, Lecturer.class);
        typedQuery.setParameter(1, username);
        return typedQuery.getSingleResult();
    }

    public List<Lecturer> getLecturers(){
        String query= "from lecturer order by username";
        TypedQuery<Lecturer> typedQuery = entityManager.createQuery(query, Lecturer.class);
        return typedQuery.getResultList();
    }

    @Override
    public void save(Lecturer lecturer) {
        entityManager.merge(lecturer);
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
