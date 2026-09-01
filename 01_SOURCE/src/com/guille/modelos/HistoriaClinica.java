package com.guille.modelos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoriaClinica {

    private final LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private final List<Consulta> consultas;

    public HistoriaClinica() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = this.fechaCreacion;
        this.consultas = new ArrayList<>();
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public LocalDateTime getFechaCreacion() {
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
