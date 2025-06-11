package com.student.api.infrastructure.config;


public class DuplicateStudentIdException extends RuntimeException{
    public DuplicateStudentIdException(String message) {
        super(message);
    }
}
