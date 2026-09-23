package com.guille.vistas.menu;

public enum OpcionesEditarVeterinario implements OpcionesMenu{
    EDITAR_NOMBRE("Editar nombre"),
    EDITAR_APELLIDO("Editar apellido"),
    EDITAR_DOCUMENTO("Editar documento"),
    EDITAR_TELEFONO("Editar teléfono"),
    EDITAR_MATRICULA("Editar matricula"),
    VOLVER("<- Volver");

    private final String descripcion;

    OpcionesEditarVeterinario(String descripcion){
        this.descripcion = descripcion;
    }

    @Override
    public String getDescripcion() {
        return this.descripcion;
    }
}
