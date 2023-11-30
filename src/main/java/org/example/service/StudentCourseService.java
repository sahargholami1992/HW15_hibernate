package org.example.service;

import org.example.base.service.BaseService;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.StudentCourse;
import org.example.entity.Teacher;

import java.util.List;

public interface StudentCourseService extends BaseService<Integer, StudentCourse> {
    Double getAvg(Student student);

    Long getSumOfUnitsInCurrentTerm(Student student);

    List<StudentCourse> findByStudent(Student student);

    List<Course> findCourseByStudent(Student student);

    List<Course> findCourseByStudentAndTeacher(Student student, Teacher teacher);


}
