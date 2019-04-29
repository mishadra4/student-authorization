package com.md.controller;

import com.md.model.Student;
import com.md.service.LectureService;
import com.md.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private LectureService lectureService;

    @RequestMapping(value = "/enrollStudent", method = RequestMethod.POST)
    public String enrollStudent(@RequestParam final int lectureId, @RequestParam final String username) {
        final Student student = getStudentService().getStudent(username);
        getLectureService().enrollStudent(lectureId, student);
        return "";
    }

    public LectureService getLectureService() {
        return lectureService;
    }

    public void setLectureService(LectureService lectureService) {
        this.lectureService = lectureService;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }
}
