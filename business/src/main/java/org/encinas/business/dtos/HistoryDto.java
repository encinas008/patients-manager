package org.encinas.business.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class HistoryDto {
    @Positive
    private int bloodSugarLevel;
    @Positive
    private double weight;
    @Positive
    private double height;
    @NotBlank
    @Length(min = 5, max = 100)
    private String symptom;
    private String treatment;
    private String observation;
}
