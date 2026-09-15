package com.guille.modelos;

import java.time.LocalDateTime;

public class Duenio extends Persona {

    private int idDuenio;

    public Duenio(String nombre, String apellido,TipoDocumento tipoDocumento, String numero_documento, String telefono) {
        super(nombre, apellido, tipoDocumento, numero_documento, telefono);
    }

    public Duenio(int idDuenio, String nombre, String apellido, TipoDocumento tipoDocumento, String numero_documento, String telefono, LocalDateTime fechaAlta) {
        super(nombre, apellido, tipoDocumento, numero_documento, telefono, fechaAlta);
        this.idDuenio = idDuenio;
    }

    public int getIdDuenio(){ return this.idDuenio; }

    @Override
    public String toString() {
        return "Duenio { idDuenio = " + this.idDuenio + ", " + super.toString() + "} ";
    }
}
