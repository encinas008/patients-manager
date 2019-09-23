package org.encinas.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "patient")
@Data
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
}
