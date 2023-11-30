package org.example.entity;


import lombok.*;
import org.example.base.entity.BaseEntity;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Lesson extends BaseEntity<Integer> {
    private String title;
    private Long unit;

    @Override
    public String toString() {
        return "Lesson{" +
                "id = " + getId() +'\'' +
                " title='" + title + '\'' +
                ", unit=" + unit +
                "} ";
    }
}
