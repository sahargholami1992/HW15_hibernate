package org.example.service;

import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.StudentCourse;
import org.example.service.base.UserService;

import java.util.List;

public interface StudentService  extends UserService<Student> {
    boolean chooseCourses(Student student, Course availableCourses);

    List<StudentCourse> findByStudent(Student student);
}
