package com.guille.configuracion;

import com.guille.controladores.*;
import com.guille.persistencia.ConexionBD;
import com.guille.persistencia.dao.*;
import com.guille.vistas.Aplicacion;

public class FabricaAplicacion {

    public static Aplicacion crear(ConexionBD.Ambiente ambiente){
        ConexionBD conexionBD = new ConexionBD(ambiente);

        VeterinarioDAO veterinarioDAO = new VeterinarioDAO(conexionBD);
        DuenioDAO duenioDAO = new DuenioDAO(conexionBD);
        MascotaDAO mascotaDAO = new MascotaDAO(conexionBD);
        HistoriaClinicaDAO historiaClinicaDAO = new HistoriaClinicaDAO(conexionBD);
        ConsultaDAO consultaDAO = new ConsultaDAO(conexionBD);


        ControladorVeterinarios controladorVeterinarios = new ControladorVeterinarios(veterinarioDAO);
        ControladorDuenios controladorDuenios = new ControladorDuenios(duenioDAO);
        ControladorMascotas controladorMascotas = new ControladorMascotas(mascotaDAO);
        ControladorConsultas controladorConsultas = new ControladorConsultas(consultaDAO,historiaClinicaDAO);

        Controladores controladores = new Controladores();

        controladores.setControladorVeterinarios(controladorVeterinarios);
        controladores.setControladorDuenios(controladorDuenios);
        controladores.setControladorMascotas(controladorMascotas);
        controladores.setControladorConsultas(controladorConsultas);

        return new Aplicacion(controladores);
    }
}
