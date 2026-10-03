package com.guille.modelos;

import java.time.LocalDateTime;

public class Veterinario extends Persona {

    private int idVeterinario;

    private String matricula;

    public Veterinario(String nombre, String apellido, TipoDocumento tipoDocumento, String numeroDocumento, String telefono, String matricula) {
        super(nombre, apellido,tipoDocumento, numeroDocumento, telefono);
        this.matricula = matricula;
    }

    public Veterinario(int idVeterinario, String nombre, String apellido, TipoDocumento tipoDocumento, String numeroDocumento, String telefono, LocalDateTime fechaAlta, String matricula, boolean activo) {
        super(nombre, apellido,tipoDocumento, numeroDocumento, telefono, fechaAlta, activo);
        this.idVeterinario = idVeterinario;
        this.matricula = matricula;
    }

    public int getIdVeterinario(){ return this.idVeterinario;}

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    @Override
    public String toString() {

        return "Veterinario { idVeterinario = " + this.idVeterinario + ", "
                + super.toString() +
                ", matricula='" + matricula + '\'' +
                '}';
    }
}
