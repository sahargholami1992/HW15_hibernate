package org.example.entity;

import lombok.*;
import org.example.base.entity.BaseEntity;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class StudentCourse extends BaseEntity<Integer> {
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn
    private Student student;
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Course course;
    private Double score;
    private Boolean isPass;




}
