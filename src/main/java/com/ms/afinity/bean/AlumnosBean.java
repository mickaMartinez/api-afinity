package com.ms.afinity.bean;

import lombok.Data;

@Data
public class AlumnosBean {
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String telefono;
    private Boolean estatus;
}
