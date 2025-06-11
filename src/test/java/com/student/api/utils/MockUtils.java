package com.student.api.utils;

import com.student.api.domain.model.Student;
import com.student.api.domain.model.StudentStatus;
import com.student.api.infrastructure.dto.StudentDTO;
import com.student.api.infrastructure.persistance.StudentEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MockUtils {

    public static StudentDTO buildStudentDTO(){
        return StudentDTO.builder()
                .id(15L)
                .name("Roberto")
                .lastName("Torres")
                .status("ACTIVE")
                .age(45)
                .build();
    }
    public static Student buildStudent(){
        return Student.builder()
                .id(15L)
                .name("Roberto")
                .lastName("Torres")
                .status(StudentStatus.ACTIVE)
                .age(45)
                .build();
    }
    public static StudentEntity buildStudentEntity(){
        return StudentEntity.builder()
                .id(15L)
                .name("Roberto")
                .lastName("Torres")
                .status(StudentStatus.ACTIVE)
                .age(45)
                .build();
    }
    public static List<Student> buildActiveList(){
        return List.of(
                new Student(1L, "Ana", "Lopez", StudentStatus.ACTIVE, 22),
                new Student(2L, "Luis", "Gomez", StudentStatus.ACTIVE, 50),
                new Student(3L, "Jerson", "OLIVARES", StudentStatus.ACTIVE, 28)
        );
    }
    public static List<StudentDTO> buildActiveStudentList(){
        return buildActiveList().stream()
                .map(student -> StudentDTO.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .lastName(student.getLastName())
                        .status(student.getStatus().name())
                        .age(student.getAge())
                        .build())
                .collect(Collectors.toList());
    }
    public static List<StudentEntity> buildActiveStudentEntityList(){
        return buildActiveList().stream()
                .map(student -> StudentEntity.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .lastName(student.getLastName())
                        .status(student.getStatus())
                        .age(student.getAge())
                        .build())
                .collect(Collectors.toList());
    }
}
