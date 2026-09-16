package com.guille.controladores;

public class Controladores {

    private ControladorDuenios controladorDuenios;
    private ControladorVeterinarios controladorVeterinarios;
    private ControladorMascotas controladorMascotas;
    private ControladorHistoriasClinicas controladorHistoriasClinicas;
    private ControladorConsultas controladorConsultas;


    public void setControladorVeterinarios(ControladorVeterinarios controladorVeterinarios){
        this.controladorVeterinarios = controladorVeterinarios;
    }

    public ControladorVeterinarios getControladorVeterinarios(){
        return this.controladorVeterinarios;
    }

    public void setControladorDuenios(ControladorDuenios controladorDuenios){
        this.controladorDuenios = controladorDuenios;
    }

    public ControladorDuenios getControladorDuenios(){
        return this.controladorDuenios;
    }

    public void setControladorMascotas(ControladorMascotas controladorMascotas){
        this.controladorMascotas = controladorMascotas;
    }

    public ControladorMascotas getControladorMascotas(){ return this.controladorMascotas; }

    public void setControladorHistoriasClinicas(ControladorHistoriasClinicas controladorHistoriasClinicas){
        this.controladorHistoriasClinicas = controladorHistoriasClinicas;
    }

    public ControladorHistoriasClinicas getControladorHistoriasClinicas(){
        return this.controladorHistoriasClinicas;
    }

    public void setControladorConsultas(ControladorConsultas controladorConsultas){
        this.controladorConsultas = controladorConsultas;
    }

    public ControladorConsultas getControladorConsultas(){
        return this.controladorConsultas;
    }
}
