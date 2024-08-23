package com.ms.afinity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.afinity.entity.AlumnosEntity;
import com.ms.afinity.entity.CursosEntity;
import com.ms.afinity.entity.InscripcionesEntity;

@Repository
public interface InscripcionesRepository extends JpaRepository<InscripcionesEntity, Long> {
    List<InscripcionesEntity> findByAlumnos(AlumnosEntity alumnosEntity);

    List<InscripcionesEntity> findByAlumnosAndEstatusIsTrue(AlumnosEntity alumnosEntity);

    List<InscripcionesEntity> findByCursos(CursosEntity cursosEntity);
}
