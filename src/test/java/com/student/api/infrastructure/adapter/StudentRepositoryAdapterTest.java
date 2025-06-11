package com.student.api.infrastructure.adapter;

import com.student.api.domain.model.Student;
import com.student.api.domain.model.StudentStatus;
import com.student.api.infrastructure.persistance.StudentEntity;
import com.student.api.infrastructure.persistance.StudentMapper;
import com.student.api.utils.MockUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;


@ExtendWith(MockitoExtension.class)
class StudentRepositoryAdapterTest {
    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private R2dbcEntityTemplate template;

    @InjectMocks
    private StudentRepositoryAdapter adapter;

    private Student student;
    private StudentEntity entity;
    private List<Student> studentList;
    private List<StudentEntity> entityList;

    @BeforeEach
    void setUp() {
        student = MockUtils.buildStudent();
        entity = MockUtils.buildStudentEntity();
        studentList = MockUtils.buildActiveList();
        entityList = MockUtils.buildActiveStudentEntityList();
    }

    @Test
    void shouldCreateStudent() {

        Mockito.when(studentMapper.toEntity(student)).thenReturn(entity);
        Mockito.when(studentRepository.existsById(15L)).thenReturn(Mono.just(false));
        Mockito.when(template.insert(StudentEntity.class).using(entity)).thenReturn(Mono.just(entity));
        Mockito.when(studentMapper.toDomain(entity)).thenReturn(student);

        StepVerifier.create(adapter.createStudent(student))
                .expectNext(student)
                .verifyComplete();
    }

    @Test
    void getActiveStudents() {
        Mockito.when(studentRepository.findByStatus(StudentStatus.ACTIVE))
                .thenReturn(Flux.just(entityList.get(0), entityList.get(1)));

        Mockito.when(studentMapper.toDomain(entityList.get(0))).thenReturn(studentList.get(0));
        Mockito.when(studentMapper.toDomain(entityList.get(1))).thenReturn(studentList.get(1));

        StepVerifier.create(adapter.getActiveStudents())
                .expectNext(studentList.get(0))
                .expectNext(studentList.get(1))
                .verifyComplete();
    }
}