package com.md.facade.impl;

import com.md.facade.LectureFacade;
import com.md.facade.converter.GenericConverter;
import com.md.facade.dto.LectureData;
import com.md.model.Lecture;
import com.md.service.LectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LectureFacadeImpl implements LectureFacade {

    @Autowired
    private LectureService lectureService;
    @Autowired
    private GenericConverter<Lecture, LectureData> lectureConverter;

    @Override
    public LectureData getLecture(final int id) {
        return lectureConverter.convertToDTO(lectureService.getLecture(id));
    }

    @Override
    public void createLecture(final LectureData lectureData) {
        lectureService.saveLecture(lectureConverter.convertToEntity(lectureData));
    }

    @Override
    public List<LectureData> getLectures(final String subjectId) {
        return lectureService.getLectures(subjectId).stream()
                .map(lectureConverter::convertToDTO)
                .collect(Collectors.toList());
    }
}
