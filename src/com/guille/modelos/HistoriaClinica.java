package com.guille.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoriaClinica {

    private Date fechaCreacion;
    private Date fechaActualizacion;
    private List<Consulta> consultas;

    public HistoriaClinica() {
        this.fechaCreacion = new Date();
        this.fechaActualizacion = this.fechaCreacion;
        this.consultas = new ArrayList<>();
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void registrarConsulta( Consulta consulta){
        this.consultas.add(consulta);
        this.fechaActualizacion = consulta.getFecha();
    }

    public List<Consulta> obtenerConsultas(){

        return new ArrayList<>(this.consultas);
    }

    @Override
    public String toString() {
        return "HistoriaClinica{" +
                "fechaCreacion=" + fechaCreacion +
                ", fechaActualizacion=" + fechaActualizacion +
                ", consultas=" + consultas +
                '}';
    }
}
