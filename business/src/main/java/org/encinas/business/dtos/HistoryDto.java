package org.encinas.business.dtos;

import lombok.Data;

@Data
public class HistoryDto {
    private int bloodSugarLevel;
    private double weight;
    private double height;
    private String symptom;
    private String treatment;
    private String observation;
}
