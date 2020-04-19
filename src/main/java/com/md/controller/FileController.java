package com.md.controller;

import com.md.csv.CSVLoader;
import com.md.dao.AuthorityDao;
import com.md.facade.dto.StudentData;
import com.md.facade.dto.SubjectData;
import com.md.model.Authority;
import com.md.model.Lecturer;
import com.md.model.Student;
import com.md.model.Subject;
import com.md.service.GroupService;
import com.md.service.LecturerService;
import com.md.service.StudentService;
import com.md.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static java.lang.Boolean.TRUE;


@Controller
public class FileController {

    private static final String ROLE_USER = "ROLE_USER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_SUPER_USER = "ROLE_SUPER_USER";

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private AuthorityDao authorityDao;

    @Autowired
    private CSVLoader csvLoader;

    @RequestMapping(value = "/uploadStudents", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {

            try {
                final List<StudentData> studentDataList = csvLoader.getStudents(convert(file));
                saveStudents(studentDataList);

                return "Students were saved successfully";

            } catch (Exception e) {
                e.printStackTrace();
                return "File upload is failed: " + e.getMessage();
            }
        } else {
            return "File upload is failed: File is empty";
        }
    }

    private void saveStudents(List<StudentData> studentDataList) {
        studentDataList.forEach(this::saveStudent);
    }

    private void saveStudent(StudentData studentData) {
        Student student = studentService.getStudent(studentData.getUsername());
        if (student == null) {
            student = new Student();
            student.setUsername(studentData.getUsername());
            student.setLastName(studentData.getLastName());
            student.setFirstName(studentData.getFirstName());
            student.setId(studentData.getId());
            student.setPassword(studentData.getPassword());
            student.setGroups(groupService.getGroup(studentData.getGroupName()));
            student.setEnabled(TRUE);
            studentService.save(student);
            createAuthority(studentData.getUsername(), ROLE_USER);
        }
    }

    private void createAuthority(final String username, String authorityName) {
        Authority authority = new Authority();
        authority.setAuthorityName(authorityName);
        authority.setUsername(username);
        authorityDao.save(authority);
    }

    @RequestMapping(value = "/uploadLecturers", method = RequestMethod.POST)
    public @ResponseBody
    String handleLecturerUpload(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {

                List<Lecturer> lecturers = csvLoader.getLecturers(convert(file));
                lecturerService.saveAll(lecturers);
                lecturers.forEach(lecturer -> createAuthority(lecturer.getUsername(), ROLE_ADMIN));

                return "Lecturers were saved successfully";
            } catch (Exception e) {
                e.printStackTrace();
                return "File upload is failed: " + e.getMessage();
            }
        } else {
            return "File upload is failed: File is empty";
        }
    }

    @RequestMapping(value = "/uploadGroups", method = RequestMethod.POST)
    public @ResponseBody
    String handleGroupUpload(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                groupService.saveAll(csvLoader.getGroups(convert(file)));

                return "Groups were saved successfully";
            } catch (Exception e) {
                e.printStackTrace();
                return "File upload is failed: " + e.getMessage();
            }
        } else {
            return "File upload is failed: File is empty";
        }
    }


    @RequestMapping(value = "/uploadSubjects", method = RequestMethod.POST)
    public @ResponseBody
    String handleSubjectUpload(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                List<SubjectData> subjectDataList = csvLoader.getSubjects(convert(file));
                saveSubjects(subjectDataList);
                return "Subjects were saved successfully";
            } catch (Exception e) {
                e.printStackTrace();
                return "File upload is failed: " + e.getMessage();
            }
        } else {
            return "File upload is failed: File is empty";
        }
    }

    private File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convFile;
    }

    private void saveSubjects(final List<SubjectData> subjectDataList) {
        subjectDataList.forEach(this::saveSubject);
    }

    private void saveSubject(final SubjectData subjectData) {
        Subject subject = subjectService.getSubject(subjectData.getName());
        if (subject == null) {
            subject = new Subject();
            subject.setName(subjectData.getName());
            subject.setLecturer(lecturerService.getLecturerByUsername(subjectData.getLecturerUsername()));
        }
        if (subject.getGroups() != null) {
            subject.getGroups().add(groupService.getGroup(subjectData.getGroupNames().get(0)));
        } else {
            subject.setGroups(new HashSet<>(Collections.singleton(groupService.getGroup(subjectData.getGroupNames().get(0)))));
        }
        subjectService.save(subject);
    }

    public CSVLoader getCsvLoader() {
        return csvLoader;
    }

    public void setCsvLoader(CSVLoader csvLoader) {
        this.csvLoader = csvLoader;
    }

    public void setLecturerService(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
}
