package com.guille.vistas.menu;

public enum OpcionesEditarDuenio implements OpcionesMenu{
    EDITAR_NOMBRE("Editar nombre"),
    EDITAR_APELLIDO("Editar apellido"),
    EDITAR_DOCUMENTO("Editar documento"),
    EDITAR_TELEFONO("Editar teléfono"),
    VOLVER("<- Volver");

    private final String descripcion;

    OpcionesEditarDuenio(String descripcion){
        this.descripcion = descripcion;
    }

    @Override
    public String getDescripcion() {
        return this.descripcion;
    }

}
