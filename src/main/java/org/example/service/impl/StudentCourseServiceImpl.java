package org.example.service.impl;

import org.example.base.service.impl.BaseServiceImpl;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.StudentCourse;
import org.example.entity.Teacher;
import org.example.repository.StudentCourseRepository;
import org.example.service.StudentCourseService;

import java.util.List;

public class StudentCourseServiceImpl extends BaseServiceImpl<Integer, StudentCourse, StudentCourseRepository>
                               implements StudentCourseService {
    public StudentCourseServiceImpl(StudentCourseRepository repository) {
        super(repository);
    }
    @Override
    public Double getAvg(Student student) {
        return repository.average(student);
    }

    @Override
    public Long getSumOfUnitsInCurrentTerm(Student student) {
        return repository.sumUnitsInCurrentTerm(student);
    }

    @Override
    public List<StudentCourse> findByStudent(Student student) {
        return repository.findByStudent(student);
    }

    @Override
    public List<Course> findCourseByStudent(Student student) {
        return repository.findCourseByStudent(student);
    }

    @Override
    public List<Course> findCourseByStudentAndTeacher(Student student, Teacher teacher) {
        return repository.findCourseByStudentTeacher(student, teacher);
    }
}
