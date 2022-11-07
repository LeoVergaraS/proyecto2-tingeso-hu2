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

    private int mes;
    private int anio;
    private int cantidadDias;
    private int diasJustificados;
    private String rutEmpleado;
}