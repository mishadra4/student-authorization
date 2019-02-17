package com.md.facade.converter;

import com.md.DTO.LabDTO;
import com.md.model.Lab;
import com.md.service.GroupService;
import com.md.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LabConverter implements GenericConverter<Lab, LabDTO> {

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private GroupService groupService;


    @Override
    public Lab convertToEntity(LabDTO dto) {
        Lab lecture = new Lab();
        lecture.setName(dto.getName());
        lecture.setLabId(dto.getId());
        lecture.setLecturer(lecturerService.getLecturerByUsername(dto.getLecturerUsername()));
        lecture.setOrdinalNumber(dto.getOrdinalNumber());
        lecture.setDescription(dto.getDescription());

        lecture.setGroup(groupService.getGroup(dto.getGroupName()));

        return lecture;
    }

    @Override
    public LabDTO convertToDTO(Lab entity) {
        LabDTO dto = new LabDTO();
        dto.setDescription(entity.getDescription());
        dto.setId(entity.getLabId());
        dto.setName(entity.getName());
        dto.setOrdinalNumber(entity.getOrdinalNumber());
        dto.setGroupName(entity.getGroup().getName());
        dto.setLecturerUsername(entity.getLecturer().getUsername());
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
