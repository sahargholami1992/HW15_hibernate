package org.example.repository.impl;

import org.example.base.repository.impl.BaseRepositoryImpl;
import org.example.entity.*;
import org.example.repository.StudentCourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StudentCourseRepositoryImpl extends BaseRepositoryImpl<Integer, StudentCourse> implements StudentCourseRepository {
    public StudentCourseRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }


    @Override
    public Class<StudentCourse> getEntityClass() {
        return StudentCourse.class;
    }


    @Override
    public Double average(Student student) {
        try {
            Term term = currentTerm();
            String hql = "SELECT avg(score) FROM StudentCourse WHERE student = :student AND course.term.midTerm = :midTerm AND course.term.year = :year";
            TypedQuery<Double> query = entityManager.createQuery(hql, Double.class);
            query.setParameter("student", student);
            query.setParameter("midTerm", term.getMidTerm());
            query.setParameter("year", term.getYear());
            return query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    private Term currentTerm() {
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<StudentCourse> cq = cb.createQuery(StudentCourse.class);
            Root<StudentCourse> root = cq.from(StudentCourse.class);
            cq.select(root);
            cq.orderBy(cb.desc(root.get("id")));
            TypedQuery<StudentCourse> query = entityManager.createQuery(cq).setMaxResults(1);
            StudentCourse studentCourse = query.getSingleResult();
            return studentCourse.getCourse().getTerm();
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }
    @Override
    public Long sumUnitsInCurrentTerm(Student student) {
        try {
            Term term = currentTerm();
            String hql = """
                SELECT sum(c.lesson.unit) FROM StudentCourse st
                JOIN Course c ON st.course.id =  c.id
                JOIN Lesson l ON c.lesson.id = l.id
                WHERE st.student = :student  AND c.term.midTerm = :midTerm AND c.term.year = :year
                """;
            TypedQuery<Long> query = entityManager.createQuery(hql, Long.class);
            query.setParameter("student", student);
            query.setParameter("midTerm", term.getMidTerm());
            query.setParameter("year", term.getYear());
            return query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
            return null;

    }
        }

    @Override
    public List<StudentCourse> findByStudent(Student student) {
        try {
            String hql = "SELECT sc FROM StudentCourse sc WHERE sc.student = :student";
            TypedQuery<StudentCourse> typedQuery = entityManager.createQuery(hql, StudentCourse.class);
            typedQuery.setParameter("student" ,student);
            return typedQuery.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Course> findCourseByStudent(Student student) {
        try {
            String hql = "SELECT course FROM StudentCourse sc WHERE sc.student = :student";
            TypedQuery<Course> typedQuery = entityManager.createQuery(hql, Course.class);
            typedQuery.setParameter("student" ,student);
            return typedQuery.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Course> findCourseByStudentTeacher(Student student, Teacher teacher) {
        try {
            String hql = "SELECT course FROM StudentCourse sc WHERE sc.student = :student AND sc.course.teacher = :teacher";
            TypedQuery<Course> typedQuery = entityManager.createQuery(hql, Course.class);
            typedQuery.setParameter("student" ,student);
            typedQuery.setParameter("teacher" ,teacher);
            return typedQuery.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
