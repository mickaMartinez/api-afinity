package com.ms.afinity.bean;

import java.util.List;

import lombok.Data;

@Data
public class ConsultaCursosBean {
    private List<ListaCursosBean> cursos;
    private Long totalRegistros;
}
