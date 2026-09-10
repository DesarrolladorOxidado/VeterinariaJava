package com.guille.controladores;

import com.guille.BaseDatosTestDAO;
import com.guille.modelos.Duenio;
import com.guille.modelos.TipoDocumento;
import com.guille.persistencia.ConexionBD;
import com.guille.persistencia.dao.DuenioDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ControladorDueniosTest {

    private ControladorDuenios controladorDuenios;

    @BeforeEach
    public void setUp() throws SQLException {
        ConexionBD conexionBD = new ConexionBD(ConexionBD.Ambiente.TEST);
        BaseDatosTestDAO baseDatosTestDAO = new BaseDatosTestDAO(conexionBD);

        baseDatosTestDAO.borrarDatos();

        DuenioDAO duenioDAO = new DuenioDAO(conexionBD);
        controladorDuenios = new ControladorDuenios(duenioDAO);
    }

    @Test
    public void alRegistrarDuenioDebeTenerLosDatosIngresados() throws SQLException{

        Duenio duenio = controladorDuenios.registrarDuenio("Cosme","Fulanito",TipoDocumento.DNI,"30124585","223547710");

        Assertions.assertEquals("Cosme", duenio.getNombre());
        Assertions.assertEquals("Fulanito", duenio.getApellido());
        Assertions.assertEquals(TipoDocumento.DNI, duenio.getTipoDocumento());
        Assertions.assertEquals("30124585", duenio.getNumeroDocumento());
        Assertions.assertEquals("223547710", duenio.getTelefono());
        Assertions.assertTrue( duenio.getIdDuenio() > 0);
    }

    @Test
    public void debeIndicarQueExisteDuenioConDocumentoRegistrado() throws SQLException {

          controladorDuenios.registrarDuenio("Cosme","Fulanito",TipoDocumento.DNI,"1234","234");
          controladorDuenios.registrarDuenio("Cosme","Fulanito",TipoDocumento.DNI,"5332","234");
          controladorDuenios.registrarDuenio("Cosme","Fulanito",TipoDocumento.DNI,"12543","234");

          Assertions.assertTrue(controladorDuenios.existeDuenioConDocumento(TipoDocumento.DNI,"5332"));

    }

    @Test
    public void debeIndicarQueNoExisteDuenioConDocumentoRegistrado() throws SQLException{

        controladorDuenios.registrarDuenio("Cosme","Fulanito",TipoDocumento.DNI,"1234","234");
        controladorDuenios.registrarDuenio("Cosme","Fulanito",TipoDocumento.DNI,"5332","234");
        controladorDuenios.registrarDuenio("Cosme","Fulanito",TipoDocumento.DNI,"12543","234");

        Assertions.assertFalse(controladorDuenios.existeDuenioConDocumento(TipoDocumento.DNI,"5412324574"));

    }

    @Test
    public void debeDevolverElDuenioSegunDocumentoIndicado() throws SQLException {

        controladorDuenios.registrarDuenio("Cosme","Fulanito",TipoDocumento.DNI,"1234","234");
        Duenio duenio2 = controladorDuenios.registrarDuenio("Cosme","Fulanito",TipoDocumento.DNI,"5332","234");
        controladorDuenios.registrarDuenio("Cosme","Fulanito",TipoDocumento.DNI,"12543","234");

        Assertions.assertEquals(duenio2.getIdDuenio(),controladorDuenios.obtenerDuenioPorDocumento(TipoDocumento.DNI,"5332").getIdDuenio());
    }

    @Test
    public void debeDevolverNuloAlNoEncontrarDuenioConDocumentoIndicado() throws SQLException{

        controladorDuenios.registrarDuenio("Cosme","Fulanito",TipoDocumento.DNI,"1234","234");
        controladorDuenios.registrarDuenio("Cosme","Fulanito",TipoDocumento.DNI,"5332","234");
        controladorDuenios.registrarDuenio("Cosme","Fulanito",TipoDocumento.DNI,"12543","234");

        Assertions.assertNull(controladorDuenios.obtenerDuenioPorDocumento(TipoDocumento.DNI,"999999"));

    }
}