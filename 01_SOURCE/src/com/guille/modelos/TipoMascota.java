package com.guille.modelos;

public class TipoMascota {
    private final String idTipoMascota;
    private final String descripcionTipoMascota;

    public TipoMascota(String idTipoMascota, String descripcionTipoMascota){
        this.idTipoMascota = idTipoMascota;
        this.descripcionTipoMascota = descripcionTipoMascota;
    }

    public String getIdTipoMascota(){ return this.idTipoMascota; }

    public String getDescripcionTipoMascota(){ return this.descripcionTipoMascota;}
}
