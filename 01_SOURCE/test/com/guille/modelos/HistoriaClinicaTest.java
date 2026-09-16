package com.guille.modelos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class HistoriaClinicaTest {

    private HistoriaClinica historiaClinica;

    @BeforeEach
    public void setUp(){
         historiaClinica = new HistoriaClinica(1);
    }

    @Test
    public void nuevaHistoriaClinicaDebeInicializarFechas(){

        Assertions.assertNotNull(historiaClinica.getFechaCreacion());
        Assertions.assertNotNull(historiaClinica.getFechaActualizacion());
        Assertions.assertEquals(historiaClinica.getFechaCreacion(),historiaClinica.getFechaActualizacion());

    }

    @Test
    public void nuevaHistoriaClinicaNoDebeTenerConsultas(){

        Assertions.assertTrue(historiaClinica.obtenerConsultas().isEmpty());
    }

}