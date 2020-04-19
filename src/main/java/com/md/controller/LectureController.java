package com.md.controller;

import com.md.DTO.LectureDTO;
import com.md.facade.converter.LectureConverter;
import com.md.facade.dto.LectureData;
import com.md.facade.dto.StudentData;
import com.md.model.Groups;
import com.md.model.Lecture;
import com.md.model.Lecturer;
import com.md.model.Student;
import com.md.model.Subject;
import com.md.service.GroupService;
import com.md.service.LectureService;
import com.md.service.LecturerService;
import com.md.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang.StringUtils.isEmpty;

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

    @Autowired
    private SubjectService subjectService;

    @RequestMapping(value = "/subject/{subjectId}/lecture", method = RequestMethod.GET)
    public ModelAndView getLecture(final Authentication authentication, @PathVariable final int subjectId) {
        ModelAndView lecture = new ModelAndView("/form/subject");
        User user = (User) authentication.getPrincipal();
        Lecturer lecturer = lecturerService.getLecturerByUsername(user.getUsername());
        Subject subject = subjectService.getSubject(subjectId);
        lecture.addObject("subjects", lecturer.getSubjects());
        lecture.addObject("lectures", subject.getLectures());
        lecture.addObject("subject", subject);

        return lecture;
    }

    @RequestMapping(value = "/lecture/{id}", method = RequestMethod.GET)
    public ModelAndView getLectures(@PathVariable("id") Integer id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        ModelAndView lectureView = new ModelAndView("/form/lecture");
        populateLecture(id, lectureView, user.getUsername());
        return lectureView;
    }

    @RequestMapping(value = "/createLecture", method = RequestMethod.GET)
    public ModelAndView createLecturePage(final Authentication authentication, @RequestParam final int subjectId) {
        User user = (User) authentication.getPrincipal();
        final Subject subject = subjectService.getSubject(subjectId);
        Lecturer lecturer = lecturerService.getLecturerByUsername(user.getUsername());
        ModelAndView lecture = new ModelAndView("/form/createLecture");
        lecture.addObject("groups", groupService.getAllGroups());
        lecture.addObject("lecturer", lecturer);
        lecture.addObject("lecture", new LectureDTO());
        lecture.addObject("subject", subject);
        return lecture;
    }

    @RequestMapping(value = "/submitLecture", method = RequestMethod.POST)
    public ModelAndView createLecture(@ModelAttribute LectureDTO lecture, Authentication authentication, @RequestParam final int subjectId) {
        ModelAndView lectureView = new ModelAndView("/form/lecture");
        User user = (User) authentication.getPrincipal();
        lecture.setLecturerUsername(user.getUsername());
        final Subject subject = subjectService.getSubject(subjectId);
        lecture.setSubject(subject);
        List<Groups> groups = lecture.getGroups().stream()
                .map(groupService::getGroup).collect(Collectors.toList());
        lectureView.addObject("lecture", lecture);
        lectureView.addObject("present", true);
        lectureView.addObject("groups", groups);
//        Lecture l = lectureService.saveLecture(lectureConverter.convertToEntity(lecture));

        return null;//getLectures(l.getLectureId(), authentication);
    }

    private void populateLecture(final int lectureId, final ModelAndView lectureView, final String username) {
        LectureData lectureData = new LectureData();
        Lecture lecture = lectureService.getLecture(lectureId);
        Lecturer lecturer = lecturerService.getLecturerByUsername(username);
        createQrCode(lecture);
        populateStudents(lecture, lectureData);
        lectureView.addObject("lectures", lecture.getSubject().getLectures());
        lectureView.addObject("subjects", lecturer.getSubjects());
        lectureView.addObject("subject", lecture.getSubject());
        lectureView.addObject("groups", lecture.getGroups());
        lectureView.addObject("lecture", lectureData);
    }

    private void createQrCode(Lecture lecture) {
        if (isEmpty(lecture.getQrCodeFilepath())) {
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                final String lectureUrl = "http://" + inetAddress.getHostAddress() + ":8081/student/lecture/" + lecture.getLectureId() + "/enrollStudent";
                lecture.setQrCodeFilepath(lectureUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void populateStudents(final Lecture lecture, final LectureData lectureData) {
        final List<Student> checkedStudents = lecture.getStudents();
        final List<Student> allStudents = lecture.getGroups()
                .stream()
                .map(Groups::getStudents)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        final List<StudentData> populatedStudents = allStudents
                .stream()
                .map(student -> populateStudent(student, checkedStudents))
                .collect(Collectors.toList());
        lectureData.setStudents(populatedStudents);
        lectureData.setFilePath(lecture.getQrCodeFilepath());
        lectureData.setLectureId(lecture.getLectureId());
    }

    private StudentData populateStudent(final Student student, final List<Student> checkedStudents) {
        StudentData studentData = new StudentData();
        studentData.setUsername(student.getUsername());
        studentData.setFirstName(student.getFirstName());
        studentData.setLastName(student.getLastName());
        studentData.setChecked(checkedStudents.contains(student));
        studentData.setGroupName(student.getGroups().getName());
        return studentData;
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

    public SubjectService getSubjectService() {
        return subjectService;
    }

    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

}
