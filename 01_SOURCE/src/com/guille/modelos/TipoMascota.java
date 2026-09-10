package com.guille.modelos;

public enum TipoMascota {
    PERRO("PE"),
    GATO("GT"),
    CONEJO("CJ"),
    HAMSTER("HM");

    private String codigo;

    TipoMascota(String codigo){ this.codigo = codigo;}

    public String getCodigo(){ return this.codigo;}

    public static TipoMascota obtenerTipoMascota( String codigoMascota){
        for( TipoMascota tipoMascota : TipoMascota.values()) {
            if (tipoMascota.getCodigo().equals(codigoMascota)) {
                return tipoMascota;

            }
        }
        return null;
    }
}
