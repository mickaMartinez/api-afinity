package com.ms.afinity.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListaAlumnoBean extends AlumnosBean {
    private Long idAlumno;
}
