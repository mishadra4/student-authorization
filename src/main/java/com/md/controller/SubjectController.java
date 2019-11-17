package com.md.controller;

import com.md.model.Lecturer;
import com.md.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/subject")
public class SubjectController {

    private static final String SUBJECT_PAGE = "/form/subject";

    @Autowired
    private LecturerService lecturerService;

    @RequestMapping(method = RequestMethod.GET)
    private ModelAndView getMainPage(final Authentication authentication)
    {
        ModelAndView modelAndView = new ModelAndView(SUBJECT_PAGE);
        User user = (User) authentication.getPrincipal();
        Lecturer lecturer = lecturerService.getLecturerByUsername(user.getUsername());
        modelAndView.addObject("subjects", lecturer.getSubjects());
        return modelAndView;
    }

    public LecturerService getLecturerService() {
        return lecturerService;
    }

    public void setLecturerService(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }
}
