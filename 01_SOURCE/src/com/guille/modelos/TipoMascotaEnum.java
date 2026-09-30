package com.guille.modelos;

public enum TipoMascotaEnum {
    PERRO("PE"),
    GATO("GT"),
    CONEJO("CJ"),
    HAMSTER("HM");

    private final String codigo;

    TipoMascotaEnum(String codigo){ this.codigo = codigo;}

    public String getCodigo(){ return this.codigo;}

    public String getNombre(){ return this.name().replace("_"," ");}

    public static TipoMascotaEnum obtenerCodigoTipoMascota(String codigoMascota){
        for( TipoMascotaEnum tipoMascota : TipoMascotaEnum.values()) {
            if (tipoMascota.getCodigo().equalsIgnoreCase(codigoMascota)) {
                return tipoMascota;

            }
        }
        return null;
    }

    public static TipoMascotaEnum obtenerNombreTipoMascota(String nombreTipoMascota){
        for( TipoMascotaEnum tipoMascota : TipoMascotaEnum.values()) {
            if (tipoMascota.name().equalsIgnoreCase(nombreTipoMascota)) {
                return tipoMascota;

            }
        }
        return null;
    }
}
