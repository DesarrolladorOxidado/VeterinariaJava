package com.guille.controladores;

import com.guille.modelos.Duenio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ControladorDueniosTest {

    private ControladorDuenios controladorDuenios;

    @BeforeEach
    public void setUp() {
        controladorDuenios = new ControladorDuenios();
    }

    @Test
    public void alCrearControladorDueniosNoDebeTenerDuenios(){
        Assertions.assertFalse(controladorDuenios.existenDuenios());
    }

    @Test
    public void alRegistrarDuenioDebeAgregarseALaListaDeDuenios(){

        Duenio duenio = controladorDuenios.registrarDuenio("Guille","Miño","30124585","223547710");

        Assertions.assertTrue(controladorDuenios.existenDuenios());
        Assertions.assertSame(duenio,controladorDuenios.obtenerDuenioPorIndice(0));
    }

    @Test
    public void alRegistrarDuenioDebeTenerLosDatosIngresados(){

        Duenio duenio = controladorDuenios.registrarDuenio("Guille","Miño","30124585","223547710");

        Assertions.assertEquals("Guille", duenio.getNombre());
        Assertions.assertEquals("Miño", duenio.getApellido());
        Assertions.assertEquals("30124585", duenio.getNumeroDocumento());
        Assertions.assertEquals("223547710", duenio.getTelefono());
    }

    @Test
    public void debeIndicarQueExisteDuenioConDocumentoRegistrado(){

          controladorDuenios.registrarDuenio("Guille","Miño","1234","234");
          controladorDuenios.registrarDuenio("Mateo","Miño","5332","234");
          controladorDuenios.registrarDuenio("Ruben","Miño","12543","234");

          Assertions.assertTrue(controladorDuenios.existeDuenioConDocumento("5332"));

    }

    @Test
    public void debeIndicarQueNoExisteDuenioConDocumentoRegistrado(){

        controladorDuenios.registrarDuenio("Guille","Miño","1234","234");
        controladorDuenios.registrarDuenio("Mateo","Miño","5332","234");
        controladorDuenios.registrarDuenio("Ruben","Miño","12543","234");

        Assertions.assertFalse(controladorDuenios.existeDuenioConDocumento("5412324574"));

    }

    @Test
    public void alRegistrarDueniosDebeActualizarLaCantidadDeDueniosRegistrados(){

        controladorDuenios.registrarDuenio("Guille","Miño","1234","234");
        controladorDuenios.registrarDuenio("Mateo","Miño","5332","234");
        controladorDuenios.registrarDuenio("Ruben","Miño","12543","234");

        Assertions.assertEquals(3, controladorDuenios.cantidadDuenios());
    }

    @Test
    public void debeDevolverElDuenioSegunIndiceIndicado(){

        Duenio duenio  = controladorDuenios.registrarDuenio("Guille","Miño","1234","234");
        Duenio duenio2 = controladorDuenios.registrarDuenio("Mateo","Miño","5332","234");
        Duenio duenio3 = controladorDuenios.registrarDuenio("Ruben","Miño","12543","234");

        Assertions.assertSame(duenio, controladorDuenios.obtenerDuenioPorIndice(0));
        Assertions.assertSame(duenio2, controladorDuenios.obtenerDuenioPorIndice(1));
        Assertions.assertSame(duenio3, controladorDuenios.obtenerDuenioPorIndice(2));
    }

    @Test
    public void debeDevolverElDuenioSegunDocumentoIndicado(){

        Duenio duenio  = controladorDuenios.registrarDuenio("Guille","Miño","1234","234");
        Duenio duenio2 = controladorDuenios.registrarDuenio("Mateo","Miño","5332","234");
        Duenio duenio3 = controladorDuenios.registrarDuenio("Ruben","Miño","12543","234");

        Assertions.assertSame(duenio2,controladorDuenios.obtenerDuenioPorDocumento("5332"));
    }

    @Test
    public void debeDevolverNuloAlNoEncontrarDuenioConDocumentoIndicado(){

        Duenio duenio  = controladorDuenios.registrarDuenio("Guille","Miño","1234","234");
        Duenio duenio2 = controladorDuenios.registrarDuenio("Mateo","Miño","5332","234");
        Duenio duenio3 = controladorDuenios.registrarDuenio("Ruben","Miño","12543","234");

        Assertions.assertNull(controladorDuenios.obtenerDuenioPorDocumento("999999"));

    }
}