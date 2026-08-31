package com.guille.controladores;

import com.guille.modelos.Veterinario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ControladorVeterinariosTest {

    private ControladorVeterinarios controladorVeterinarios;

    @BeforeEach
    void setUp() {
        controladorVeterinarios = new ControladorVeterinarios();
    }

    @Test
    public void alCrearControladorVeterinariosNoDebeTenerVeterinarios(){
        Assertions.assertFalse(controladorVeterinarios.existenVeterinarios());
    }

    @Test
    public void alRegistrarVeterinarioDebeAgregarseALaListaDeVeterinarios(){

        Veterinario veterinario = controladorVeterinarios.registrarVeterinario("Guille","Miño","30124585","223547710","12");

        Assertions.assertTrue(controladorVeterinarios.existenVeterinarios());
        Assertions.assertSame(veterinario,controladorVeterinarios.obtenerVeterinarioPorIndice(0));
    }

    @Test
    public void alRegistrarVeterinarioDebeTenerLosDatosIngresados(){

        Veterinario veterinario = controladorVeterinarios.registrarVeterinario("Guille","Miño","30124585","223547710","12");

        Assertions.assertEquals("Guille", veterinario.getNombre());
        Assertions.assertEquals("Miño", veterinario.getApellido());
        Assertions.assertEquals("30124585", veterinario.getNumeroDocumento());
        Assertions.assertEquals("223547710", veterinario.getTelefono());
        Assertions.assertEquals("12", veterinario.getMatricula());
    }

    @Test
    public void debeIndicarQueExisteVeterinarioConDocumentoRegistrado(){

        controladorVeterinarios.registrarVeterinario("Guille","Miño","1234","234","12");
        controladorVeterinarios.registrarVeterinario("Mateo","Miño","5332","234","99");
        controladorVeterinarios.registrarVeterinario("Ruben","Miño","12543","234","52");

        Assertions.assertTrue(controladorVeterinarios.existeVeterinarioConDocumento("5332"));

    }

    @Test
    public void debeIndicarQueNoExisteVeterinarioConDocumentoRegistrado(){

        controladorVeterinarios.registrarVeterinario("Guille","Miño","1234","234","12");
        controladorVeterinarios.registrarVeterinario("Mateo","Miño","5332","234","99");
        controladorVeterinarios.registrarVeterinario("Ruben","Miño","12543","234","52");

        Assertions.assertFalse(controladorVeterinarios.existeVeterinarioConDocumento("5412324574"));

    }

    @Test
    public void alRegistrarVeterinarioDebeActualizarLaCantidadDeVeterinariosRegistrados(){

        controladorVeterinarios.registrarVeterinario("Guille","Miño","1234","234","12");
        controladorVeterinarios.registrarVeterinario("Mateo","Miño","5332","234","99");
        controladorVeterinarios.registrarVeterinario("Ruben","Miño","12543","234","52");

        Assertions.assertEquals(3, controladorVeterinarios.cantidadVeterinarios());
    }

    @Test
    public void debeDevolverElVeterinarioSegunIndiceIndicado(){

        Veterinario veterinario  = controladorVeterinarios.registrarVeterinario("Guille","Miño","1234","234","12");
        Veterinario veterinario2 = controladorVeterinarios.registrarVeterinario("Mateo","Miño","5332","234","99");
        Veterinario veterinario3 = controladorVeterinarios.registrarVeterinario("Ruben","Miño","12543","234","52");

        Assertions.assertSame(veterinario, controladorVeterinarios.obtenerVeterinarioPorIndice(0));
        Assertions.assertSame(veterinario2, controladorVeterinarios.obtenerVeterinarioPorIndice(1));
        Assertions.assertSame(veterinario3, controladorVeterinarios.obtenerVeterinarioPorIndice(2));
    }

    @Test
    public void debeDevolverElVeterinarioSegunDocumentoIndicado(){

        Veterinario veterinario  = controladorVeterinarios.registrarVeterinario("Guille","Miño","1234","234","12");
        Veterinario veterinario2 = controladorVeterinarios.registrarVeterinario("Mateo","Miño","5332","234","99");
        Veterinario veterinario3 = controladorVeterinarios.registrarVeterinario("Ruben","Miño","12543","234","52");

        Assertions.assertSame(veterinario2,controladorVeterinarios.obtenerVeterinarioConDocumento("5332"));
    }

    @Test
    public void debeDevolverNuloAlNoEncontrarVeterinarioConDocumentoIndicado(){

        Veterinario veterinario  = controladorVeterinarios.registrarVeterinario("Guille","Miño","1234","234","12");
        Veterinario veterinario2 = controladorVeterinarios.registrarVeterinario("Mateo","Miño","5332","234","99");
        Veterinario veterinario3 = controladorVeterinarios.registrarVeterinario("Ruben","Miño","12543","234","52");

        Assertions.assertNull(controladorVeterinarios.obtenerVeterinarioConDocumento("999999"));

    }

    @Test
    public void debeDevolverElVeterinarioSegunMatriculaIndicada(){

        Veterinario veterinario  = controladorVeterinarios.registrarVeterinario("Guille","Miño","1234","234","12");
        Veterinario veterinario2 = controladorVeterinarios.registrarVeterinario("Mateo","Miño","5332","234","99");
        Veterinario veterinario3 = controladorVeterinarios.registrarVeterinario("Ruben","Miño","12543","234","52");

        Assertions.assertSame(veterinario2,controladorVeterinarios.obtenerVeterinarioConMatricula("99"));
    }

    @Test
    public void debeDevolverNuloAlNoEncontrarVeterinarioConMatriculaIndicada(){

        Veterinario veterinario  = controladorVeterinarios.registrarVeterinario("Guille","Miño","1234","234","12");
        Veterinario veterinario2 = controladorVeterinarios.registrarVeterinario("Mateo","Miño","5332","234","99");
        Veterinario veterinario3 = controladorVeterinarios.registrarVeterinario("Ruben","Miño","12543","234","52");

        Assertions.assertNull(controladorVeterinarios.obtenerVeterinarioConMatricula("999999"));

    }

}