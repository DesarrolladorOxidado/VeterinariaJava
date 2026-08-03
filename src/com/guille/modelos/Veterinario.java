package com.guille.modelos;

public class Veterinario extends Persona {

    private String matricula;

    public Veterinario(String nombre, String apellido, String numero_documento, String telefono, String matricula) {
        super(nombre, apellido, numero_documento, telefono);

        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    @Override
    public String toString() {

        return "Veterinario { " + super.toString() +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
