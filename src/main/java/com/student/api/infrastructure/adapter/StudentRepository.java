package com.student.api.infrastructure.adapter;

import com.student.api.domain.model.StudentStatus;
import com.student.api.infrastructure.persistance.StudentEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface StudentRepository extends ReactiveCrudRepository<StudentEntity, Long> {
    Flux<StudentEntity> findByStatus(StudentStatus status);

}
