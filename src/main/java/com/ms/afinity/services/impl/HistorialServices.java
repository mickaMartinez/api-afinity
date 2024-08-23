package com.ms.afinity.services.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.ms.afinity.entity.AccionEntity;
import com.ms.afinity.entity.HistorialEntity;
import com.ms.afinity.repository.HistorialRepository;
import com.ms.afinity.services.IHistorialServices;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class HistorialServices implements IHistorialServices {
    private HistorialRepository historialRepository;

    @PersistenceContext(unitName = "afinity-repo")
    private EntityManager entityManager;

    public HistorialServices(HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    public void guardarHistorial(Integer idAccion, Long idAlumno, Integer idCurso, Date fecha) {
        AccionEntity accionEntity = entityManager.find(AccionEntity.class, idAccion);

        HistorialEntity historialEntity = new HistorialEntity();

        historialEntity.setAccion(accionEntity);
        historialEntity.setIdAlumnos(idAlumno);
        historialEntity.setIdCursos(idCurso);
        historialEntity.setFecha(new Date());

        historialRepository.save(historialEntity);
    }

}
