package com.guille.modelos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class DuenioTest {

    private Duenio duenio;

    @BeforeEach
    public void setUp(){
        duenio = new Duenio("Guille","Miño","2123123","23423423");
    }

    @Test
    public void debeAgregarMascotasAlDuenio() {

        Mascota mascota = new Mascota("Mateo",TipoMascota.PERRO);
        mascota.setFechaNacimiento(LocalDate.parse("13/07/2022", DateTimeFormatter.ofPattern("d/M/uuuu").withResolverStyle(ResolverStyle.STRICT)));
        mascota.setPeso(25.4);

        duenio.agregarMascota(mascota);

        Assertions.assertEquals(1, duenio.cantidadMascotas());
        Assertions.assertSame(mascota, duenio.obtenerMascotas().get(0));

    }

    @Test
    public void nuevoDuenioNoTieneMascotas(){

        Assertions.assertFalse(duenio.tieneMascotas());

    }
}