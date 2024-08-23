package com.ms.afinity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms.afinity.bean.ConsultaCursosBean;
import com.ms.afinity.bean.CursosBean;
import com.ms.afinity.config.ResponseService;
import com.ms.afinity.services.ICursosServices;
import com.ms.afinity.services.impl.CursosServices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RestController
@RequestMapping(value = "/cursos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CursosController {
    private ICursosServices cursosServices;

    public CursosController(CursosServices cursosServices) {
        this.cursosServices = cursosServices;
    }

    @GetMapping("")
    public ResponseEntity<ResponseService<ConsultaCursosBean>> traerCursos(@RequestParam Integer pagina) {
        final ResponseService<ConsultaCursosBean> response = new ResponseService<>();
        ConsultaCursosBean dataResponse = this.cursosServices.traerCursos(pagina);

        try {
            return response.getResponse(dataResponse, ResponseService.MSGCORRECTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return response.getResponseError(ResponseService.MSGERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<ResponseService<Boolean>> agregarCurso(@RequestBody CursosBean cursosBean) {
        final ResponseService<Boolean> response = new ResponseService<>();
        Boolean dataResponse = this.cursosServices.agregarCurso(cursosBean);

        try {
            return response.getResponse(dataResponse, ResponseService.MSGCORRECTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return response.getResponseError(ResponseService.MSGERROR);
        }
    }

    @PutMapping("/{idCurso}")
    public ResponseEntity<ResponseService<Boolean>> editarCurso(@PathVariable Integer idCurso,
            @RequestBody CursosBean cursosBean) {
        final ResponseService<Boolean> response = new ResponseService<>();
        Boolean dataResponse = this.cursosServices.editarCurso(idCurso, cursosBean);

        try {
            return response.getResponse(dataResponse, ResponseService.MSGCORRECTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return response.getResponseError(ResponseService.MSGERROR);
        }
    }

    @DeleteMapping("/{idCurso}")
    public ResponseEntity<ResponseService<Boolean>> eliminarCurso(@PathVariable Integer idCurso) {
        final ResponseService<Boolean> response = new ResponseService<>();
        Boolean dataResponse = this.cursosServices.eliminarCurso(idCurso);

        try {
            return response.getResponse(dataResponse, ResponseService.MSGCORRECTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return response.getResponseError(ResponseService.MSGERROR);
        }
    }
}
