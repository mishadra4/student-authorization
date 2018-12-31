package com.md.controller;

import com.md.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LectureController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private LectureService lectureService;

    @RequestMapping(value = "/lecture{id}", method = RequestMethod.GET)
    public ModelAndView getLectures(@PathVariable("id")Integer id){
        ModelAndView lecture = new ModelAndView("/lecture");

        lecture.addObject("lecture", lectureService.getLecture(id));

        return lecture;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public LectureService getLectureService() {
        return lectureService;
    }

    public void setLectureService(LectureService lectureService) {
        this.lectureService = lectureService;
    }
}
