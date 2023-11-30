package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.base.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass

public class User extends BaseEntity<Integer> {
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String userName;
    private String password;
    public User( String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}
