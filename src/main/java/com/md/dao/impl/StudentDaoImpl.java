package com.md.dao.impl;

import com.md.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.md.dao.StudentDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void save(Student student){
        entityManager.persist(student);
    }

    @Override
    public boolean enrollStudent(String username) {
        String query= "update student set id_lecture=? where username = ?";
        Query nativeQuery = entityManager.createNativeQuery(query);
        nativeQuery.setParameter(1, "0001");
        nativeQuery.setParameter(2, username);
        int result = nativeQuery.executeUpdate();
        return result > 0; // result show how many rows was updated.
    }

    @Override
    public Student getStudent(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student getStudent(String username) {
        String query= "from student as s join user as u on u.student_id = s.student_id where u.username = ?";
        TypedQuery<Student> typedQuery = entityManager.createQuery(query, Student.class);
        typedQuery.setParameter(1, username);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Student> getAllStudents(){
        String query = "from Student order by ID";
        TypedQuery<Student> typedQuery = entityManager.createQuery(query, Student.class);
        return typedQuery.getResultList();
    }

    public boolean deleteStudent(int idStudent) {
        System.out.println("ORMExample deleteUser is called");

        String qlString = "delete from student where idStudent=?";
        Query query = entityManager.createNativeQuery(qlString);
        query.setParameter(1, idStudent);
        int result = query.executeUpdate();

        return result > 0;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
