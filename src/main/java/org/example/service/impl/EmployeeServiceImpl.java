package org.example.service.impl;
import org.example.entity.Employee;
import org.example.repository.EmployeeRepository;
import org.example.service.EmployeeService;
import org.example.service.base.UserServiceImpl;
import org.example.service.dto.EmployeePaySlip;
import org.example.utill.SecurityContext;


public class EmployeeServiceImpl extends UserServiceImpl<Employee, EmployeeRepository>
        implements EmployeeService {

    public EmployeeServiceImpl(EmployeeRepository repository) {
        super(repository);
    }

    @Override
    public EmployeePaySlip showPaySlip() {
        Employee employee = (Employee) SecurityContext.getCurrentUser();
        double salary = 1000000.0;
        return new EmployeePaySlip(
                employee, salary
        );
    }
}
