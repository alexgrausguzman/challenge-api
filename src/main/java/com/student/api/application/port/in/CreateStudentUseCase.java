package com.student.api.application.port.in;

import com.student.api.domain.model.Student;
import reactor.core.publisher.Mono;

public interface CreateStudentUseCase {
    Mono<Student> createStudent(Student student);
}
