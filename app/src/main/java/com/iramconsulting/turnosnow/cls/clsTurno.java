package com.iramconsulting.turnosnow.cls;

import java.io.Serializable;

/**
 * Created by RAMOS on 24/12/2016.
 */

public class clsTurno implements Serializable {
    private static final long serialVersionUID=1L;
    private String fecha;
    private String hora;
    private String sucursal;
    private int id_turno;
    private String estado;

//Constructor
    public clsTurno(String fecha, String hora, String sucursal, int id_turno, String estado) {
        this.fecha = fecha;
        this.hora = hora;
        this.sucursal = sucursal;
        this.id_turno = id_turno;
        this.estado = estado;
    }


//Get and Set
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public int getId_turno() {
        return id_turno;
    }

    public void setId_turno(int id_turno) {
        this.id_turno = id_turno;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }




}



