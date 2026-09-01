package com.guille.controladores;

import com.guille.modelos.Duenio;
import com.guille.modelos.Mascota;

import java.util.ArrayList;
import java.util.List;

public class ControladorDuenios {

    private List<Duenio> duenios;

    public ControladorDuenios(){
        this.duenios = new ArrayList<>();
        //cargarDueniosPrueba();
    }

    private void cargarDueniosPrueba() {

        this.duenios.add(new Duenio(
                "Juan",
                "Pérez",
                "30111222",
                "2234567890"));

        this.duenios.add(new Duenio(
                "María",
                "Gómez",
                "27888999",
                "2234112233"));

        this.duenios.add(new Duenio(
                "Carlos",
                "Rodríguez",
                "32444555",
                "2234998877"));

        this.duenios.add(new Duenio(
                "Laura",
                "Fernández",
                "35666777",
                "2234556677"));
    }

    private Duenio crearDuenio(String nombre, String apellido, String numeroDocumento, String telefono){
        return new Duenio(nombre,apellido,numeroDocumento,telefono);
    }

    public Duenio registrarDuenio(String nombre, String apellido, String numeroDocumento, String telefono){
        Duenio resultado = crearDuenio(nombre,apellido,numeroDocumento,telefono);
        this.duenios.add(resultado);
        return resultado;
    }

    public boolean existeDuenioConDocumento(String documento){
        return obtenerDuenioPorDocumento(documento) != null;
    }

    public int cantidadDuenios(){
        return this.duenios.size();
    }

    public boolean existenDuenios(){
        return !this.duenios.isEmpty();
    }

    public Duenio obtenerDuenioPorIndice(int indice){
        return this.duenios.get(indice);
    }

    public Duenio obtenerDuenioPorDocumento(String documento){
        for ( Duenio duenio : this.duenios)
            if ( duenio.getNumeroDocumento().equals(documento))
                return duenio;
        return null;
    }

    public void agregarMascota(Duenio duenio, Mascota mascota){
            duenio.agregarMascota(mascota);
    }
}
