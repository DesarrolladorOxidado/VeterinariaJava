package com.guille.modelos;

import java.time.LocalDateTime;

public class Duenio extends Persona {

    private int idDuenio;

    public Duenio(String nombre, String apellido, TipoDocumento tipoDocumento, String numeroDocumento, String telefono) {
        super(nombre, apellido, tipoDocumento, numeroDocumento, telefono);
    }

    public Duenio(int idDuenio, String nombre, String apellido, TipoDocumento tipoDocumento, String numeroDocumento, String telefono, LocalDateTime fechaAlta, boolean activo) {
        super(nombre, apellido, tipoDocumento, numeroDocumento, telefono, fechaAlta, activo);
        this.idDuenio = idDuenio;
    }

    public int getIdDuenio(){ return this.idDuenio; }

    @Override
    public String toString() {
        return "Duenio { idDuenio = " + this.idDuenio + ", " + super.toString() + "} ";
    }
}
