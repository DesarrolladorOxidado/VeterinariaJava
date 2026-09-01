package com.guille.modelos;

public abstract class Persona {

    private String nombre;
    private String apellido;
    private String numeroDocumento;
    private String telefono;

    //El constructor solo lo necesitan las clases hijas, no cualquiera.
    //Por eso lo declaro como protected
    protected Persona(String nombre, String apellido, String numero_documento, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroDocumento = numero_documento;
        this.telefono = telefono;
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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", numero_documento='" + numeroDocumento + '\'' +
                ", telefono='" + telefono + "'";
    }
}
