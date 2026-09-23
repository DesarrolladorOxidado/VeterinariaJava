package com.guille.vistas.menu;

public enum OpcionMenuPrincipal implements OpcionesMenu{
    REGISTRAR_VETERINARIO("Registrar veterinario"),
    REGISTRAR_DUENIO("Registrar dueño"),
    REGISTRAR_MASCOTA("Registrar mascota"),
    MOSTRAR_VETERINARIOS("Mostrar veterinarios"),
    MOSTRAR_DUENIOS("Mostrar dueños"),
    MOSTRAR_MASCOTAS("Mostrar mascotas de un dueño"),
    NUEVA_CONSULTA("Nueva consulta"),
    CONSULTAR_HISTORIA_CLINICA("Consultar historia clínica"),
    EDITAR("Editar datos"),
    SALIR("Salir");

    private final String descripcion;

    OpcionMenuPrincipal(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String getDescripcion() {
        return this.descripcion;
    }
}
