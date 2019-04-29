package com.md.controller;


import com.md.DTO.LabDTO;
import com.md.facade.converter.LabConverter;
import com.md.model.Groups;
import com.md.model.Lab;
import com.md.service.GroupService;
import com.md.service.LabService;
import com.md.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LabController {

    @Autowired
    private LabService labService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private LabConverter labConverter;

    @RequestMapping(value = "/createLab", method = RequestMethod.POST)
    public ModelAndView createLab(LabDTO lab, Authentication authentication) {
        ModelAndView lectureView = new ModelAndView("/form/lecture");
        User user = (User) authentication.getPrincipal();
        lab.setLecturerUsername(user.getUsername());
        Groups groups = groupService.getGroup(lab.getGroupName());
        lectureView.addObject("lab", lab);
        lectureView.addObject("present", true);
        lectureView.addObject("group", groups);
        labService.saveLab(labConverter.convertToEntity(lab));
        return lectureView;
    }


    @RequestMapping(value = "/lab", method = RequestMethod.GET)
    public ModelAndView getLecture(Authentication authentication) {
        ModelAndView lecture = new ModelAndView("/form/lecture");
        User user = (User) authentication.getPrincipal();
        List<Lab> labs = labService.getLab(user.getUsername());

        lecture.addObject("labs", labs);

        return lecture;
    }

    @RequestMapping(value = "/lab/{id}", method = RequestMethod.GET)
    public ModelAndView getLectures(@PathVariable("id") Integer id) {
        ModelAndView lecture = new ModelAndView("/form/createLab");
        lecture.addObject("lecture", labService.getLab(id));
        lecture.addObject("present", Boolean.TRUE);

        return lecture;
    }


    @RequestMapping(value = "/createLab")
    public ModelAndView createLabPage(Authentication authentication) {
        ModelAndView lab = new ModelAndView("/form/createLab");
        lab.addObject("groups", groupService.getAllGroups());
        lab.addObject("lab", new LabDTO());

        return lab;
    }

    public LabService getLabService() {
        return labService;
    }

    public void setLabService(LabService labService) {
        this.labService = labService;
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
}
