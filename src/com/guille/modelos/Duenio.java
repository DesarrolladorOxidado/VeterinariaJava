package com.guille.modelos;

import java.util.ArrayList;
import java.util.List;

public class Duenio extends Persona {

    private List<Mascota> mascotas;

    public Duenio(String nombre, String apellido, String numero_documento, String telefono) {
        super(nombre, apellido, numero_documento, telefono);

        this.mascotas = new ArrayList<>();
    }

    public void agregarMascota( Mascota mascota){
        this.mascotas.add(mascota);
    }


    public List<Mascota> obtenerMascotas(){
        return new ArrayList<>(this.mascotas);
    }

    public Mascota obtenerMascotaPorNombre(String nombre){
        for(Mascota mascota : mascotas){
            if (mascota.getNombre().equalsIgnoreCase(nombre))
                return mascota;
        }
        return null;
    }

    public List<Mascota> obtenerMascotasPorTipo(TipoMascota tipoMascota ){

        List<Mascota> resultado = new ArrayList<>();

        for(Mascota mascota : mascotas){
            if (mascota.getTipo() == tipoMascota ){
                resultado.add(mascota);
            }
        }

        return resultado;
    }

    public boolean tieneMascotas(){
        return !this.mascotas.isEmpty();
    }

    public int cantidadMascotas(){ return this.mascotas.size();}

    @Override
    public String toString() {
        return "Duenio { " + super.toString() + "} ";
    }
}
