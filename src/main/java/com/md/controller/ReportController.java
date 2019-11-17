package com.md.controller;

import com.md.service.ReportService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ReportController {
    private static final String FILE_NAME = "Report.xlsx";
    private static final String FILE_PATH = "/reports/";

    @Autowired
    private ReportService reportService;

    @ResponseBody
    @RequestMapping(value = "/app/report/subject/{subjectName}")
    public void getReport(@PathVariable String subjectName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String realPath = request.getSession().getServletContext().getRealPath(FILE_PATH + subjectName + "_" + FILE_NAME);

        reportService.generateReport(subjectName, realPath);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        try (InputStream is = new FileInputStream(realPath)) {
            IOUtils.copy(is, response.getOutputStream());
        }
        response.flushBuffer();
    }

}
