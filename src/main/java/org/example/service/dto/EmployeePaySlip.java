package org.example.service.dto;


import lombok.*;
import org.example.entity.Employee;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class EmployeePaySlip implements Serializable {

    private Employee employee ;

    private double salary;

}