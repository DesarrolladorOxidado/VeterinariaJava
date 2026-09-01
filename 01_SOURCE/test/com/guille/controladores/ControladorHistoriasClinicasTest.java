package com.guille.controladores;

import com.guille.modelos.Consulta;
import com.guille.modelos.Mascota;
import com.guille.modelos.TipoMascota;
import com.guille.modelos.Veterinario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class ControladorHistoriasClinicasTest {

    @Test
    public void debeAgregarUnaConsultaALaHistoriaClinicaDeLaMascota(){

        ControladorHistoriasClinicas controladorHistoriasClinicas = new ControladorHistoriasClinicas();
        LocalDate fechaNacimiento = LocalDate.parse("13/07/2022", DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT));
        Mascota mascota = new Mascota("Mateo", TipoMascota.PERRO);
        mascota.setRaza("Border Collie");
        mascota.setFechaNacimiento(fechaNacimiento);
        mascota.setPeso(24.3);

        Consulta consulta = new Consulta("Control",new Veterinario("Guille","Miño","12230","1452","12"));

        Assertions.assertTrue(controladorHistoriasClinicas.agregarConsultaHistoriaClinica(mascota,consulta));
        Assertions.assertSame(consulta,mascota.getHistoriaClinica().obtenerConsultas().get(0));
    }
}