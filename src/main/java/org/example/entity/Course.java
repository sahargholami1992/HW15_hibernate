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
@Entity
public class Course extends BaseEntity<Integer> {
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Teacher teacher;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Lesson lesson;
    @ToString.Exclude
    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private Set<StudentCourse> studentCourses;
    @Column(columnDefinition = "Boolean default false")
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
