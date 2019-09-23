package org.encinas.business.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class PatientDto {
    private String fullName;
    private String dni;
    private String address;
    private LocalDate birthDate;
}
