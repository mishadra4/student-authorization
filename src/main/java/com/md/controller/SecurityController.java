package com.md.controller;

import com.md.dao.LectureDao;
import com.md.dao.StudentDao;
import com.md.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;


@Controller
public class SecurityController {

    private StudentDao studentDao;

    @Autowired
    private LectureDao lectureDao;

    @RequestMapping(value = "/")
    public ModelAndView login(){
        return new ModelAndView("/index");
    }

    @RolesAllowed(value = "ROLE_USER")
    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public ModelAndView userCheckoutSuccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        studentDao.enrollStudent(user.getUsername());
        List<Student> students = studentDao.getAllStudents();
        for (Student student :
                students) {
            System.out.println("Student username: " + student.getUsername());
            System.out.println();
        }

        System.out.println(studentDao.getStudent(user.getUsername()).getUsername());
        return new ModelAndView("form/success");
    }

    @RolesAllowed(value = "ROLE_ADMIN")
    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public ModelAndView teacherPage() {
        return new ModelAndView("form/teacher");
    }

/*    @RolesAllowed(value = "ROLE_ADMIN")
    @RequestMapping(value = "/createLecture", method = RequestMethod.GET)
    public ModelAndView createLecture() {
        lectureDao.createLecture();
        return new ModelAndView("form/teacher");
    }*/


    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public void setLectureDao(LectureDao lectureDao) {
        this.lectureDao = lectureDao;
    }
}
