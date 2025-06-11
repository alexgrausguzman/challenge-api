package com.student.api.infrastructure.config;

import com.student.api.infrastructure.dto.ControllerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<ControllerResponse>> handleValidationErrors(WebExchangeBindException ex) {
        List<String> errors = ex.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ControllerResponse response = new ControllerResponse(
                Constants.ERROR_TITLE,
                errors
        );

        return Mono.just(ResponseEntity.badRequest().body(response));
    }
    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<ControllerResponse> handleEmptyRequest(ServerWebInputException ex) {
        return ResponseEntity.badRequest()
                .body(new ControllerResponse(Constants.NO_CONTENT));
    }
    @ExceptionHandler(DuplicateStudentIdException.class)
    public ResponseEntity<ControllerResponse> handleDuplicateId(DuplicateStudentIdException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ControllerResponse(ex.getMessage(), null));
    }

}
