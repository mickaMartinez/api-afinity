package com.ms.afinity.services;

import java.util.List;

import com.ms.afinity.bean.AlumnoBusquedaAvanzada;
import com.ms.afinity.bean.AlumnosBean;
import com.ms.afinity.bean.ConsultaAlumnosBean;
import com.ms.afinity.bean.ListaAlumnoBean;

public interface IAlumnosServices {
    ConsultaAlumnosBean traerAlumnos(Integer pagina);

    Boolean agregarAlumno(AlumnosBean alumnosBean);

    Boolean editarAlumno(Long idAlumno, AlumnosBean alumnosBean);

    Boolean eliminarAlumno(Long idAlumno);

    List<ListaAlumnoBean> traerAlumnoBusquedaAvanzada(AlumnoBusquedaAvanzada alumnoBusquedaAvanzada);
}