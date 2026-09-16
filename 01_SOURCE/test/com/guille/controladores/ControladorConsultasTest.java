package com.guille.controladores;


import com.guille.BaseDatosTestDAO;
import com.guille.modelos.*;
import com.guille.persistencia.ConexionBD;
import com.guille.persistencia.GestorTransacciones;
import com.guille.persistencia.dao.*;
import com.guille.servicios.RegistrarConsultaService;
import com.guille.servicios.RegistroMascotaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;

public class ControladorConsultasTest {

    private ControladorConsultas controladorConsultas;
    private Veterinario veterinario;
    private Mascota mascota;

    @BeforeEach
    public void setUp() throws SQLException {

        ConexionBD conexionBD = new ConexionBD(ConexionBD.Ambiente.TEST);
        BaseDatosTestDAO baseDatosTestDAO = new BaseDatosTestDAO(conexionBD);
        GestorTransacciones gestorTransacciones = new GestorTransacciones(conexionBD);

        baseDatosTestDAO.borrarDatos();

        DuenioDAO duenioDAO = new DuenioDAO(conexionBD);
        MascotaDAO mascotaDAO = new MascotaDAO(conexionBD);
        ConsultaDAO consultaDAO = new ConsultaDAO(conexionBD);
        HistoriaClinicaDAO historiaClinicaDAO = new HistoriaClinicaDAO(conexionBD);
        VeterinarioDAO veterinarioDAO = new VeterinarioDAO(conexionBD);

        RegistroMascotaService registroMascotaService = new RegistroMascotaService(mascotaDAO,historiaClinicaDAO,gestorTransacciones);
        RegistrarConsultaService registrarConsultaService = new RegistrarConsultaService(consultaDAO,historiaClinicaDAO,gestorTransacciones);


        ControladorVeterinarios controladorVeterinarios = new ControladorVeterinarios(veterinarioDAO);
        ControladorDuenios controladorDuenios = new ControladorDuenios(duenioDAO);
        ControladorMascotas controladorMascotas = new ControladorMascotas(mascotaDAO, registroMascotaService);

        Duenio duenio = controladorDuenios.registrarDuenio("Cosme", "Fulanito", TipoDocumento.DNI, "221232", "232323");
        mascota = controladorMascotas.registrarMascota("Mateo", TipoMascota.PERRO, "Border Collie", LocalDate.of(2022, 7, 13), 24.3, duenio.getIdDuenio());
        veterinario = controladorVeterinarios.registrarVeterinario("Julius", "Hibbert", TipoDocumento.DNI, "123522", "15555", "52");

        controladorConsultas = new ControladorConsultas(registrarConsultaService);
    }

    @Test
    public void alRegistrarConsultaDebeTenerLosDatosIngresados() throws SQLException {

        Consulta consulta = controladorConsultas.registrarConsulta("Control", "Sin datos", "Sin datos", "Sin datos", veterinario, mascota.getHistoriaClinica().getId());

        Assertions.assertTrue(consulta.getId() > 0);
        Assertions.assertEquals("Control", consulta.getMotivo());
        Assertions.assertEquals("Sin datos", consulta.getDiagnostico());
        Assertions.assertEquals("Sin datos", consulta.getTratamiento());
        Assertions.assertEquals("Sin datos", consulta.getObservaciones());
        Assertions.assertEquals(veterinario.getIdVeterinario(), consulta.getVeterinario().getIdVeterinario());
        Assertions.assertEquals(mascota.getHistoriaClinica().getId(), consulta.getIdHistoriaClinica());
    }
}