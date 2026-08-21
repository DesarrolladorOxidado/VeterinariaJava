package com.guille.modelos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

import static org.junit.jupiter.api.Assertions.*;

public class MascotaTest {

    private Mascota mascota;
    private LocalDate fechaNacimiento;

    @BeforeEach
    public void setUp(){
        mascota = new Mascota("Maestro Ruben", TipoMascota.GATO);
        mascota.setRaza("Persa");
        fechaNacimiento = LocalDate.parse("13/07/2022", DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT));
        mascota.setFechaNacimiento(fechaNacimiento);
        mascota.setPeso(5.2);
    }

    @Test
    public void debeCalcularEdadAPartirDeFechaNacimiento(){

        Assertions.assertEquals(Period.between(fechaNacimiento,LocalDate.now()).getYears() , mascota.getEdad());
    }

    @Test
    public void alCrearMascotaSuHistoriaClinicaNoDebeTenerConsultas(){

        Assertions.assertNotNull(mascota.getHistoriaClinica());
        Assertions.assertTrue(mascota.getHistoriaClinica().obtenerConsultas().isEmpty());

    }
}