package com.md.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.md.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Collections;
import java.util.List;


@Component
public class CSVLoader {
    private static Logger LOG = LoggerFactory.getLogger(CSVLoader.class);

    public <T> List<T> loadObjectList(Class<T> type, File file) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
            CsvMapper mapper = new CsvMapper();
            MappingIterator<T> readValues =
                    mapper.reader(type).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            LOG.error("Error occurred while loading object list from file " + file.getName(), e);
            return Collections.emptyList();
        }
    }

    public List<Student> getStudents(File file) {
        return loadObjectList(Student.class, file);
    }
}
