package com.md.service.impl;


import com.md.dao.StudentDao;
import com.md.model.Student;
import com.md.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public void studentRegister(Integer studentId) {
    }

    @Override
    public void save(Student student) {
        getStudentDao().save(student);
    }

    @Override
    public void saveAll(List<Student> students) {
        students.forEach(this::save);
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
}
