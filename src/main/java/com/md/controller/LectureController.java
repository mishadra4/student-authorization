package com.md.controller;

import com.md.model.Group;
import com.md.model.Lecture;
import com.md.model.User;
import com.md.service.GroupService;
import com.md.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.SpringSessionContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LectureController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private LectureService lectureService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private User user;


    @RequestMapping(value = "/lecture/{id}", method = RequestMethod.GET)
    public ModelAndView getLectures(@PathVariable("id") Integer id){
        ModelAndView lecture = new ModelAndView("/form/createLecture");
        lecture.addObject("lecture", lectureService.getLecture(id));
        lecture.addObject("present", Boolean.TRUE);

        return lecture;
    }

    @RequestMapping(value = "/createLecture", method = RequestMethod.GET)
    public ModelAndView createLecturePage(){
        ModelAndView lecture = new ModelAndView("/form/lecture");
        lecture.addObject("groups", groupService.getAllGroups());
        lecture.addObject("lecturer", user);

        return lecture;
    }

    @RequestMapping(value = "/createLecture", method = RequestMethod.POST)
    public ModelAndView createLecture(){
        ModelAndView lecture = new ModelAndView("/form/lecture");


        return lecture;
    }

    @RequestMapping(value = "/createLab", method = RequestMethod.POST)
    public ModelAndView createLab(){
        ModelAndView lab = new ModelAndView("/form/lecture");


        return lab;
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

    public GroupService getGroupService() {
        return groupService;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
