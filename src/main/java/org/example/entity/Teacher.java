package org.example.entity;

import lombok.*;
import org.example.entity.enumeration.TeacherType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Teacher extends User {
    private String teacherNumber;
    @Enumerated(value = EnumType.STRING)
    private TeacherType type;
    public Teacher(String firstName, String lastName, String userName, String password, String teacherNumber, TeacherType type) {
        super(firstName, lastName, userName, password);
        this.teacherNumber = teacherNumber;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id = " + getId()+ '\'' +
                "teacherNumber= '" + teacherNumber + '\'' +
                ", type= " + type +
                '}';
    }

}
