package org.example.service;

import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.entity.Term;
import org.example.service.base.UserService;
import org.example.service.dto.TeacherPaySlip;

public interface TeacherService extends UserService<Teacher> {
    Teacher getAllTeacherInfo(Integer id);

    boolean recordStudentScore(Double score, Student student, Course course);

    TeacherPaySlip showPaySlip(Term term);
}
