package org.encinas.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
@Getter @Setter
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 50)
    private String fullName;
    @Column(length = 15)
    private String dni;
    @Column(length=300)
    private String address;
    @Column
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private List<HistoryEntity> histories = new ArrayList<>();
}
