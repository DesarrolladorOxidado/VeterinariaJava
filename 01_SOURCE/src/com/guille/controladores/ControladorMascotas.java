package com.guille.controladores;

import com.guille.modelos.Mascota;
import com.guille.modelos.TipoMascota;

import java.time.LocalDate;

public class ControladorMascotas {

    public ControladorMascotas(){

    }

    public Mascota crearMascota(String nombre, TipoMascota tipoMascota, String raza, LocalDate fechaNacimiento, double peso){

        Mascota mascota = new Mascota(nombre, tipoMascota);
        mascota.setRaza(raza);
        mascota.setFechaNacimiento(fechaNacimiento);
        mascota.setPeso(peso);

        return mascota;
    }
}
