package com.student.api.infrastructure.adapter;

import com.student.api.application.port.out.StudentRepositoryPort;
import com.student.api.domain.model.Student;
import com.student.api.domain.model.StudentStatus;
import com.student.api.infrastructure.config.Constants;
import com.student.api.infrastructure.config.DuplicateStudentIdException;
import com.student.api.infrastructure.persistance.StudentEntity;
import com.student.api.infrastructure.persistance.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class StudentRepositoryAdapter implements StudentRepositoryPort {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final R2dbcEntityTemplate template;

    @Override
    public Mono<Student> createStudent(Student student) {
        StudentEntity entity = studentMapper.toEntity(student);
        return studentRepository.existsById(entity.getId())
                .flatMap( exists ->{
                    if (exists) {
                        return Mono.error(new DuplicateStudentIdException(Constants.STUDENT_ID));
                    }
                    return template.insert(StudentEntity.class)
                            .using(entity)
                            .map(studentMapper::toDomain);
                });
    }

    @Override
    public Flux<Student> getActiveStudents() {
        return studentRepository.findByStatus(StudentStatus.active)
                .map(studentMapper::toDomain);
    }
}
