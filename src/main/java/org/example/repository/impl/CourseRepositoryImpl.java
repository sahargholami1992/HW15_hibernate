package org.example.repository.impl;

import org.example.base.repository.impl.BaseRepositoryImpl;
import org.example.entity.Course;

import org.example.repository.CourseRepository;


import javax.persistence.EntityManager;
import javax.persistence.Query;


public class CourseRepositoryImpl extends BaseRepositoryImpl<Integer, Course> implements CourseRepository {
    public CourseRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Course> getEntityClass() {
        return Course.class;
    }



}
