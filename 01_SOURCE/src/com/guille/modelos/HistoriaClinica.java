package com.guille.modelos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoriaClinica {

    private int id;
    private int idMascota;
    private final LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private final List<Consulta> consultas;

    public HistoriaClinica(int idMascota) {
        this.idMascota = idMascota;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = this.fechaCreacion;
        this.consultas = new ArrayList<>();
    }

    public HistoriaClinica(int id, int idMascota, LocalDateTime fechaCreacion,LocalDateTime fechaActualizacion, List<Consulta> consultas) {
        this.id = id;
        this.idMascota = idMascota;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
        this.consultas = new ArrayList<>(consultas);
    }

    public int getId(){ return this.id; }

    public int getIdMascota(){ return this.idMascota;}

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public List<Consulta> obtenerConsultas(){

        return new ArrayList<>(this.consultas);
    }

    @Override
    public String toString() {
        return "HistoriaClinica{" +
                "id = " + id +
                ", idMascota = " + idMascota +
                ", fechaCreacion =" + fechaCreacion +
                ", fechaActualizacion=" + fechaActualizacion +
                ", consultas=" + consultas +
                '}';
    }
}
