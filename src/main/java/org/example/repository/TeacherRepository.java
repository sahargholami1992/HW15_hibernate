package org.example.repository;

import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.entity.Term;
import org.example.repository.base.UserRepository;

public interface TeacherRepository extends UserRepository<Teacher> {
    void recordScore(Double score, Student student, Course course);

    Long teachingUnits(Term term);
}
