package com.md.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "LECTURER")
public class Lecturer extends User {

    @Id
    @GeneratedValue
    @Column(name = "lecturer_id")
    private Integer id;

    @OneToMany(mappedBy = "lecturer")
    private List<Subject> subjects;

    @OneToMany(mappedBy = "lecturer")
    private List<Lecture> lectures;

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
