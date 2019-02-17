package com.md.controller;

import com.md.csv.CSVLoader;
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


@Controller
public class FileController {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CSVLoader csvLoader;

    @RequestMapping(value = "/uploadStudents", method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {

            try {

                byte[] fileBytes = file.getBytes();
                String rootPath = System.getProperty("catalina.home");
                System.out.println("Server rootPath: " + rootPath);
                System.out.println("File original name: " + file.getOriginalFilename());
                System.out.println("File content type: " + file.getContentType());

                File newFile = new File(rootPath + File.separator + file.getOriginalFilename());

                studentService.saveAll(csvLoader.getStudents(convert(file)));

                return "Students were saved successfully";

            } catch (Exception e) {
                e.printStackTrace();
                return "File upload is failed: " + e.getMessage();
            }
        } else {
            return "File upload is failed: File is empty";
        }
    }


    @RequestMapping(value = "/uploadLecturers", method = RequestMethod.POST)
    public @ResponseBody String handleLecturerUpload(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                lecturerService.saveAll(csvLoader.getLecturers(convert(file)));

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
    public @ResponseBody String handleGroupUpload(@RequestParam("file") MultipartFile file) {

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
    public @ResponseBody String handleSubjectUpload(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {
            try {
                subjectService.saveAll(csvLoader.getSubjects(convert(file)));

                return "Subjects were saved successfully";
            } catch (Exception e) {
                e.printStackTrace();
                return "File upload is failed: " + e.getMessage();
            }
        } else {
            return "File upload is failed: File is empty";
        }
    }

    private File convert(MultipartFile file)
    {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return convFile;
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
