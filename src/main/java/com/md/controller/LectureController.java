package com.md.controller;

import com.md.DTO.LectureDTO;
import com.md.facade.converter.LectureConverter;
import com.md.model.Group;
import com.md.model.Lecture;
import com.md.model.Lecturer;
import com.md.service.GroupService;
import com.md.service.LectureService;
import com.md.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private LectureConverter lectureConverter;

    @RequestMapping(value = "/lecture", method = RequestMethod.GET)
    public ModelAndView getLecture(Authentication authentication){
        ModelAndView lecture = new ModelAndView("/form/lecture");
        User user = (User) authentication.getPrincipal();
        List<Lecture> lectures = lectureService.getLecture(user.getUsername());

        lecture.addObject("lectures", lectures);

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
        lecture.addObject("lecture", new Lecture());
        return lecture;
    }

    @RequestMapping(value = "/createLecture", method = RequestMethod.POST)
    public ModelAndView createLecture(@ModelAttribute LectureDTO lecture, Authentication authentication){
        ModelAndView lectureView = new ModelAndView("/form/lecture");
        User user = (User) authentication.getPrincipal();
        lecture.setLecturerUsername(user.getUsername());
//        List<Group> groups = lecture.getGroups().stream()
//                .map(groupService::getGroup).collect(Collectors.toList());
        lectureView.addObject("lecture", lecture);
        lectureView.addObject("present", true);
//        lectureView.addObject("groups", groups);
        lectureService.saveLecture(lectureConverter.convertToEntity(lecture));
        return lectureView;
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

    public LecturerService getLecturerService() {
        return lecturerService;
    }

    public void setLecturerService(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    public LectureConverter getLectureConverter() {
        return lectureConverter;
    }

    public void setLectureConverter(LectureConverter lectureConverter) {
        this.lectureConverter = lectureConverter;
    }
}
