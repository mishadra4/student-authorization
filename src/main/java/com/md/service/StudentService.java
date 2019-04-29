package com.md.service;

import com.md.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {

    void studentRegister(Integer studentId);

    void save(Student student);

    void saveAll(List<Student> students);

    Student getStudent(final String username);

}
