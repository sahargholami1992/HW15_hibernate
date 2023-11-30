package org.example.service.impl;


import org.example.entity.Course;
import org.example.entity.Lesson;
import org.example.entity.Student;
import org.example.entity.StudentCourse;
import org.example.repository.StudentCourseRepository;
import org.example.repository.StudentRepository;
import org.example.service.StudentCourseService;
import org.example.service.StudentService;
import org.example.service.base.UserServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentServiceImpl extends UserServiceImpl<Student, StudentRepository> implements StudentService {
    StudentCourseService studentCourseService;


    public StudentServiceImpl(StudentRepository repository, StudentCourseService studentCourseService) {
        super(repository);
        this.studentCourseService=studentCourseService;
    }

    public boolean chooseCourses(Student student, Course course) {
        int maxUnits;
        Long sum;
        if (studentCourseService.getAvg(student) == null) {
            maxUnits = 20;
        } else {
            double previousAvg = studentCourseService.getAvg(student);
            maxUnits = (previousAvg > 18) ? 24 : 20;
        }
        if (studentCourseService.getSumOfUnitsInCurrentTerm(student) == null) {
            sum = 0L;
        } else {
            sum = studentCourseService.getSumOfUnitsInCurrentTerm(student);
        }
        if (sum < maxUnits) {
            Set<Lesson> passedLessons = getPassedLessons(student);
            if (!passedLessons.contains(course.getLesson())) {
                if (!isSimilarCourseChosen(student, course)) {
                    StudentCourse studentCourse = new StudentCourse();
                    studentCourse.setCourse(course);
                    studentCourse.setStudent(student);
                    studentCourseService.saveOrUpdate(studentCourse);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<StudentCourse> findByStudent(Student student) {
        return studentCourseService.findByStudent(student);
    }

    private Set<Lesson> getPassedLessons(Student student) {
        Set<Lesson> passedLessons = new HashSet<>();
        for (StudentCourse studentCourse : student.getStudentCourses()) {
            if (studentCourse.getIsPass()) {
                passedLessons.add(studentCourse.getCourse().getLesson());
            }
        }
        return passedLessons;
    }

    private boolean isSimilarCourseChosen(Student student, Course course) {
        for (StudentCourse studentCourse : student.getStudentCourses()) {
            if (studentCourse.getCourse().getLesson().equals(course.getLesson())) {
                return true;
            }
        }
        return false;
    }
}
