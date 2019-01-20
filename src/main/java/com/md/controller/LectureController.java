package com.md.controller;

import com.md.model.Group;
import com.md.model.Lecture;
import com.md.model.Lecturer;
import com.md.service.GroupService;
import com.md.service.LectureService;
import com.md.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.SpringSessionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LectureController {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private LectureService lectureService;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/lecture/", method = RequestMethod.GET)
    public ModelAndView getLecture(){
        ModelAndView lecture = new ModelAndView("/form/lecture");

        return lecture;
    }

    @RequestMapping(value = "/lecture/{id}", method = RequestMethod.GET)
    public ModelAndView getLectures(@PathVariable("id") Integer id){
        ModelAndView lecture = new ModelAndView("/form/createLecture");
        lecture.addObject("lecture", lectureService.getLecture(id));
        lecture.addObject("present", Boolean.TRUE);

        return lecture;
    }

    @RequestMapping(value = "/createLecture")
    public ModelAndView createLecturePage(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Lecturer lecturer = lecturerService.getLecturerByUsername(user.getUsername());
        ModelAndView lecture = new ModelAndView("/form/createLecture");
        lecture.addObject("groups", groupService.getAllGroups());
        lecture.addObject("lecturer", lecturer);

        return lecture;
    }

    @RequestMapping(value = "/createLecture", method = RequestMethod.POST)
    public ModelAndView createLecture(Lecture lecture, Authentication authentication){
        ModelAndView lectureView = new ModelAndView("/form/lecture");
        User user = (User) authentication.getPrincipal();
        Lecturer lecturer = lecturerService.getLecturerByUsername(user.getUsername());
        lecture.setLecturer(lecturer);
        lecture.setGroups(groupService.getAllGroups());
        lectureView.addObject("lecture", lecture);
        lectureView.addObject("present", true);
        lectureService.saveLecture(lecture);
        return lectureView;
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
}
