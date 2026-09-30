package com.guille.vistas.menu;

public enum OpcionesEditarMascota implements OpcionesMenu {
    EDITAR_NOMBRE("Editar nombre"),
    EDITAR_TIPO_MASCOTA("Editar tipo mascota"),
    EDITAR_RAZA("Editar raza"),
    EDITAR_FECHA_NACIMIENTO("Editar fecha nacimiento"),
    EDITAR_PESO("Editar peso"),
    VOLVER("<- Volver");

    private final String descripcion;

    OpcionesEditarMascota( String descripcion ){
        this.descripcion = descripcion;
    }

    @Override
    public String getDescripcion() {
        return this.descripcion;
    }
}
