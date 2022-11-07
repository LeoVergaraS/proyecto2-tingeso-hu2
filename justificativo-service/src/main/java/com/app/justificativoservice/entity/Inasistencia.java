package com.app.justificativoservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "inasistencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inasistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private int month;
    private int year;
    private int mountOfDays;
    private int justificative;
    private String rut;
}