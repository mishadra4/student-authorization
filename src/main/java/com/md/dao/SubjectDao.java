package com.md.dao;

import com.md.model.Subject;

public interface SubjectDao {
    void save(Subject subject);

    Subject getSubject(String name);

    Subject getSubject(final int id);
}
