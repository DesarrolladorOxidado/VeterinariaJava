package com.guille.modelos;

public class TipoDocumento {

    private String idTipoDocumento;
    private String descripcionDocumento;

    public TipoDocumento(String idTipoDocumento, String descripcionDocumento){
        this.idTipoDocumento = idTipoDocumento;
        this.descripcionDocumento = descripcionDocumento;
    }

    public String getIdTipoDocumento(){ return this.idTipoDocumento; }

    public String getDescripcionDocumento(){ return this.descripcionDocumento;}

}
