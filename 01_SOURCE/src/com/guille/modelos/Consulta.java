package com.guille.modelos;

import java.time.LocalDateTime;

public class Consulta {

    private static final String CAMPO_DIAGNOSTICO_INCOMPLETO = "Sin diagnóstico";
    private static final String CAMPO_TRATAMIENTO_INCOMPLETO = "Sin tratamiento";
    private static final String CAMPO_OBSERVACIONES_INCOMPLETO = "Sin observaciones";


    private int idConsulta;
    //La fecha no se modifica, se establece únicamente al crear la consulta
    private final LocalDateTime fecha;
    final private String motivo;
    private String diagnostico;
    private String tratamiento;
    private String observaciones;
    //El veterinario no puede modificarse, se establece al crear la consulta
    private final Veterinario veterinario;
    private final int idHistoriaClinica;

    public Consulta( String motivo, Veterinario veterinario, int idHistoriaClinica) {
        this.fecha = LocalDateTime.now();
        this.motivo = motivo;
        this.veterinario = veterinario;
        this.idHistoriaClinica = idHistoriaClinica;

        this.diagnostico = CAMPO_DIAGNOSTICO_INCOMPLETO;
        this.tratamiento = CAMPO_TRATAMIENTO_INCOMPLETO;
        this.observaciones = CAMPO_OBSERVACIONES_INCOMPLETO;
    }

    public Consulta(int idConsulta, LocalDateTime fecha, String motivo, String diagnostico, String tratamiento, String observaciones, Veterinario veterinario, int idHistoriaClinica) {
        this.idConsulta = idConsulta;
        this.fecha = fecha;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
        this.veterinario = veterinario;
        this.idHistoriaClinica = idHistoriaClinica;
    }

    public int getIdConsulta(){ return this.idConsulta; }

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

    public int getIdHistoriaClinica(){ return this.idHistoriaClinica;}

    @Override
    public String toString() {
        return "Consulta{ idConsulta= " + this.idConsulta +
                "fecha=" + fecha +
                ", motivo='" + motivo + '\'' +
                ", diagnostico='" + diagnostico + '\'' +
                ", tratamiento='" + tratamiento + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", veterinario=" + veterinario +
                ", historia clinica=" + idHistoriaClinica +
                '}';
    }
}
