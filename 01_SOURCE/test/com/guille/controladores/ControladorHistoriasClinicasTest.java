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

public class ControladorHistoriasClinicasTest {

    private ControladorHistoriasClinicas controladorHistoriasClinicas;
    private ControladorConsultas controladorConsultas;
    private Mascota mascota;
    private Veterinario veterinario;

    @BeforeEach
    public void setUp() throws SQLException {
        ConexionBD conexionBD = new ConexionBD(ConexionBD.Ambiente.TEST);
        GestorTransacciones gestorTransacciones = new GestorTransacciones(conexionBD);
        BaseDatosTestDAO baseDatosTestDAO = new BaseDatosTestDAO(conexionBD);
        baseDatosTestDAO.borrarDatos();

        VeterinarioDAO veterinarioDAO = new VeterinarioDAO(conexionBD);
        DuenioDAO duenioDAO = new DuenioDAO(conexionBD);
        HistoriaClinicaDAO historiaClinicaDAO = new HistoriaClinicaDAO(conexionBD);
        MascotaDAO mascotaDAO = new MascotaDAO(conexionBD);
        ConsultaDAO consultaDAO = new ConsultaDAO(conexionBD);

        RegistroMascotaService registroMascotaService = new RegistroMascotaService(mascotaDAO,historiaClinicaDAO,gestorTransacciones);
        RegistrarConsultaService registrarConsultaService = new RegistrarConsultaService(consultaDAO,historiaClinicaDAO,gestorTransacciones);

        ControladorVeterinarios controladorVeterinarios = new ControladorVeterinarios(veterinarioDAO);
        this.controladorHistoriasClinicas = new ControladorHistoriasClinicas(historiaClinicaDAO);
        ControladorDuenios controladorDuenios = new ControladorDuenios(duenioDAO);
        ControladorMascotas controladorMascotas = new ControladorMascotas(mascotaDAO, registroMascotaService);
        this.controladorConsultas = new ControladorConsultas(registrarConsultaService);

        this.veterinario = controladorVeterinarios.registrarVeterinario("Julius","Hibbert",TipoDocumento.DNI,"123","1234","MV-5");
        Duenio duenio = controladorDuenios.registrarDuenio("Cosme","Fulanito", TipoDocumento.DNI,"1234","5412");
        this.mascota = controladorMascotas.registrarMascota("Mateo",TipoMascota.PERRO,"Border Collie",LocalDate.of(2022,7,13),24.2,duenio.getIdDuenio());
    }

    @Test
    public void debeDevolverIdDeHistoriaClinica() throws SQLException{

        Assertions.assertEquals(this.mascota.getHistoriaClinica().getIdHistoriaClinica(),this.controladorHistoriasClinicas.obtenerIdHistoriaClinica(mascota.getIdMascota()));

    }

    @Test
    public void debeDevolverHistoriaClinicaConConsultas() throws SQLException{
            this.controladorConsultas.registrarConsulta("Control","","","",veterinario,mascota.getHistoriaClinica().getIdHistoriaClinica());

            Assertions.assertEquals(1, this.controladorHistoriasClinicas.obtenerHistoriaClinica(mascota.getIdMascota()).obtenerConsultas().size());
    }

    @Test
    public void debeDevolverHistoriaClinicaConVariasConsultas() throws SQLException{
        int cantidadConsultas = 4;
        for (int i = 1; i <= cantidadConsultas; i++) {
            this.controladorConsultas.registrarConsulta("Control " + i, "", "", "", veterinario, mascota.getHistoriaClinica().getIdHistoriaClinica());
        }

        Assertions.assertEquals(cantidadConsultas, this.controladorHistoriasClinicas.obtenerHistoriaClinica(mascota.getIdMascota()).obtenerConsultas().size());
    }

    @Test
    public void debeDevolverHistoriaClinicaSinConsultas() throws SQLException{
        Assertions.assertTrue(this.controladorHistoriasClinicas.obtenerHistoriaClinica(mascota.getIdMascota()).obtenerConsultas().isEmpty());
    }
}
