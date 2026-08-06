package com.guille.controladores;

import com.guille.modelos.Mascota;
import com.guille.modelos.TipoMascota;

public class ControladorMascotas {

    public ControladorMascotas(){

    }

    public Mascota crearMascota(String nombre, String tipoMascota, String raza, int edad, double peso){

        Mascota mascota = new Mascota(nombre, TipoMascota.valueOf(tipoMascota));
        mascota.setRaza(raza);
        mascota.setEdad(edad);
        mascota.setPeso(peso);

        return mascota;
    }
}
