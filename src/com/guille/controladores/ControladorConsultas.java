package com.guille.controladores;

import com.guille.modelos.Consulta;
import com.guille.modelos.Veterinario;

public class ControladorConsultas {

    public ControladorConsultas(){

    }

    public Consulta crearConsulta(String motivo, String diagnostico, String tratamiento, String observaciones, Veterinario veterinario){
        Consulta consulta = new Consulta(motivo,veterinario);

        consulta.setDiagnostico(diagnostico);
        consulta.setTratamiento(tratamiento);
        consulta.setObservaciones(observaciones);

        return consulta;
    }


}
