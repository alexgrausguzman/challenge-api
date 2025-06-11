package com.student.api.infrastructure.controller;

import com.student.api.application.port.in.CreateStudentUseCase;
import com.student.api.application.port.in.GetActiveStudentsUseCase;
import com.student.api.domain.model.Student;
import com.student.api.infrastructure.config.Constants;
import com.student.api.infrastructure.dto.StudentDTO;
import com.student.api.infrastructure.persistance.StudentMapper;
import com.student.api.utils.MockUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@WebFluxTest(StudentController.class)
@ExtendWith(SpringExtension.class)
class StudentControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CreateStudentUseCase createStudentUseCase;

    @MockBean
    private GetActiveStudentsUseCase getActiveStudents;

    @MockBean
    private StudentMapper studentMapper;

    private StudentDTO studentDTO;
    private Student student;
    private List<StudentDTO> studentDTOList;
    private List<Student> studentList;

    @BeforeEach
    void setUp() {
        studentDTO = MockUtils.buildStudentDTO();
        student = MockUtils.buildStudent();
        studentList = MockUtils.buildActiveList();
        studentDTOList = MockUtils.buildActiveStudentList();
    }

    @Test
    void createStudent() {
        Mockito.when(studentMapper.toDomainDto(Mockito.any(StudentDTO.class))).thenReturn(student);
        Mockito.when(createStudentUseCase.createStudent(student)).thenReturn(Mono.just(student));

        webTestClient.post()
                .uri("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(studentDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.message").isEqualTo(Constants.STUDENT_CREATED);
    }

    @Test
    void getActiveStudents() {

        Mockito.when(getActiveStudents.getActiveStudents())
                .thenReturn(Flux.just(studentList.get(0), studentList.get(1), studentList.get(2)));
        Mockito.when(studentMapper.toDTO(studentList.get(0))).thenReturn(studentDTOList.get(0));
        Mockito.when(studentMapper.toDTO(studentList.get(1))).thenReturn(studentDTOList.get(1));
        Mockito.when(studentMapper.toDTO(studentList.get(2))).thenReturn(studentDTOList.get(2));

        webTestClient.get()
                .uri("/students/active")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(StudentDTO.class)
                .hasSize(3)
                .contains(studentDTOList.get(0), studentDTOList.get(1), studentDTOList.get(2));
    }
}