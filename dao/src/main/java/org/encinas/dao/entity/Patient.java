package org.encinas.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
@Getter @Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 50)
    private String fullName;
    @Column(length = 15, unique = true)
    private String dni;
    @Column(length=300)
    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private List<History> histories = new ArrayList<>();
}
