package com.ms.afinity.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListaCursosBean extends CursosBean {
    private Integer idCurso;
}
