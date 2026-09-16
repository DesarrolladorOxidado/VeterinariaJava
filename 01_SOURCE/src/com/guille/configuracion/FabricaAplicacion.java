package com.guille.configuracion;

import com.guille.controladores.*;
import com.guille.persistencia.ConexionBD;
import com.guille.persistencia.GestorTransacciones;
import com.guille.persistencia.dao.*;
import com.guille.servicios.RegistrarConsultaService;
import com.guille.servicios.RegistroMascotaService;
import com.guille.vistas.Aplicacion;

public class FabricaAplicacion {

    public static Aplicacion crear(ConexionBD.Ambiente ambiente){
        ConexionBD conexionBD = new ConexionBD(ambiente);

        GestorTransacciones gestorTransacciones = new GestorTransacciones(conexionBD);

        VeterinarioDAO veterinarioDAO = new VeterinarioDAO(conexionBD);
        DuenioDAO duenioDAO = new DuenioDAO(conexionBD);
        MascotaDAO mascotaDAO = new MascotaDAO(conexionBD);
        HistoriaClinicaDAO historiaClinicaDAO = new HistoriaClinicaDAO(conexionBD);
        ConsultaDAO consultaDAO = new ConsultaDAO(conexionBD);

        RegistroMascotaService registroMascotaService = new RegistroMascotaService(mascotaDAO,historiaClinicaDAO,gestorTransacciones);
        RegistrarConsultaService registrarConsultaService = new RegistrarConsultaService(consultaDAO,historiaClinicaDAO,gestorTransacciones);

        ControladorVeterinarios controladorVeterinarios = new ControladorVeterinarios(veterinarioDAO);
        ControladorDuenios controladorDuenios = new ControladorDuenios(duenioDAO);
        ControladorMascotas controladorMascotas = new ControladorMascotas(mascotaDAO, registroMascotaService);
        ControladorHistoriasClinicas controladorHistoriasClinicas = new ControladorHistoriasClinicas(historiaClinicaDAO);
        ControladorConsultas controladorConsultas = new ControladorConsultas(registrarConsultaService);

        Controladores controladores = new Controladores();

        controladores.setControladorVeterinarios(controladorVeterinarios);
        controladores.setControladorDuenios(controladorDuenios);
        controladores.setControladorMascotas(controladorMascotas);
        controladores.setControladorHistoriasClinicas(controladorHistoriasClinicas);
        controladores.setControladorConsultas(controladorConsultas);

        return new Aplicacion(controladores);
    }
}
