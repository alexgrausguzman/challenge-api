package com.student.api.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private Long id;
    private String name;
    private String lastName;
    private StudentStatus status;
    private Integer age;
}
