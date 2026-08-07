package com.guille.controladores;

import com.guille.modelos.Consulta;
import com.guille.modelos.HistoriaClinica;
import com.guille.modelos.Mascota;

import java.util.ArrayList;
import java.util.List;

public class ControladorHistoriasClinicas {

    public ControladorHistoriasClinicas(){

    }

    public boolean agregarConsultaHistoriaClinica(Mascota mascota, Consulta consulta){
        mascota.getHistoriaClinica().registrarConsulta(consulta);
        return true;
    }
}
