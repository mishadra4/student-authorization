package com.md.service.impl;

import com.md.dao.SubjectDao;
import com.md.model.Subject;
import com.md.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectDao subjectDao;

    @Override
    public void save(Subject subject) {
        getSubjectDao().save(subject);
    }

    @Override
    public Subject getSubject(int id) {
        return subjectDao.getSubject(id);
    }

    @Override
    public Subject getSubject(String name) {
        return subjectDao.getSubject(name);
    }

    @Override
    public void saveAll(List<Subject> subjects) {
        subjects.forEach(this::save);
    }

    public SubjectDao getSubjectDao() {
        return subjectDao;
    }

    public void setSubjectDao(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }
}
