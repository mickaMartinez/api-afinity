package com.ms.afinity.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ms.afinity.bean.InscripcionBean;
import com.ms.afinity.bean.ListaCursosBean;
import com.ms.afinity.bean.ListaInscripcionesBean;
import com.ms.afinity.entity.AlumnosEntity;
import com.ms.afinity.entity.CursosEntity;
import com.ms.afinity.entity.InscripcionesEntity;
import com.ms.afinity.repository.CursosRepository;
import com.ms.afinity.repository.InscripcionesRepository;
import com.ms.afinity.services.IInscripcionesServices;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class InscripcionesServices implements IInscripcionesServices {
    private InscripcionesRepository inscripcionesRepository;
    private CursosRepository cursosRepository;

    @PersistenceContext(unitName = "afinity-repo")
    private EntityManager entityManager;

    public InscripcionesServices(InscripcionesRepository inscripcionesRepository, CursosRepository cursosRepository) {
        this.inscripcionesRepository = inscripcionesRepository;
        this.cursosRepository = cursosRepository;
    }

    @Override
    @Transactional
    public List<ListaInscripcionesBean> traerMaterias(Long idAlumno) {
        AlumnosEntity alumnosEntity = entityManager.find(AlumnosEntity.class, idAlumno);
        List<InscripcionesEntity> materias = inscripcionesRepository.findByAlumnosAndEstatusIsTrue(alumnosEntity);

        List<ListaInscripcionesBean> listaFinal = new ArrayList<>();
        for (InscripcionesEntity materia : materias) {
            ListaInscripcionesBean lista = new ListaInscripcionesBean();

            lista.setIdInscripcion(materia.getIdInscripciones());
            lista.setNombre(materia.getCursos().getNombre());

            listaFinal.add(lista);
        }

        return listaFinal;
    }

    @Override
    @Transactional
    public List<ListaCursosBean> traerNombreMaterias(String curso) {
        List<CursosEntity> cursos = cursosRepository.findByNombreContainingAndEstatusTrue(curso);

        List<ListaCursosBean> listaFinal = new ArrayList<>();
        for (CursosEntity it : cursos) {
            ListaCursosBean lista = new ListaCursosBean();

            lista.setIdCurso(it.getIdCursos());
            lista.setNombre(it.getNombre());

            listaFinal.add(lista);
        }

        return listaFinal;
    }

    @Override
    @Transactional
    public Boolean agregarInscripcion(InscripcionBean inscripcionBean) {
        AlumnosEntity alumnosEntity = entityManager.find(AlumnosEntity.class, inscripcionBean.getIdAlumno());

        for (Integer cursos : inscripcionBean.getCursos()) {
            InscripcionesEntity inscripcionesEntity = new InscripcionesEntity();

            inscripcionesEntity.setAlumnos(alumnosEntity);
            inscripcionesEntity.setCursos(entityManager.find(CursosEntity.class, cursos));
            inscripcionesEntity.setEstatus(true);

            inscripcionesRepository.save(inscripcionesEntity);
        }

        return true;
    }

    @Override
    @Transactional
    public Boolean eliminarInscripcion(Long idInscripcion) {
        Optional<InscripcionesEntity> inscripcionExistente = inscripcionesRepository.findById(idInscripcion);
        Boolean response = Boolean.FALSE;

        if (inscripcionExistente.isPresent()) {
            inscripcionExistente.get().setEstatus(false);
            inscripcionesRepository.save(inscripcionExistente.get());

            response = Boolean.TRUE;
        }

        return response;
    }

}
