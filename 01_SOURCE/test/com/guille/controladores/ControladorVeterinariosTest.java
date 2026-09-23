package com.guille.controladores;

import com.guille.BaseDatosTestDAO;
import com.guille.modelos.TipoDocumento;
import com.guille.modelos.Veterinario;
import com.guille.persistencia.ConexionBD;
import com.guille.persistencia.dao.VeterinarioDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;


public class ControladorVeterinariosTest {

    private ControladorVeterinarios controladorVeterinarios;

    @BeforeEach
    void setUp() throws SQLException {
        ConexionBD conexionBD = new ConexionBD(ConexionBD.Ambiente.TEST);
        BaseDatosTestDAO baseDatosTestDAO = new BaseDatosTestDAO(conexionBD);

        baseDatosTestDAO.borrarDatos();

        VeterinarioDAO veterinarioDAO = new VeterinarioDAO(conexionBD);
        controladorVeterinarios = new ControladorVeterinarios(veterinarioDAO);
    }

    @Test
    public void alRegistrarVeterinarioDebeTenerLosDatosIngresados() throws SQLException{

        Veterinario veterinario = controladorVeterinarios.registrarVeterinario("Julius","Hibbert",TipoDocumento.DNI,"30124585","223547710","12");

        Assertions.assertTrue( veterinario.getIdVeterinario() > 0);
        Assertions.assertEquals("Julius", veterinario.getNombre());
        Assertions.assertEquals("Hibbert", veterinario.getApellido());
        Assertions.assertEquals(TipoDocumento.DNI, veterinario.getTipoDocumento());
        Assertions.assertEquals("30124585", veterinario.getNumeroDocumento());
        Assertions.assertEquals("223547710", veterinario.getTelefono());
        Assertions.assertEquals("12", veterinario.getMatricula());
    }

    @Test
    public void debeIndicarQueExisteVeterinarioConDocumentoRegistrado() throws SQLException{

        controladorVeterinarios.registrarVeterinario("Julius","Hibbert",TipoDocumento.DNI,"1234","234","12");
        controladorVeterinarios.registrarVeterinario("Nick","Riviera",TipoDocumento.DNI,"5332","234","99");
        controladorVeterinarios.registrarVeterinario("Marvin","Monroe",TipoDocumento.DNI,"12543","234","52");

        Assertions.assertTrue(controladorVeterinarios.existeVeterinarioConDocumento(TipoDocumento.DNI,"5332"));

    }

    @Test
    public void debeIndicarQueNoExisteVeterinarioConDocumentoRegistrado() throws SQLException{

        controladorVeterinarios.registrarVeterinario("Julius","Hibbert",TipoDocumento.DNI,"1234","234","12");
        controladorVeterinarios.registrarVeterinario("Nick","Riviera",TipoDocumento.DNI,"5332","234","99");
        controladorVeterinarios.registrarVeterinario("Marvin","Monroe",TipoDocumento.DNI,"12543","234","52");

        Assertions.assertFalse(controladorVeterinarios.existeVeterinarioConDocumento(TipoDocumento.DNI,"5412324574"));

    }

    @Test
    public void debeDevolverElVeterinarioSegunDocumentoIndicado() throws SQLException{

        controladorVeterinarios.registrarVeterinario("Julius","Hibbert",TipoDocumento.DNI,"1234","234","12");
        Veterinario veterinario = controladorVeterinarios.registrarVeterinario("Nick","Riviera",TipoDocumento.DNI,"5332","234","99");
        controladorVeterinarios.registrarVeterinario("Marvin","Monroe",TipoDocumento.DNI,"12543","234","52");

        Assertions.assertEquals(veterinario.getIdVeterinario(),controladorVeterinarios.obtenerVeterinarioConDocumento(TipoDocumento.DNI,"5332").getIdVeterinario());
    }

    @Test
    public void debeDevolverNuloAlNoEncontrarVeterinarioConDocumentoIndicado() throws SQLException{

        controladorVeterinarios.registrarVeterinario("Julius","Hibbert",TipoDocumento.DNI,"1234","234","12");
        controladorVeterinarios.registrarVeterinario("Nick","Riviera",TipoDocumento.DNI,"5332","234","99");
        controladorVeterinarios.registrarVeterinario("Marvin","Monroe",TipoDocumento.DNI,"12543","234","52");

        Assertions.assertNull(controladorVeterinarios.obtenerVeterinarioConDocumento(TipoDocumento.DNI,"999999"));

    }

    @Test
    public void debeDevolverElVeterinarioSegunMatriculaIndicada() throws SQLException{

        controladorVeterinarios.registrarVeterinario("Julius","Hibbert",TipoDocumento.DNI,"1234","234","12");
        Veterinario veterinario = controladorVeterinarios.registrarVeterinario("Nick","Riviera",TipoDocumento.DNI,"5332","234","99");
        controladorVeterinarios.registrarVeterinario("Marvin","Monroe",TipoDocumento.DNI,"12543","234","52");

        Assertions.assertEquals(veterinario.getIdVeterinario(),controladorVeterinarios.obtenerVeterinarioConMatricula("99").getIdVeterinario());
    }

    @Test
    public void debeDevolverNuloAlNoEncontrarVeterinarioConMatriculaIndicada() throws SQLException{

        controladorVeterinarios.registrarVeterinario("Julius","Hibbert",TipoDocumento.DNI,"1234","234","12");
        controladorVeterinarios.registrarVeterinario("Nick","Riviera",TipoDocumento.DNI,"5332","234","99");
        controladorVeterinarios.registrarVeterinario("Marvin","Monroe",TipoDocumento.DNI,"12543","234","52");

        Assertions.assertNull(controladorVeterinarios.obtenerVeterinarioConMatricula("999999"));

    }

    @Test
    public void alActualizarDatosDebePersistirNuevosDatos() throws SQLException{
        Veterinario veterinario = controladorVeterinarios.registrarVeterinario("Nick","Riviera",TipoDocumento.DNI,"5332","234","99");

        veterinario.setNombre("Lalo");
        veterinario.setApellido("Landa");
        veterinario.setTipoDocumento(TipoDocumento.DNI);
        veterinario.setNumeroDocumento("85244");
        veterinario.setTelefono("12452");
        veterinario.setMatricula("MV-512");

        controladorVeterinarios.actualizarVeterinario(veterinario);

        Veterinario veterinarioEncontrado = controladorVeterinarios.obtenerVeterinarioConMatricula("MV-512");

        Assertions.assertEquals(veterinario.getIdVeterinario(), veterinarioEncontrado.getIdVeterinario());
        Assertions.assertEquals(veterinario.getNombre(),veterinarioEncontrado.getNombre());
        Assertions.assertEquals(veterinario.getApellido(),veterinarioEncontrado.getApellido());
        Assertions.assertEquals(veterinario.getTipoDocumento(),veterinarioEncontrado.getTipoDocumento());
        Assertions.assertEquals(veterinario.getNumeroDocumento(),veterinarioEncontrado.getNumeroDocumento());
        Assertions.assertEquals(veterinario.getTelefono(),veterinarioEncontrado.getTelefono());
        Assertions.assertEquals(veterinario.getMatricula(),veterinarioEncontrado.getMatricula());
    }
}