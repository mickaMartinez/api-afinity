package com.ms.afinity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.afinity.entity.AlumnosEntity;

@Repository
public interface AlumnosRepository extends JpaRepository<AlumnosEntity, Long> {
    
}



