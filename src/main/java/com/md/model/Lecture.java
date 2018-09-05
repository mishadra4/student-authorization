package com.md.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "LECTURE")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDLECTURE")
    private Integer lectureId;

    @OneToMany
    List<Student> students;


    public void setLectureId(Integer lectureId) {
        this.lectureId = lectureId;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
