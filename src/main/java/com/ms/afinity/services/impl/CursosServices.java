package com.ms.afinity.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ms.afinity.bean.ConsultaCursosBean;
import com.ms.afinity.bean.CursosBean;
import com.ms.afinity.bean.ListaCursosBean;
import com.ms.afinity.entity.CursosEntity;
import com.ms.afinity.entity.InscripcionesEntity;
import com.ms.afinity.repository.CursosRepository;
import com.ms.afinity.repository.InscripcionesRepository;
import com.ms.afinity.services.ICursosServices;

import jakarta.transaction.Transactional;

@Service
public class CursosServices implements ICursosServices {
    private CursosRepository cursosRepository;
    private InscripcionesRepository inscripcionesRepository;

    public CursosServices(CursosRepository cursosRepository, InscripcionesRepository inscripcionesRepository) {
        this.cursosRepository = cursosRepository;
        this.inscripcionesRepository = inscripcionesRepository;
    }

    @Override
    public ConsultaCursosBean traerCursos(Integer pagina) {
        Pageable pageable = PageRequest.of(pagina, 10);

        Page<CursosEntity> cursosRegistros = cursosRepository.findAll(pageable);
        List<CursosEntity> cursos = cursosRegistros.getContent();

        ConsultaCursosBean consultaCursosBean = new ConsultaCursosBean();
        List<ListaCursosBean> listaFinal = new ArrayList<>();
        for (CursosEntity curso : cursos) {
            ListaCursosBean lista = new ListaCursosBean();

            lista.setIdCurso(curso.getIdCursos());
            lista.setNombre(curso.getNombre());
            lista.setEstatus(curso.getEstatus());

            listaFinal.add(lista);
        }

        consultaCursosBean.setCursos(listaFinal);
        consultaCursosBean.setTotalRegistros(cursosRegistros.getTotalElements());

        return consultaCursosBean;
    }

    @Override
    @Transactional
    public Boolean agregarCurso(CursosBean cursosBean) {
        CursosEntity cursosEntity = new CursosEntity();
        cursosEntity.setNombre(cursosBean.getNombre().toLowerCase().toUpperCase().trim());
        cursosEntity.setEstatus(cursosBean.getEstatus());

        cursosRepository.save(cursosEntity);

        return true;
    }

    @Override
    @Transactional
    public Boolean editarCurso(Integer idCurso, CursosBean cursosBean) {
        Optional<CursosEntity> cursoExistente = cursosRepository.findById(idCurso);
        Boolean response = Boolean.FALSE;

        if (cursoExistente.isPresent()) {
            CursosEntity curso = cursoExistente.get();

            curso.setNombre(cursosBean.getNombre().toLowerCase().toUpperCase().trim());
            curso.setEstatus(cursosBean.getEstatus());
            cursosRepository.save(curso);

            response = Boolean.TRUE;
        }

        return response;
    }

    @Override
    @Transactional
    public Boolean eliminarCurso(Integer idCurso) {
        Optional<CursosEntity> cursoExistente = cursosRepository.findById(idCurso);
        Boolean response = Boolean.FALSE;

        if (cursoExistente.isPresent()) {
            List<InscripcionesEntity> inscripcionesEntity = inscripcionesRepository
                    .findByCursos(cursoExistente.get());

            for (InscripcionesEntity it : inscripcionesEntity) {
                it.setEstatus(false);
                inscripcionesRepository.save(it);
            }

            cursoExistente.get().setEstatus(false);
            cursosRepository.save(cursoExistente.get());

            response = Boolean.TRUE;
        }

        return response;
    }

}
