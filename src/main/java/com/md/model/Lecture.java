package com.md.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "LECTURE")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LECTURE")
    private Integer lectureId;

    @ManyToMany
    List<Student> students;


    public void setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Integer getLectureId() {
        return lectureId;
    }

    public List<Student> getStudents() {
        return students;
    }
}
