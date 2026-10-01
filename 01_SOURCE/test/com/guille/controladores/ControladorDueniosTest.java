package com.guille.controladores;

import com.guille.BaseDatosTestDAO;
import com.guille.configuracion.FechaHora;
import com.guille.modelos.Duenio;
import com.guille.modelos.Mascota;
import com.guille.modelos.TipoDocumento;
import com.guille.persistencia.ConexionBD;
import com.guille.persistencia.dao.DuenioDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ControladorDueniosTest {

    private ControladorDuenios controladorDuenios;
    private TipoDocumento dni;
    private BaseDatosTestDAO baseDatosTestDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        ConexionBD conexionBD = new ConexionBD(ConexionBD.Ambiente.TEST);
        baseDatosTestDAO = new BaseDatosTestDAO(conexionBD);

        baseDatosTestDAO.borrarDatos();

        DuenioDAO duenioDAO = new DuenioDAO(conexionBD);
        controladorDuenios = new ControladorDuenios(duenioDAO);

        dni = new TipoDocumento("DNI","Documento Nacional de Identidad");
    }

    @Test
    public void alRegistrarDuenioDebeTenerLosDatosIngresados() throws SQLException{

        Duenio duenio = controladorDuenios.registrarDuenio("Cosme","Fulanito", dni,"30124585","223547710");

        Assertions.assertEquals("Cosme", duenio.getNombre());
        Assertions.assertEquals("Fulanito", duenio.getApellido());
        Assertions.assertEquals(dni, duenio.getTipoDocumento());
        Assertions.assertEquals("30124585", duenio.getNumeroDocumento());
        Assertions.assertEquals("223547710", duenio.getTelefono());
        Assertions.assertTrue( duenio.getIdDuenio() > 0);
        Assertions.assertTrue(duenio.getActivo());
    }

    @Test
    public void debeIndicarQueExisteDuenioConDocumentoRegistrado() throws SQLException {

          controladorDuenios.registrarDuenio("Cosme","Fulanito", dni,"1234","234");
          controladorDuenios.registrarDuenio("Cosme","Fulanito", dni,"5332","234");
          controladorDuenios.registrarDuenio("Cosme","Fulanito", dni,"12543","234");

          Assertions.assertTrue(controladorDuenios.existeDuenioConDocumento(dni,"5332"));

    }

    @Test
    public void debeIndicarQueNoExisteDuenioConDocumentoRegistrado() throws SQLException{

        controladorDuenios.registrarDuenio("Cosme","Fulanito", dni,"1234","234");
        controladorDuenios.registrarDuenio("Cosme","Fulanito", dni,"5332","234");
        controladorDuenios.registrarDuenio("Cosme","Fulanito", dni,"12543","234");

        Assertions.assertFalse(controladorDuenios.existeDuenioConDocumento(dni,"5412324574"));

    }

    @Test
    public void debeDevolverElDuenioSegunDocumentoIndicado() throws SQLException {

        controladorDuenios.registrarDuenio("Cosme","Fulanito", dni,"1234","234");
        Duenio duenio2 = controladorDuenios.registrarDuenio("Cosme","Fulanito", dni,"5332","234");
        controladorDuenios.registrarDuenio("Cosme","Fulanito", dni,"12543","234");

        Assertions.assertEquals(duenio2.getIdDuenio(),controladorDuenios.obtenerDuenioPorDocumento(dni,"5332").getIdDuenio());
    }

    @Test
    public void debeDevolverNuloAlNoEncontrarDuenioConDocumentoIndicado() throws SQLException{

        controladorDuenios.registrarDuenio("Cosme","Fulanito", dni,"1234","234");
        controladorDuenios.registrarDuenio("Cosme","Fulanito", dni,"5332","234");
        controladorDuenios.registrarDuenio("Cosme","Fulanito", dni,"12543","234");

        Assertions.assertNull(controladorDuenios.obtenerDuenioPorDocumento(dni,"999999"));

    }

    @Test
    public void alActualizarDatosDebePersistirNuevosDatos() throws SQLException{
        Duenio duenio = controladorDuenios.registrarDuenio("Cosme","Fulanito", dni,"1245782","234");

        duenio.setNombre("Lalo");
        duenio.setApellido("Landa");
        duenio.setTipoDocumento(dni);
        duenio.setNumeroDocumento("85244");
        duenio.setTelefono("12452");

        controladorDuenios.actualizarDuenio(duenio);

        Duenio duenioEncontrado = controladorDuenios.obtenerDuenioPorDocumento(dni,"85244");

        Assertions.assertEquals(duenio.getIdDuenio(), duenioEncontrado.getIdDuenio());
        Assertions.assertEquals(duenio.getNombre(),duenioEncontrado.getNombre());
        Assertions.assertEquals(duenio.getApellido(),duenioEncontrado.getApellido());
        Assertions.assertEquals(duenio.getTipoDocumento(),duenioEncontrado.getTipoDocumento());
        Assertions.assertEquals(duenio.getNumeroDocumento(),duenioEncontrado.getNumeroDocumento());
        Assertions.assertEquals(duenio.getTelefono(),duenioEncontrado.getTelefono());
    }

    @Test
    public void alRecuperarUnDuenioInactivoTieneQueEstarInactivo() throws SQLException {

        Duenio duenio = controladorDuenios.registrarDuenio("Cosme","Fulanito", dni,"1245782","234");

        this.baseDatosTestDAO.cambiarEstadoDuenio(duenio.getIdDuenio(),false);

        Duenio duenioBD = controladorDuenios.obtenerDuenioPorDocumento(duenio.getTipoDocumento(),duenio.getNumeroDocumento());

        Assertions.assertFalse(duenioBD.getActivo());
    }
}