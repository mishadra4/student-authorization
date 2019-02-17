package com.md.controller;

import com.md.csv.CSVLoader;
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
    private CSVLoader csvLoader;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()) {

            try {

                byte[] fileBytes = file.getBytes();
                String rootPath = System.getProperty("catalina.home");
                System.out.println("Server rootPath: " + rootPath);
                System.out.println("File original name: " + file.getOriginalFilename());
                System.out.println("File content type: " + file.getContentType());

                File newFile = new File(rootPath + File.separator + file.getOriginalFilename());

                csvLoader.getStudents(convert(file));

                return "File is saved under: " + rootPath + File.separator + file.getOriginalFilename();

            } catch (Exception e) {
                e.printStackTrace();
                return "File upload is failed: " + e.getMessage();
            }
        } else {
            return "File upload is failed: File is empty";
        }
    }

    public File convert(MultipartFile file)
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
}
