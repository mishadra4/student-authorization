package com.md.service.impl;


import com.md.dao.StudentDao;
import com.md.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentsServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public void studentRegister(Integer studentId) {
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
}
