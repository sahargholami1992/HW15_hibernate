package org.example.repository.impl;

import org.example.entity.*;
import org.example.repository.TeacherRepository;
import org.example.repository.base.UserRepositoryImpl;
import org.example.utill.SecurityContext;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


public class TeacherRepositoryImpl extends UserRepositoryImpl<Teacher> implements TeacherRepository {
    public TeacherRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Teacher> getEntityClass() {
        return Teacher.class;
    }

    @Override
    public void recordScore(Double score, Student student, Course course) {

        try {
            beginTransaction();
            boolean isPass = score > 10;
            String hql = "UPDATE FROM StudentCourse sc SET sc.score = :score , sc.isPass= :isPass WHERE student = :student  AND course = :course";
            Query query = entityManager.createQuery(hql);
            query.setParameter("score", score);
            query.setParameter("student", student);
            query.setParameter("course", course);
            query.setParameter("isPass", isPass);
            query.executeUpdate();
            commitTransaction();
        } catch(Exception e){
            e.printStackTrace();
            rollBack();
        }

    }

    @Override
    public Long teachingUnits(Term term) {
        try {
            Teacher teacher = (Teacher) SecurityContext.getCurrentUser();

            String hql = """
                SELECT SUM(l.unit) FROM Course c
                JOIN Lesson l on l = c.lesson
                WHERE c.term = :term AND c.teacher = :teacher
                """;
            TypedQuery<Long> query = entityManager.createQuery(hql, Long.class);
            query.setParameter("term", term);
            query.setParameter("teacher", teacher);
            return query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
//        Root<Course> course = cq.from(Course.class);
//        Join<Course, Lesson> lesson = course.join("lesson");
//        cq.where(cb.equal(course.get("term"), term),
//                cb.equal(course.get("teacher"), teacher));
//
//        return entityManager.createQuery(cq).getSingleResult();
    }
}
