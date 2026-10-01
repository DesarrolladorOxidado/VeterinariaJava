package com.guille.controladores;

import com.guille.BaseDatosTestDAO;
import com.guille.configuracion.FechaHora;
import com.guille.modelos.*;
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
    private TipoMascota perro;

    @BeforeEach
    public void setUp()throws SQLException {
        ConexionBD conexionBD = new ConexionBD(ConexionBD.Ambiente.TEST);
        BaseDatosTestDAO baseDatosTestDAO = new BaseDatosTestDAO(conexionBD);
        GestorTransacciones gestorTransacciones = new GestorTransacciones(conexionBD);

        baseDatosTestDAO.borrarDatos();

        DuenioDAO duenioDAO = new DuenioDAO(conexionBD);
        ControladorDuenios controladorDuenios = new ControladorDuenios(duenioDAO);

        TipoDocumento dni = new TipoDocumento("DNI","Documento Nacional de Identidad");
        this.duenio = controladorDuenios.registrarDuenio("Cosme","Fulanito", dni,"221232","232323");
        this.perro = new TipoMascota("PE","Perro");


        HistoriaClinicaDAO historiaClinicaDAO = new HistoriaClinicaDAO(conexionBD);

        MascotaDAO mascotaDAO = new MascotaDAO(conexionBD);
        RegistroMascotaService registroMascotaService = new RegistroMascotaService(mascotaDAO,historiaClinicaDAO,gestorTransacciones);
        controladorMascotas = new ControladorMascotas(mascotaDAO,registroMascotaService);
    }


    @Test
    public void alRegistrarMascotaDebeTenerLosDatosIngresados() throws SQLException{

        LocalDate fechaNacimiento = LocalDate.parse("13/07/2022", DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT));
        Mascota mascota = controladorMascotas.registrarMascota("Mateo", perro,"Border Collie", fechaNacimiento,24.3,duenio.getIdDuenio());

        Assertions.assertTrue(mascota.getIdMascota() > 0);
        Assertions.assertEquals("Mateo",mascota.getNombre());
        Assertions.assertEquals(perro,mascota.getTipo());
        Assertions.assertEquals("Border Collie",mascota.getRaza());
        Assertions.assertEquals( fechaNacimiento,mascota.getFechaNacimiento());
        Assertions.assertEquals(24.3,mascota.getPeso());
        Assertions.assertEquals(duenio.getIdDuenio(),mascota.getIdDuenio());
        Assertions.assertNotNull(mascota.getHistoriaClinica());
        Assertions.assertTrue(mascota.getHistoriaClinica().getIdHistoriaClinica() > 0);
    }

    @Test
    public void alActualizarDebePersistirNuevosDatos() throws SQLException{

        Mascota mascota = controladorMascotas.registrarMascota("Mateo", perro,"Border Collie", FechaHora.parsearFecha("13/07/2022"),24.3,duenio.getIdDuenio());

        mascota.setNombre("Rubén");
        mascota.setTipo(new TipoMascota("GT","Gato"));
        mascota.setRaza("Persa");
        mascota.setFechaNacimiento(FechaHora.parsearFecha("13/07/2023"));
        mascota.setPeso(23.2);

        controladorMascotas.actualizarMascota(mascota);

        Mascota mascotaRecuperada = controladorMascotas.obtenerMascotasDeUnDuenio(duenio.getIdDuenio()).get(0);

        Assertions.assertEquals(mascota.getIdMascota(), mascotaRecuperada.getIdMascota());
        Assertions.assertEquals(mascota.getNombre(),mascotaRecuperada.getNombre());
        Assertions.assertEquals(mascota.getTipo().getIdTipoMascota(),mascotaRecuperada.getTipo().getIdTipoMascota());
        Assertions.assertEquals(mascota.getRaza(),mascotaRecuperada.getRaza());
        Assertions.assertEquals(mascota.getFechaNacimiento(),mascotaRecuperada.getFechaNacimiento());
        Assertions.assertEquals(mascota.getPeso(),mascotaRecuperada.getPeso());
        Assertions.assertEquals(mascota.getIdDuenio(),mascotaRecuperada.getIdDuenio());
        Assertions.assertEquals(mascota.getFechaAlta(),mascotaRecuperada.getFechaAlta());
    }
}