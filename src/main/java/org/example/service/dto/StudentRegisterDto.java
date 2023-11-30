package org.example.service.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class StudentRegisterDto implements Serializable {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String studentNumber;


}
