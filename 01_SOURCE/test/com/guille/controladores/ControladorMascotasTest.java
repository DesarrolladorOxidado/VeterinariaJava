package com.guille.controladores;

import com.guille.BaseDatosTestDAO;
import com.guille.modelos.Duenio;
import com.guille.modelos.Mascota;
import com.guille.modelos.TipoDocumento;
import com.guille.modelos.TipoMascota;
import com.guille.persistencia.ConexionBD;
import com.guille.persistencia.GestorTransacciones;
import com.guille.persistencia.dao.DuenioDAO;
import com.guille.persistencia.dao.HistoriaClinicaDAO;
import com.guille.persistencia.dao.MascotaDAO;
import com.guille.servicios.RegistroMascotaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class ControladorMascotasTest {

    private ControladorMascotas controladorMascotas;
    private Duenio duenio;

    @BeforeEach
    public void setUp()throws SQLException {
        ConexionBD conexionBD = new ConexionBD(ConexionBD.Ambiente.TEST);
        BaseDatosTestDAO baseDatosTestDAO = new BaseDatosTestDAO(conexionBD);
        GestorTransacciones gestorTransacciones = new GestorTransacciones(conexionBD);

        baseDatosTestDAO.borrarDatos();

        DuenioDAO duenioDAO = new DuenioDAO(conexionBD);
        ControladorDuenios controladorDuenios = new ControladorDuenios(duenioDAO);

        this.duenio = controladorDuenios.registrarDuenio("Cosme","Fulanito", TipoDocumento.DNI,"221232","232323");

        HistoriaClinicaDAO historiaClinicaDAO = new HistoriaClinicaDAO(conexionBD);

        MascotaDAO mascotaDAO = new MascotaDAO(conexionBD);
        RegistroMascotaService registroMascotaService = new RegistroMascotaService(mascotaDAO,historiaClinicaDAO,gestorTransacciones);
        controladorMascotas = new ControladorMascotas(mascotaDAO,registroMascotaService);
    }


    @Test
    public void alRegistrarMascotaDebeTenerLosDatosIngresados() throws SQLException{

        LocalDate fechaNacimiento = LocalDate.parse("13/07/2022", DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT));
        Mascota mascota = controladorMascotas.registrarMascota("Mateo", TipoMascota.PERRO,"Border Collie", fechaNacimiento,24.3,duenio.getIdDuenio());

        Assertions.assertTrue(mascota.getId() > 0);
        Assertions.assertEquals("Mateo",mascota.getNombre());
        Assertions.assertEquals(TipoMascota.PERRO,mascota.getTipo());
        Assertions.assertEquals("Border Collie",mascota.getRaza());
        Assertions.assertEquals( fechaNacimiento,mascota.getFechaNacimiento());
        Assertions.assertEquals(24.3,mascota.getPeso());
        Assertions.assertEquals(duenio.getIdDuenio(),mascota.getIdDuenio());
        Assertions.assertNotNull(mascota.getHistoriaClinica());
        Assertions.assertTrue(mascota.getHistoriaClinica().getId() > 0);
    }
}