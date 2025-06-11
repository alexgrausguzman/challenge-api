package com.student.api.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private Long id;
    private String name;
    private String lastName;
    private StudentStatus status;
    private Integer age;
}
