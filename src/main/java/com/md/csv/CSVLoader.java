package com.md.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.md.model.Group;
import com.md.model.Lecturer;
import com.md.model.Student;
import com.md.model.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.List;


@Component
public class CSVLoader {
    private static Logger LOG = LoggerFactory.getLogger(CSVLoader.class);

    private  <T> List<T> loadObjectList(Class<T> type, File file) {
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

    public List<Lecturer> getLecturers(File file) {
        return loadObjectList(Lecturer.class, file);
    }

    public List<Subject> getSubjects(File file) {
        return loadObjectList(Subject.class, file);
    }

    public List<Group> getGroups(File file) {
        return loadObjectList(Group.class, file);
    }
}
