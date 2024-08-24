package com.ms.afinity.entity;

import java.util.Date;

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
@Table(name = "d_historial")
public class HistorialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Long idHistorial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_accion", referencedColumnName = "id_accion")
    private AccionEntity accion;

    @Column(name = "id_alumno")
    private Long idAlumno;

    @Column(name = "id_curso")
    private Integer idCurso;

    @Column(name = "id_inscripcion")
    private Long idInscripcion;

    @Column(name = "fecha")
    private Date fecha;

}
