package com.md.service.impl;

import com.md.model.Groups;
import com.md.model.Lecture;
import com.md.model.Student;
import com.md.model.Subject;
import com.md.service.ReportService;
import com.md.service.SubjectService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {
    private static final String PRESENT = "Присутній";
    private static final String NON_PRESENT = "Відсутній";
    @Autowired
    private SubjectService subjectService;

    @Override
    public String generateReport(final String subjectName, final String filePath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Список відвідування");

            Subject subject = subjectService.getSubject(subjectName);

            Set<Lecture> lectures = subject.getLectures();
            List<Student> students = subject.getGroups()
                    .stream()
                    .map(Groups::getStudents)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());

            createLectureRow(lectures, sheet);
            int rowCount = 0;

            for (Student student : students) {
                Row row = sheet.createRow(++rowCount);

                int columnCount = 0;
                Cell studentCell = row.createCell(columnCount);
                studentCell.setCellValue(student.getFirstName() + " " + student.getLastName());
                CellStyle cs = workbook.createCellStyle();
                cs.setWrapText(true);
                studentCell.setCellStyle(cs);
                for (Lecture lecture : lectures) {
                    Cell cell = row.createCell(++columnCount);
                    final boolean isPresent = lecture.getStudents().contains(student);
                    cell.setCellValue(isPresent ? PRESENT : NON_PRESENT);
                }

            }


            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            try (BufferedOutputStream bos = new BufferedOutputStream(fileOut)) {
                bos.flush();
            }
            fileOut.close();
        }
        return filePath;
    }

    private void createLectureRow(Set<Lecture> lectures, Sheet sheet) {
        Row row = sheet.createRow(0);
        int columnCount = 1;
        for (Lecture lecture : lectures) {
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(lecture.getName());
        }
    }
}
