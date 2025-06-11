package com.student.api.infrastructure.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentDTO {

    @NotNull(message = "El ID es obligatiorio.")
    private Long id;

    @NotBlank(message = "El nombre es obligatiorio.")
    private String name;

    @NotBlank(message = "Los apellidos son obligatorios.")
    private String lastName;

    @NotBlank(message = "El estado es obligatorio.")
    @Pattern(regexp = "ACTIVE|INACTIVE", message = "El estado debe ser ACTIVE o INACTIVE")
    private String status;

    @NotNull(message = "La edad es obligatoria.")
    private Integer age;

}
