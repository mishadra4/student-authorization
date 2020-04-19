package com.md.facade.converter;

import com.md.facade.dto.GroupData;
import com.md.facade.dto.StudentData;
import com.md.model.Groups;
import com.md.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GroupConverter implements GenericConverter<Groups, GroupData> {

    @Autowired
    private GenericConverter<Student, StudentData> studentConverter;

    @Override
    public Groups convertToEntity(GroupData dto) {
        return null;
    }

    @Override
    public GroupData convertToDTO(Groups entity) {
        GroupData dto = new GroupData();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setStudents(entity.getStudents().stream()
                .map(studentConverter::convertToDTO)
                .collect(Collectors.toList()));
        return dto;
    }
}
