package com.ms.afinity.services;

import java.util.List;

import com.ms.afinity.bean.InscripcionBean;
import com.ms.afinity.bean.ListaCursosBean;
import com.ms.afinity.bean.ListaInscripcionesBean;

public interface IInscripcionesServices {
    List<ListaInscripcionesBean> traerMaterias(Long idAlumno);

    List<ListaCursosBean> traerNombreMaterias(String curso);

    Boolean agregarInscripcion(InscripcionBean inscripcionBean);

    Boolean eliminarInscripcion(Long idInscripcion);
}
