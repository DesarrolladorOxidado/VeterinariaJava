package com.guille.controladores;

import com.guille.modelos.Consulta;

public class ControladorConsultas {

    public ControladorConsultas(){

    }

    public Consulta crearConsulta(String motivo, String diagnostico, String tratamiento, String observaciones){
        Consulta consulta = new Consulta(motivo);

        consulta.setDiagnostico(diagnostico);
        consulta.setTratamiento(tratamiento);
        consulta.setObservaciones(observaciones);

        return consulta;
    }


}
