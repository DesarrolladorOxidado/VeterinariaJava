package com.guille.vistas.menu;

public enum OpcionesMenuEdicion implements OpcionesMenu{

    EDITAR_VETERINARIO("Editar veterinario"),
    EDITAR_DUENIO("Editar dueño"),
    EDITAR_MASCOTA("Editar mascota"),
    VOLVER("<- Volver");

    private final String descripcion;

    OpcionesMenuEdicion(String descripcion ){
        this.descripcion = descripcion;
    }

    @Override
    public String getDescripcion() {
        return this.descripcion;
    }
}
