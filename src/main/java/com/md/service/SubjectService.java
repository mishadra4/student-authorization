package com.md.service;


import com.md.model.Subject;

import java.util.List;

public interface SubjectService {

    void save(Subject subject);

    void saveAll(List<Subject> subjects);
}
