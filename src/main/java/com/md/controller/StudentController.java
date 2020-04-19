package com.md.controller;

import com.md.facade.dto.LectureData;
import com.md.model.Student;
import com.md.service.LectureService;
import com.md.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private LectureService lectureService;

    @RequestMapping(value = "/student/enrollStudent", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity enrollStudent(@ModelAttribute final LectureData lecture) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/student/lecture/{lectureId}/enrollStudent")
    @ResponseBody
    public ResponseEntity enrollStudent(@PathVariable final int lectureId, final Authentication authentication, @RequestParam boolean isPresent) {
        User user = (User) authentication.getPrincipal();
        enrollStudent(lectureId, user.getUsername(), isPresent);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/lecture/{lectureId}/student/{username}/enrollStudent", method = RequestMethod.POST)
    public String enrollStudent(@PathVariable final int lectureId, @PathVariable final String username, @RequestParam boolean isPresent) {
        Student student = studentService.getStudent(username);
        if (isPresent) {
            lectureService.unEnrollStudent(lectureId, student);
        } else {
            lectureService.enrollStudent(lectureId, student);
        }
        return "redirect: /lecture/" + lectureId;
    }

    @ResponseBody
    @RequestMapping(value = "/lecture/{lectureId}/student/{username}/enroll", method = RequestMethod.POST)
    public ResponseEntity enrollStudent(@PathVariable final int lectureId, @PathVariable final String username) {
        Student student = studentService.getStudent(username);
        boolean isPresent = lectureService.getLecture(lectureId).getStudents().contains(student);
        if (isPresent) {
            lectureService.unEnrollStudent(lectureId, student);
        } else {
            lectureService.enrollStudent(lectureId, student);
        }
        return ResponseEntity.ok().build();
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
