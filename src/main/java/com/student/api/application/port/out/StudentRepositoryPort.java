package com.student.api.application.port.out;

import com.student.api.domain.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepositoryPort {
    Mono<Student> createStudent(Student student);
    Flux<Student> getActiveStudents();
}
