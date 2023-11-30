package org.example.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Student extends User {
    private String studentNumber;
    @ToString.Exclude
    @OneToMany(mappedBy = "student")
    private List<StudentCourse> studentCourses;

    public Student(String firstName, String lastName, String userName, String password, String studentNumber) {
        super(firstName, lastName, userName, password);
        this.studentNumber = studentNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id = "+getId()+
                " studentNumber = '" + studentNumber + '\'' +
                ", studentCourses = " + studentCourses +
                '}';
    }


}
