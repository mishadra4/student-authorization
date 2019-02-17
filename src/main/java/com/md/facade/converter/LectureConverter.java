package com.md.facade.converter;

import com.md.DTO.LectureDTO;
import com.md.model.Group;
import com.md.model.Lecture;
import com.md.service.GroupService;
import com.md.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LectureConverter implements GenericConverter<Lecture, LectureDTO> {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private GroupService groupService;


    @Override
    public Lecture convertToEntity(LectureDTO dto) {
        Lecture lecture = new Lecture();
        lecture.setName(dto.getName());
        lecture.setLectureId(dto.getId());
        lecture.setLecturer(lecturerService.getLecturerByUsername(dto.getLecturerUsername()));
        lecture.setOrdinalNumber(dto.getOrdinalNumber());
        lecture.setDescription(dto.getDescription());

//        lecture.setGroups(dto.getGroups().stream()
//                .map(groupService::getGroup)
//                .collect(Collectors.toList()));

        return lecture;
    }

    @Override
    public LectureDTO convertToDTO(Lecture entity) {
        LectureDTO dto = new LectureDTO();
        dto.setDescription(entity.getDescription());
        dto.setId(entity.getLectureId());
        dto.setName(entity.getName());
        dto.setOrdinalNumber(entity.getOrdinalNumber());
//        dto.setGroups(entity.getGroups().stream()
//                .map(Group::getName).collect(Collectors.toList()));
//        dto.setLecturerUsername(entity.getLecturer().getUsername());

        return dto;
    }

    public LecturerService getLecturerService() {
        return lecturerService;
    }

    public void setLecturerService(LecturerService lecturerService) {
        this.lecturerService = lecturerService;
    }

    public GroupService getGroupService() {
        return groupService;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }
}
