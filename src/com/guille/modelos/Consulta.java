package com.guille.modelos;

import java.util.Date;

public class Consulta {

    private final String CAMPO_INCOMPLETO = "Pendiente";

    //La fecha no se modifica, se establece únicamente al crear la consulta
    private Date fecha;
    private String motivo;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    //El veterinario no puede modificarse, se establece al crear la consulta
    private Veterinario veterinario;

    public Consulta( String motivo) {
        this.fecha = new Date();
        this.motivo = motivo;

        this.diagnostico = CAMPO_INCOMPLETO;
        this.tratamiento = CAMPO_INCOMPLETO;
        this.observaciones = CAMPO_INCOMPLETO;
    }

    public Consulta(String motivo, String diagnostico, String tratamiento, String observaciones, Veterinario veterinario) {
        this.fecha = new Date();
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
        this.veterinario = veterinario;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "fecha=" + fecha +
                ", motivo='" + motivo + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", tratamiento='" + tratamiento + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", veterinario=" + veterinario +
                '}';
    }
}
