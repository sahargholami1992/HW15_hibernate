package org.example.service.dto;

import lombok.*;
import org.example.entity.Teacher;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class TeacherPaySlip implements Serializable {

        private Teacher teacher;

        private Long teachingUnits;

        private double salary;

}