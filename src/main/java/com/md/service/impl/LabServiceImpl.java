package com.md.service.impl;

import com.md.dao.LabDao;
import com.md.model.Lab;
import com.md.service.LabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabServiceImpl implements LabService {

    @Autowired
    private LabDao labDao;

    @Override
    public Lab getLab(Integer id) {
        return labDao.getLab(id);
    }

    @Override
    public List<Lab> getLab(String lecturerUsername) {
        return labDao.getLabs(lecturerUsername);
    }

    @Override
    public void saveLab(Lab lab) {
        labDao.saveLab(lab);
    }

    public LabDao getLabDao() {
        return labDao;
    }

    public void setLabDao(LabDao labDao) {
        this.labDao = labDao;
    }
}
