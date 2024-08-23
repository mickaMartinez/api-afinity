package com.ms.afinity.bean;

import java.util.List;

import lombok.Data;

@Data
public class ConsultaAlumnosBean {
    private List<ListaAlumnoBean> alumnos;
    private Long totalRegistros;
}
