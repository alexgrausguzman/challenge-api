package com.student.api.infrastructure.persistance;

import com.student.api.domain.model.Student;
import com.student.api.infrastructure.dto.StudentDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentEntity toEntity(Student student);
    Student toDomain(StudentEntity entity);
    @Mapping(target = "status", source = "status")
    Student toDomainDto(StudentDTO dto);
}
