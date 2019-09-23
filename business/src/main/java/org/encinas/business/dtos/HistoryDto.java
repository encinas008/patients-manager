package org.encinas.business.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HistoryDto {
    private int bloodSugarLevel;
    private double weight;
    private double height;
    private String symptom;
    private String treatment;
    private String observation;
}
