package com.ms.afinity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ms.afinity.entity.AlumnosEntity;

@Repository
public interface AlumnosRepository extends JpaRepository<AlumnosEntity, Long> {
    @Query("SELECT ma FROM AlumnosEntity ma"
            + " WHERE (:nombre IS NULL OR ma.nombre LIKE UPPER(CONCAT('%',:nombre,'%')))"
            + " AND (:correo IS NULL OR ma.correo = :correo)"
            + " AND (:estatus IS NULL OR ma.estatus = :estatus)")
    List<AlumnosEntity> findAlumnos(@Param(value = "nombre") String nombre, @Param(value = "correo") String correo,
            @Param(value = "estatus") Boolean estatus);
}
