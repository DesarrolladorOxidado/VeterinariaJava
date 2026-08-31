package com.guille.modelos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class ConsultaTest {

    private Consulta consulta;
    private Veterinario veterinario;

    @BeforeEach
    public void setUp() {
        veterinario = new Veterinario("Cosme","Fulanito","12121","212312","MPV-3232");
        consulta = new Consulta("Control",veterinario);
    }

    @Test
    public void nuevaConsultaDebeTenerVeterinarioAsignado(){

        Assertions.assertSame(veterinario,consulta.getVeterinario());

    }

    @Test
    public void nuevaConsultaDebeTenerFechaDeCreacionActual(){

        Duration diferencia = Duration.between(consulta.getFecha(),LocalDateTime.now());

        Assertions.assertTrue(diferencia.toMillis()<1000);
    }
    
    @Test
    public void debeAsignarValorPorDefectoEnCamposVacios(){

        consulta.setDiagnostico("Cambio el comportamiento por defecto");
        consulta.setTratamiento("Cambio el comportamiento por defecto");
        consulta.setObservaciones("Cambio el comportamiento por defecto");

        consulta.setDiagnostico("");
        consulta.setTratamiento("");
        consulta.setObservaciones("");

        Assertions.assertEquals("Sin diagnóstico", consulta.getDiagnostico());
        Assertions.assertEquals("Sin tratamiento", consulta.getTratamiento());
        Assertions.assertEquals("Sin observaciones", consulta.getObservaciones());
    }
}