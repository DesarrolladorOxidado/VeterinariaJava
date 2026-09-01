package com.guille.modelos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class HistoriaClinicaTest {

    private HistoriaClinica historiaClinica;

    @BeforeEach
    public void setUp(){
         historiaClinica = new HistoriaClinica();
    }

    @Test
    public void nuevaHistoriaClinicaDebeInicializarFechas(){

        Assertions.assertNotNull(historiaClinica.getFechaCreacion());
        Assertions.assertNotNull(historiaClinica.getFechaActualizacion());
        Assertions.assertEquals(historiaClinica.getFechaCreacion(),historiaClinica.getFechaActualizacion());

    }

    @Test
    public void debeRegistrarConsulta(){

        Consulta consulta = new Consulta("Control rutinario",null);

        historiaClinica.registrarConsulta(consulta);

        Assertions.assertEquals(1, historiaClinica.obtenerConsultas().size());
        Assertions.assertSame(consulta,historiaClinica.obtenerConsultas().get(0));

    }

    @Test
    public void nuevaHistoriaClinicaNoDebeTenerConsultas(){

        Assertions.assertTrue(historiaClinica.obtenerConsultas().isEmpty());
    }

    @Test
    public void debeActualizarFechaHistoriaClinicaAlRegistrarNuevaConsulta(){

        Consulta consulta = new Consulta("Control",null);

        historiaClinica.registrarConsulta(consulta);

        Assertions.assertEquals(consulta.getFecha(),historiaClinica.getFechaActualizacion());
    }
}