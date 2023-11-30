package org.example.repository.impl;

import org.example.entity.Student;
import org.example.repository.StudentRepository;
import org.example.repository.base.UserRepositoryImpl;

import javax.persistence.EntityManager;

public class StudentRepositoryImpl extends UserRepositoryImpl<Student> implements StudentRepository {
    public StudentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Student> getEntityClass() {
        return Student.class;
    }
}
