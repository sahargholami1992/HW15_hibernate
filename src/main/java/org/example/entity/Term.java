package org.example.entity;

import lombok.*;
import org.example.entity.enumeration.MidTerm;


import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable
public class Term {
    private Integer year;
    @Enumerated(value = EnumType.STRING)
    private MidTerm midTerm;

}
