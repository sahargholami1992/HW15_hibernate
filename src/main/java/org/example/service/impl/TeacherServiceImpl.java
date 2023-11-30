package org.example.service.impl;

import org.example.base.repository.BaseRepository;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.entity.Term;
import org.example.entity.enumeration.TeacherType;
import org.example.repository.TeacherRepository;
import org.example.service.TeacherService;
import org.example.service.base.UserServiceImpl;
import org.example.service.dto.TeacherPaySlip;
import org.example.utill.SecurityContext;

public class TeacherServiceImpl extends UserServiceImpl<Teacher, TeacherRepository> implements TeacherService {


    public TeacherServiceImpl(TeacherRepository repository) {
        super(repository);
    }
    @Override
    public Teacher getAllTeacherInfo(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public boolean recordStudentScore(Double score, Student student, Course course) {
        if (score < 0 || score > 20) {
            return false;
        }
        repository.recordScore(score, student, course);
        return true;
    }

    @Override
    public TeacherPaySlip showPaySlip(Term term) {
        Teacher teacher = (Teacher) SecurityContext.getCurrentUser();
        double salary;
        Long unit;
        if (repository.teachingUnits(term) == null) {
            unit = 0L;
        } else {
            unit = repository.teachingUnits(term);
        }
        if (teacher.getType() == TeacherType.ACADEMIC_STAFF) {
            double fixedSalary = 5000000;
            salary = fixedSalary + (unit * 1000000);
        } else {
            salary = unit * 1000000;
        }
        return new TeacherPaySlip(
                teacher, unit, salary
        );
    }
}
