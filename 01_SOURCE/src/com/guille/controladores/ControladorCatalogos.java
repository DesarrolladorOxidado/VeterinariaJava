package com.guille.controladores;

import com.guille.modelos.TipoDocumento;
import com.guille.modelos.TipoMascota;
import com.guille.persistencia.dao.CatalogosDAO;

import java.sql.SQLException;
import java.util.List;

public class ControladorCatalogos {

    private final CatalogosDAO catalogosDAO;

    public ControladorCatalogos( CatalogosDAO catalogosDAO){
        this.catalogosDAO = catalogosDAO;
    }

    public List<TipoDocumento> obtenerTiposDocumentos() throws SQLException{
        return this.catalogosDAO.obtenerTiposDocumentos();
    }

    public List<TipoMascota> obtenerTiposMascotas() throws SQLException{
        return this.catalogosDAO.obtenerTiposMascotas();
    }
}
