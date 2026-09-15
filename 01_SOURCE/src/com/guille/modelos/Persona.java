package com.guille.modelos;

import java.time.LocalDateTime;

public abstract class Persona {

    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private String telefono;

    private final LocalDateTime fechaAlta;

    //El constructor solo lo necesitan las clases hijas, no cualquiera.
    //Por eso lo declaro como protected
    protected Persona(String nombre, String apellido, TipoDocumento tipoDocumento,String numero_documento, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numero_documento;
        this.telefono = telefono;
        this.fechaAlta = LocalDateTime.now();
    }

    protected Persona(String nombre, String apellido, TipoDocumento tipoDocumento, String numeroDocumento, String telefono, LocalDateTime fechaAlta) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.telefono = telefono;
        this.fechaAlta = fechaAlta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public TipoDocumento getTipoDocumento(){ return this.tipoDocumento; }

    public void setTipoDocumento(TipoDocumento tipoDocumento ){ this.tipoDocumento = tipoDocumento;}

    public void setNumeroDocumento(String numeroDocumento){
        this.numeroDocumento = numeroDocumento;
    }
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDateTime getFechaAlta(){ return this.fechaAlta; }

    @Override
    public String toString() {
        return
                "nombre ='" + nombre + '\'' +
                ", apellido = '" + apellido + '\'' +
                ", tipo_documento = " + tipoDocumento.toString() + '\'' +
                ", numero_documento = '" + numeroDocumento + '\'' +
                ", telefono = '" + telefono + '\'' +
                ", fechaAlta = '" + fechaAlta + "'";
    }
}
