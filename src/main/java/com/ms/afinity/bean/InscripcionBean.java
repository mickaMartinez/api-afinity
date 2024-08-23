package com.ms.afinity.bean;

import java.util.List;

import lombok.Data;

@Data
public class InscripcionBean {
    private Long idAlumno;
    private List<Integer> cursos;
}
