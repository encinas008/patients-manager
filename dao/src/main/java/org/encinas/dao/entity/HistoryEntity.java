package org.encinas.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity()
@Table(name = "history")
@Getter @Setter
public class HistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 3)
    private int bloodSugarLevel;
    @Column(length = 5)
    private double weight;
    @Column(length = 3)
    private double height;
    @Column(length = 300)
    private String symptom;
    @Column(length = 300)
    private String treatment;
    @Column(length = 100)
    private String observation;
}
