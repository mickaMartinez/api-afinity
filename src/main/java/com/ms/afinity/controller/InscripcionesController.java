package com.ms.afinity.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.afinity.bean.InscripcionBean;
import com.ms.afinity.bean.ListaCursosBean;
import com.ms.afinity.bean.ListaInscripcionesBean;
import com.ms.afinity.config.ResponseService;
import com.ms.afinity.services.IInscripcionesServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/inscripciones")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InscripcionesController {

    private IInscripcionesServices inscripcionesServices;

    public InscripcionesController(IInscripcionesServices inscripcionesServices) {
        this.inscripcionesServices = inscripcionesServices;
    }

    @GetMapping("/{idAlumno}")
    public ResponseEntity<ResponseService<List<ListaInscripcionesBean>>> traerMaterias(@PathVariable Long idAlumno) {
        final ResponseService<List<ListaInscripcionesBean>> response = new ResponseService<>();
        List<ListaInscripcionesBean> dataResponse = this.inscripcionesServices.traerMaterias(idAlumno);

        try {
            return response.getResponse(dataResponse, ResponseService.MSGCORRECTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return response.getResponseError(ResponseService.MSGERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<ResponseService<List<ListaCursosBean>>> traerNombreMaterias(@RequestParam String curso) {
        final ResponseService<List<ListaCursosBean>> response = new ResponseService<>();
        List<ListaCursosBean> dataResponse = this.inscripcionesServices.traerNombreMaterias(curso);

        try {
            return response.getResponse(dataResponse, ResponseService.MSGCORRECTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return response.getResponseError(ResponseService.MSGERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<ResponseService<Boolean>> agregarInscripcion(@RequestBody InscripcionBean inscripcionBean) {
        final ResponseService<Boolean> response = new ResponseService<>();
        Boolean dataResponse = this.inscripcionesServices.agregarInscripcion(inscripcionBean);

        try {
            return response.getResponse(dataResponse, ResponseService.MSGCORRECTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return response.getResponseError(ResponseService.MSGERROR);
        }
    }

    @DeleteMapping("/{idInscripcion}")
    public ResponseEntity<ResponseService<Boolean>> editarAlumno(@PathVariable Long idInscripcion) {
        final ResponseService<Boolean> response = new ResponseService<>();
        Boolean dataResponse = this.inscripcionesServices.eliminarInscripcion(idInscripcion);

        try {
            return response.getResponse(dataResponse, ResponseService.MSGCORRECTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return response.getResponseError(ResponseService.MSGERROR);
        }
    }
}
