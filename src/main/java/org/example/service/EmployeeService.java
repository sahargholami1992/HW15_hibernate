package org.example.service;

import org.example.entity.Employee;
import org.example.service.base.UserService;
import org.example.service.dto.EmployeePaySlip;


public interface EmployeeService extends UserService<Employee> {
    EmployeePaySlip showPaySlip();
}
