package org.example.repository.impl;

import org.example.entity.Employee;
import org.example.repository.EmployeeRepository;
import org.example.repository.base.UserRepositoryImpl;

import javax.persistence.EntityManager;

public class EmployeeRepositoryImpl extends UserRepositoryImpl<Employee> implements EmployeeRepository {


    public EmployeeRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Employee> getEntityClass() {
        return Employee.class;
    }
}
