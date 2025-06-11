package com.student.api.application.port.in;

import com.student.api.domain.model.Student;
import reactor.core.publisher.Flux;

public interface GetActiveStudentsUseCase {
    Flux<Student> getActiveStudents();
}
