package com.guille.controladores;

import com.guille.modelos.Mascota;
import com.guille.modelos.TipoMascota;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class ControladorMascotasTest {

    @Test
    public void alCrearMascotaDebeTenerLosDatosIngresados(){

        LocalDate fechaNacimiento = LocalDate.parse("13/07/2022", DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT));
        ControladorMascotas controladorMascotas = new ControladorMascotas();
        Mascota mascota = controladorMascotas.crearMascota("Mateo", TipoMascota.PERRO,"Border Collie", fechaNacimiento,24.3);

        Assertions.assertEquals("Mateo",mascota.getNombre());
        Assertions.assertEquals(TipoMascota.PERRO,mascota.getTipo());
        Assertions.assertEquals("Border Collie",mascota.getRaza());
        Assertions.assertEquals( fechaNacimiento,mascota.getFechaNacimiento());
        Assertions.assertEquals(24.3,mascota.getPeso());
    }
}