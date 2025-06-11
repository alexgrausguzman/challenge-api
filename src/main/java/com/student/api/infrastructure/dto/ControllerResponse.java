package com.student.api.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by Alex Graus Guzman.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ControllerResponse {
    private String message;
    private List<String> errors;

    public ControllerResponse(String message){
        this.message = message;
    }
}
