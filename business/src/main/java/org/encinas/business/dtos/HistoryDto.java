package org.encinas.business.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Positive;

@Data
public class HistoryDto {
    @Positive
    private Integer bloodSugarLevel;
    @Positive
    private Double weight;
    @Positive
    private Double height;
    @Length(min = 5, max = 300)
    private String symptom;
    private String treatment;
    private String observation;
}
