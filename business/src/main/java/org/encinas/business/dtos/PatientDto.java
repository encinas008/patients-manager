package org.encinas.business.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class PatientDto {
    private Integer id;
    @Length(min = 3, max = 50)
    private String fullName;
    @Length(min = 3, max = 15)
    private String dni;
    @Length(min = 3, max = 300)
    private String address;
}
