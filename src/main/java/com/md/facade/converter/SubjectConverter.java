package com.md.facade.converter;

import com.md.facade.dto.GroupData;
import com.md.facade.dto.SubjectData;
import com.md.model.Groups;
import com.md.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class SubjectConverter implements GenericConverter<Subject, SubjectData> {

    @Autowired
    private GenericConverter<Groups, GroupData> groupConverter;

    @Override
    public Subject convertToEntity(SubjectData dto) {
        Subject subject = new Subject();
        subject.setName(dto.getName());
        return subject;
    }

    @Override
    public SubjectData convertToDTO(Subject entity) {
        SubjectData subjectData = new SubjectData();
        subjectData.setId(entity.getId());
        subjectData.setName(entity.getName());
        subjectData.setLecturerUsername(entity.getLecturer().getUsername());
        subjectData.setGroups(entity.getGroups().stream()
                .map(groupConverter::convertToDTO)
                .collect(Collectors.toList()));
        return subjectData;
    }
}
