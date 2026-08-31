package com.guille.controladores;


import com.guille.modelos.Consulta;
import com.guille.modelos.Veterinario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ControladorConsultasTest {

    @Test
    public void alCrearConsultaDebeTenerLosDatosIngresados(){

        ControladorConsultas controladorConsultas = new ControladorConsultas();
        Veterinario veterinario = new Veterinario("Guille","Miño","123522","15555","52");

        Consulta consulta = controladorConsultas.crearConsulta("Control","Sin datos","Sin datos","Sin datos",veterinario);

        Assertions.assertEquals("Control",consulta.getMotivo());
        Assertions.assertEquals("Sin datos",consulta.getDiagnostico());
        Assertions.assertEquals("Sin datos",consulta.getTratamiento());
        Assertions.assertEquals( "Sin datos",consulta.getObservaciones());
        Assertions.assertSame(veterinario,consulta.getVeterinario());
    }
}