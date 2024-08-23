package com.ms.afinity.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "r_inscripciones")
public class InscripcionesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inscripciones")
    private Long idInscripciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_alumnos", referencedColumnName = "id_alumnos")
    private AlumnosEntity alumnos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cursos", referencedColumnName = "id_cursos")
    private CursosEntity cursos;

    @Column(name = "estatus")
    private Boolean estatus;

}
