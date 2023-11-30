package org.example.entity;


import lombok.*;
import org.example.base.entity.BaseEntity;
import org.example.entity.enumeration.MidTerm;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Course extends BaseEntity<Integer> {
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Teacher teacher;
    @ManyToOne(fetch = FetchType.LAZY)
    private Lesson lesson;
    @ToString.Exclude
    @OneToMany(mappedBy = "course")
    private Set<StudentCourse> studentCourses;
    @Column(columnDefinition = "Boolean default true")
    private Boolean isPass;
    @Embedded
    private Term term;

    public Course(Lesson lesson, Teacher teacher, Term term) {
        this.lesson = lesson;
        this.teacher = teacher;
        this.term = term;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id = " + getId() +
                " lesson= " + lesson +
                ", teacher= " + teacher +
                ", studentCourses= " + studentCourses +
                ", term= " + term +
                "} ";
    }





}
