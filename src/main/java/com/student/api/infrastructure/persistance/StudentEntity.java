package com.student.api.infrastructure.persistance;

import com.student.api.domain.model.StudentStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("students")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StudentEntity {
    @Id
    private Long id;
    private String name;
    private String lastName;
    private StudentStatus status;
    private Integer age;
}
