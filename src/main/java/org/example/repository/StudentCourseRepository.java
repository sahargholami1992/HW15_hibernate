package org.example.repository;


import org.example.base.repository.BaseRepository;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.StudentCourse;
import org.example.entity.Teacher;

import java.util.List;


public interface StudentCourseRepository extends BaseRepository<Integer, StudentCourse> {
    Double average(Student student);
    Long sumUnitsInCurrentTerm(Student student);

    List<StudentCourse> findByStudent(Student student);

    List<Course> findCourseByStudent(Student student);
    List<Course> findCourseByStudentTeacher(Student student, Teacher teacher);


}
