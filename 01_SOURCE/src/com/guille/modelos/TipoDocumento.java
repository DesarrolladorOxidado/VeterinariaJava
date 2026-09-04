package com.guille.modelos;

public enum TipoDocumento {
    DNI("DNI"),
    PASAPORTE("PAS"),
    LIBRETA_ENROLAMIENTO("LE"),
    LIBRETA_CIVICA("LC");

    private String codigo;

    TipoDocumento(String codigo){
        this.codigo = codigo;
    }

    public String getCodigo(){ return this.codigo;}

    public static TipoDocumento obtenerTipoDocumento(String codigoDocumento){
        for( TipoDocumento tipoDocumento : TipoDocumento.values()) {
            if (tipoDocumento.getCodigo().equals(codigoDocumento)) {
                return tipoDocumento;

            }
        }
        return null;
    }
}