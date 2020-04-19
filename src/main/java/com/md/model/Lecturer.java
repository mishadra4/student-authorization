package com.md.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity(name = "lecturer")
@Table(name = "lecturer")
public class Lecturer extends User {

    @OneToMany(mappedBy = "lecturer", fetch = FetchType.EAGER)
    private List<Subject> subjects;

    @OneToMany(mappedBy = "lecturer")
    private List<Lecture> lectures;

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }


    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

}
