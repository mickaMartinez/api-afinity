package com.ms.afinity.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.afinity.bean.AlumnoBusquedaAvanzada;
import com.ms.afinity.bean.AlumnosBean;
import com.ms.afinity.bean.ConsultaAlumnosBean;
import com.ms.afinity.bean.ListaAlumnoBean;
import com.ms.afinity.bean.ListaInscripcionesBean;
import com.ms.afinity.config.ResponseService;
import com.ms.afinity.services.IAlumnosServices;
import com.ms.afinity.services.impl.AlumnosServices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@RestController
@RequestMapping(value = "/alumnos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AlumnosController {
    private IAlumnosServices alumnosServices;

    public AlumnosController(AlumnosServices alumnosServices) {
        this.alumnosServices = alumnosServices;
    }

    @GetMapping("")
    public ResponseEntity<ResponseService<ConsultaAlumnosBean>> traerAlumnos(@RequestParam Integer pagina) {
        final ResponseService<ConsultaAlumnosBean> response = new ResponseService<>();
        ConsultaAlumnosBean dataResponse = this.alumnosServices.traerAlumnos(pagina);

        try {
            return response.getResponse(dataResponse, ResponseService.MSGCORRECTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return response.getResponseError(ResponseService.MSGERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<ResponseService<Boolean>> agregarAlumno(@RequestBody AlumnosBean alumnosBean) {
        final ResponseService<Boolean> response = new ResponseService<>();
        Boolean dataResponse = this.alumnosServices.agregarAlumno(alumnosBean);

        try {
            return response.getResponse(dataResponse, ResponseService.MSGCORRECTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return response.getResponseError(ResponseService.MSGERROR);
        }
    }

    @PutMapping("/{idAlumno}")
    public ResponseEntity<ResponseService<Boolean>> editarAlumno(@PathVariable Long idAlumno,
            @RequestBody AlumnosBean alumnosBean) {
        final ResponseService<Boolean> response = new ResponseService<>();
        Boolean dataResponse = this.alumnosServices.editarAlumno(idAlumno, alumnosBean);

        try {
            return response.getResponse(dataResponse, ResponseService.MSGCORRECTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return response.getResponseError(ResponseService.MSGERROR);
        }
    }

    @DeleteMapping("/{idAlumno}")
    public ResponseEntity<ResponseService<Boolean>> eliminarAlumno(@PathVariable Long idAlumno) {
        final ResponseService<Boolean> response = new ResponseService<>();
        Boolean dataResponse = this.alumnosServices.eliminarAlumno(idAlumno);

        try {
            return response.getResponse(dataResponse, ResponseService.MSGCORRECTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return response.getResponseError(ResponseService.MSGERROR);
        }
    }

    @PostMapping("/avanzada")
    public ResponseEntity<ResponseService<List<ListaAlumnoBean>>> traerAlumnoBusquedaAvanzada(
            @RequestBody AlumnoBusquedaAvanzada alumnoBusquedaAvanzada) {
        final ResponseService<List<ListaAlumnoBean>> response = new ResponseService<>();
        List<ListaAlumnoBean> dataResponse = this.alumnosServices.traerAlumnoBusquedaAvanzada(alumnoBusquedaAvanzada);

        try {
            return response.getResponse(dataResponse, ResponseService.MSGCORRECTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return response.getResponseError(ResponseService.MSGERROR);
        }
    }
}
