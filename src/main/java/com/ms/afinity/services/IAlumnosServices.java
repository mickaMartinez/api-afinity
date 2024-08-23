package com.ms.afinity.services;

import com.ms.afinity.bean.AlumnosBean;
import com.ms.afinity.bean.ConsultaAlumnosBean;

public interface IAlumnosServices {
    ConsultaAlumnosBean traerAlumnos(Integer pagina);

    Boolean agregarAlumno(AlumnosBean alumnosBean);

    Boolean editarAlumno(Long idAlumno, AlumnosBean alumnosBean);

    Boolean eliminarAlumno(Long idAlumno);
}