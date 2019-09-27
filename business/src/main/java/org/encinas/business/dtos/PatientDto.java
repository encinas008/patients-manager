package org.encinas.business.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class PatientDto {
    private Integer id;
    @NotBlank
    @Length(min = 3, max = 50)
    private String fullName;
    @NotBlank
    @Length(min = 3, max = 15)
    private String dni;
    @NotBlank
    @Length(min = 3, max = 100)
    private String address;
}
