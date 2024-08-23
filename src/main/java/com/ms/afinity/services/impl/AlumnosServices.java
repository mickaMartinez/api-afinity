package com.ms.afinity.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ms.afinity.bean.AlumnosBean;
import com.ms.afinity.bean.ConsultaAlumnosBean;
import com.ms.afinity.bean.ListaAlumnoBean;
import com.ms.afinity.entity.AlumnosEntity;
import com.ms.afinity.entity.InscripcionesEntity;
import com.ms.afinity.repository.AlumnosRepository;
import com.ms.afinity.repository.InscripcionesRepository;
import com.ms.afinity.services.IAlumnosServices;

import jakarta.transaction.Transactional;

@Service
public class AlumnosServices implements IAlumnosServices {
    private AlumnosRepository alumnosRepository;
    private InscripcionesRepository inscripcionesRepository;

    public AlumnosServices(AlumnosRepository alumnosRepository, InscripcionesRepository inscripcionesRepository) {
        this.alumnosRepository = alumnosRepository;
        this.inscripcionesRepository = inscripcionesRepository;
    }

    @Override
    public ConsultaAlumnosBean traerAlumnos(Integer pagina) {
        Pageable pageable = PageRequest.of(pagina, 10);
        Page<AlumnosEntity> alumnosRegistros = alumnosRepository.findAll(pageable);
        List<AlumnosEntity> alumnos = alumnosRegistros.getContent();

        ConsultaAlumnosBean consulta = new ConsultaAlumnosBean();
        List<ListaAlumnoBean> listaFinal = new ArrayList<>();

        for (AlumnosEntity alumno : alumnos) {
            ListaAlumnoBean lista = new ListaAlumnoBean();

            lista.setIdAlumno(alumno.getIdAlumnos());
            lista.setNombre(alumno.getNombre());
            lista.setApellidoPaterno(alumno.getApellidoPaterno());
            lista.setApellidoMaterno(alumno.getApellidoMaterno());
            lista.setCorreo(alumno.getCorreo());
            lista.setTelefono(alumno.getTelefono());
            lista.setEstatus(alumno.getEstatus());

            listaFinal.add(lista);
        }

        consulta.setAlumnos(listaFinal);
        consulta.setTotalRegistros(alumnosRegistros.getTotalElements());

        return consulta;
    }

    @Override
    @Transactional
    public Boolean agregarAlumno(AlumnosBean alumnosBean) {
        AlumnosEntity alumnosEntity = new AlumnosEntity();
        alumnosEntity.setNombre(alumnosBean.getNombre().toLowerCase().toUpperCase().trim());
        alumnosEntity.setApellidoPaterno(alumnosBean.getApellidoPaterno().toLowerCase().toUpperCase().trim());
        alumnosEntity.setApellidoMaterno(alumnosBean.getApellidoMaterno().toLowerCase().toUpperCase().trim());
        alumnosEntity.setCorreo(alumnosBean.getCorreo());
        alumnosEntity.setTelefono(alumnosBean.getTelefono());
        alumnosEntity.setEstatus(alumnosBean.getEstatus());

        alumnosRepository.save(alumnosEntity);

        return true;
    }

    @Override
    @Transactional
    public Boolean editarAlumno(Long idAlumno, AlumnosBean alumnosBean) {
        Optional<AlumnosEntity> alumnoExistente = alumnosRepository.findById(idAlumno);
        Boolean response = Boolean.FALSE;

        if (alumnoExistente.isPresent()) {
            AlumnosEntity alumno = alumnoExistente.get();

            alumno.setNombre(alumnosBean.getNombre().toLowerCase().toUpperCase().trim());
            alumno.setApellidoPaterno(
                    alumnosBean.getApellidoPaterno().toLowerCase().toUpperCase().trim());
            alumno.setApellidoMaterno(
                    alumnosBean.getApellidoMaterno().toLowerCase().toUpperCase().trim());
            alumno.setCorreo(alumnosBean.getCorreo());
            alumno.setTelefono(alumnosBean.getTelefono());
            alumno.setEstatus(alumnosBean.getEstatus());

            alumnosRepository.save(alumno);
            response = Boolean.TRUE;
        }

        return response;
    }

    @Override
    @Transactional
    public Boolean eliminarAlumno(Long idAlumno) {
        Optional<AlumnosEntity> alumnoExistente = alumnosRepository.findById(idAlumno);
        Boolean response = Boolean.FALSE;

        if (alumnoExistente.isPresent()) {
            List<InscripcionesEntity> inscripcionesEntity = inscripcionesRepository
                    .findByAlumnos(alumnoExistente.get());

            for (InscripcionesEntity it : inscripcionesEntity) {
                it.setEstatus(false);
                inscripcionesRepository.save(it);
            }

            alumnoExistente.get().setEstatus(false);
            alumnosRepository.save(alumnoExistente.get());

            response = Boolean.TRUE;
        }

        return response;
    }

}