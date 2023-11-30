package org.example.utill;


import org.example.repository.*;
import org.example.repository.impl.*;
import org.example.service.*;
import org.example.service.impl.*;



import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ApplicationContext {
    private static final EntityManager entityManager =
            Persistence.createEntityManagerFactory(
                    "default"
            ).createEntityManager();
    private static final EmployeeRepository EMPLOYEE_REPOSITORY;
    private static final StudentRepository STUDENT_REPOSITORY;
    private static final TeacherRepository TEACHER_REPOSITORY;

    private static final CourseRepository COURSE_REPOSITORY;
    private static final LessonRepository LESSON_REPOSITORY;
    private static final StudentCourseRepository STUDENT_COURSE_REPOSITORY;
    private static final EmployeeService EMPLOYEE_SERVICE;
    private static final StudentService STUDENT_SERVICE;
    private static final TeacherService TEACHER_SERVICE;
    private static final LessonService LESSON_SERVICE;
    private static final CourseService COURSE_SERVICE;
    private static final StudentCourseService STUDENT_COURSE_SERVICE;
    static {
        EMPLOYEE_REPOSITORY = new EmployeeRepositoryImpl(entityManager);
        TEACHER_REPOSITORY = new TeacherRepositoryImpl(entityManager);
        STUDENT_REPOSITORY = new StudentRepositoryImpl(entityManager);
        STUDENT_COURSE_REPOSITORY = new StudentCourseRepositoryImpl(entityManager);
        STUDENT_COURSE_SERVICE = new StudentCourseServiceImpl(STUDENT_COURSE_REPOSITORY);
        STUDENT_SERVICE = new StudentServiceImpl(STUDENT_REPOSITORY,STUDENT_COURSE_SERVICE);
        TEACHER_SERVICE = new TeacherServiceImpl(TEACHER_REPOSITORY);
        EMPLOYEE_SERVICE = new EmployeeServiceImpl(EMPLOYEE_REPOSITORY);
        COURSE_REPOSITORY = new CourseRepositoryImpl(entityManager);
        COURSE_SERVICE = new CourseServiceImpl(COURSE_REPOSITORY);
        LESSON_REPOSITORY = new LessonRepositoryImpl(entityManager);
        LESSON_SERVICE = new LessonServiceImpl(LESSON_REPOSITORY);



    }

    public static EmployeeService getEmployeeService(){
        return EMPLOYEE_SERVICE;
    }
    public static StudentService getStudentService(){
        return STUDENT_SERVICE;
    }
    public static TeacherService getTeacherService(){
        return TEACHER_SERVICE;
    }
    public static CourseService getCourseService(){
        return COURSE_SERVICE;
    }
    public static LessonService getLessonService(){
        return LESSON_SERVICE;
}
    public static StudentCourseService getStudentCourseService(){
        return STUDENT_COURSE_SERVICE;
}

}
