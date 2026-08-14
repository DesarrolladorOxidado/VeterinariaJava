package com.guille.modelos;

import java.time.LocalDateTime;

public class Consulta {

    private static final String CAMPO_DIAGNOSTICO_INCOMPLETO = "Sin diagnóstico";
    private static final String CAMPO_TRATAMIENTO_INCOMPLETO = "Sin tratamiento";
    private static final String CAMPO_OBSERVACIONES_INCOMPLETO = "Sin observaciones";

    //La fecha no se modifica, se establece únicamente al crear la consulta
    private final LocalDateTime fecha;
    private String motivo;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    //El veterinario no puede modificarse, se establece al crear la consulta
    private Veterinario veterinario;

    public Consulta( String motivo) {
        this.fecha = LocalDateTime.now();
        this.motivo = motivo;

        this.diagnostico = CAMPO_DIAGNOSTICO_INCOMPLETO;
        this.tratamiento = CAMPO_TRATAMIENTO_INCOMPLETO;
        this.observaciones = CAMPO_OBSERVACIONES_INCOMPLETO;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico.isEmpty() ? CAMPO_DIAGNOSTICO_INCOMPLETO : diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento.isEmpty() ? CAMPO_TRATAMIENTO_INCOMPLETO : tratamiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones.isEmpty() ? CAMPO_OBSERVACIONES_INCOMPLETO : observaciones;
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
