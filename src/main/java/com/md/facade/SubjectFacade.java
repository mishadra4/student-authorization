package com.md.facade;

import com.md.facade.dto.SubjectData;

import java.util.List;

public interface SubjectFacade {

    List<SubjectData> getSubjects(String username);

    SubjectData getSubject(int id);

    SubjectData getSubject(String name);

}
