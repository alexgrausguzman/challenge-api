package com.student.api.application.service;

import com.student.api.application.port.in.CreateStudentUseCase;
import com.student.api.application.port.in.GetActiveStudentsUseCase;
import com.student.api.application.port.out.StudentRepositoryPort;
import com.student.api.domain.model.Student;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService implements CreateStudentUseCase, GetActiveStudentsUseCase {

    private final StudentRepositoryPort studentRepositoryPort;

    public StudentService(StudentRepositoryPort studentRepositoryPort) {
        this.studentRepositoryPort = studentRepositoryPort;
    }

    @Override
    public Flux<Student> getActiveStudents() {
        return studentRepositoryPort.getActiveStudents();
    }
    @Override
    public Mono<Student> createStudent(Student student) {
        return studentRepositoryPort.createStudent(student);
    }
}
