package com.ms.afinity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.afinity.entity.CursosEntity;

@Repository
public interface CursosRepository extends JpaRepository<CursosEntity, Integer> {
    List<CursosEntity> findByNombreContainingAndEstatusTrue(String nombre);
}
