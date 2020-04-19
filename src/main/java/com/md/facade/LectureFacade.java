package com.md.facade;

import com.md.facade.dto.LectureData;

import java.util.List;

public interface LectureFacade {

    LectureData getLecture(final int id);

    List<LectureData> getLectures(String subjectId);

    void createLecture(LectureData lectureData);
}
