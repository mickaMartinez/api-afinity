package com.ms.afinity.services;

import com.ms.afinity.bean.ConsultaCursosBean;
import com.ms.afinity.bean.CursosBean;

public interface ICursosServices {
    ConsultaCursosBean traerCursos(Integer pagina);

    Boolean agregarCurso(CursosBean cursosBean);

    Boolean editarCurso(Integer idCurso, CursosBean cursosBean);

    Boolean eliminarCurso(Integer idCurso);
}
