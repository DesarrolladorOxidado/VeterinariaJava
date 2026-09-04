package com.guille.modelos;

public class Veterinario extends Persona {

    private int idVeterinario;
    private String matricula;

    public Veterinario( String nombre, String apellido,TipoDocumento tipoDocumento, String numero_documento, String telefono, String matricula) {
        super(nombre, apellido,tipoDocumento, numero_documento, telefono);
        this.matricula = matricula;
    }

    public Veterinario(int idVeterinario, String nombre, String apellido,TipoDocumento tipoDocumento, String numero_documento, String telefono, String matricula) {
        super(nombre, apellido,tipoDocumento, numero_documento, telefono);
        this.idVeterinario = idVeterinario;
        this.matricula = matricula;
    }

    public int getIdVeterinario(){ return this.idVeterinario;}

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
