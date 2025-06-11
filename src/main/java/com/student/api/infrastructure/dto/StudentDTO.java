package com.student.api.infrastructure.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatiorio")
    private String name;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String lastName;

    @NotBlank(message = "El estado es obligatorio")
    private String status;

    @NotNull(message = "La edad es obligatoria")
    private Integer age;

}
