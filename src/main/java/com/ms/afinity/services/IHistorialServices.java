package com.ms.afinity.services;

import java.util.Date;

public interface IHistorialServices {
    void guardarHistorial(Integer idAccion, Long idAlumno, Integer idCurso, Date fecha);
}
