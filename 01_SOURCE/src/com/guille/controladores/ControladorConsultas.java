package com.guille.controladores;

import com.guille.modelos.Consulta;
import com.guille.modelos.HistoriaClinica;
import com.guille.modelos.Veterinario;
import com.guille.persistencia.dao.ConsultaDAO;
import com.guille.persistencia.dao.HistoriaClinicaDAO;
import com.guille.servicios.RegistrarConsultaService;

import java.sql.SQLException;

public class ControladorConsultas {

    private final RegistrarConsultaService registrarConsultaService;

    public ControladorConsultas(RegistrarConsultaService registrarConsultaService){

        this.registrarConsultaService = registrarConsultaService;
    }

    private Consulta crearConsulta(String motivo, String diagnostico, String tratamiento, String observaciones, Veterinario veterinario, int idHistoriaClinica){
        Consulta consulta = new Consulta(motivo,veterinario,idHistoriaClinica);

        consulta.setDiagnostico(diagnostico);
        consulta.setTratamiento(tratamiento);
        consulta.setObservaciones(observaciones);

        return consulta;
    }

    public Consulta registrarConsulta(String motivo, String diagnostico, String tratamiento, String observaciones, Veterinario veterinario, HistoriaClinica historiaClinica) throws SQLException {

        Consulta consulta = crearConsulta(motivo,diagnostico,tratamiento,observaciones,veterinario,historiaClinica.getId());
        Consulta consultaBD = this.registrarConsultaService.registrarConsulta(consulta,historiaClinica);

        return consultaBD;
    }
}
