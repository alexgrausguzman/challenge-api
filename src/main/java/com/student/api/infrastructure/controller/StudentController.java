package com.student.api.infrastructure.controller;

import com.student.api.application.port.in.CreateStudentUseCase;
import com.student.api.application.port.in.GetActiveStudentsUseCase;
import com.student.api.infrastructure.config.Constants;
import com.student.api.infrastructure.dto.ControllerResponse;
import com.student.api.infrastructure.dto.StudentDTO;
import com.student.api.infrastructure.persistance.StudentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final CreateStudentUseCase createStudentUseCase;
    private final GetActiveStudentsUseCase getActiveStudents;
    private final StudentMapper studentMapper;


    @PostMapping
    public Mono<ResponseEntity<ControllerResponse>> createStudent(@Valid @RequestBody StudentDTO dto) {
        return createStudentUseCase.createStudent(studentMapper.toDomainDto(dto))
                .map(savedStudent -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(new ControllerResponse(Constants.STUDENT_CREATED)));
    }

    @GetMapping("/active")
    public ResponseEntity<Flux<StudentDTO>> getActiveStudents() {
        return ResponseEntity.ok(
                getActiveStudents.getActiveStudents()
                        .map(studentMapper::toDTO)
        );
    }
}
