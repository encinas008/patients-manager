package org.encinas.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "history")
@Getter @Setter
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 3)
    private Integer bloodSugarLevel;
    @Column(length = 5)
    private Double weight;
    @Column(length = 3)
    private Double height;
    @Column(length = 300)
    private String symptom;
    @Column(length = 300)
    private String treatment;
    @Column(length = 100)
    private String observation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
