package com.guille.persistencia.dao;

import com.guille.modelos.TipoDocumento;
import com.guille.modelos.TipoMascota;
import com.guille.persistencia.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogosDAO extends Dao {

    public CatalogosDAO(ConexionBD conexionBD){
        super(conexionBD);
    }

    public List<TipoDocumento> obtenerTiposDocumentos() throws SQLException {
        List<TipoDocumento> tiposDocumentos = new ArrayList<>();

        String sql = "SELECT id_tipo_documento, descripcion_documento FROM tipos_documentos";

        try(Connection connection = this.conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()){
                String idTipoDocumento = resultSet.getString("id_tipo_documento");
                String descripcionDocumento = resultSet.getString("descripcion_documento");

                TipoDocumento tipoDocumento = new TipoDocumento(idTipoDocumento,descripcionDocumento);

                tiposDocumentos.add(tipoDocumento);
            }

            return tiposDocumentos;
        }

    }

    public List<TipoMascota> obtenerTiposMascotas() throws SQLException {
        List<TipoMascota> tiposMascotas = new ArrayList<>();

        String sql = "SELECT id_tipo_mascota, descripcion_tipo_mascota FROM tipos_mascotas";

        try(Connection connection = this.conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()){
                String idTipoMascota = resultSet.getString("id_tipo_mascota");
                String descripcionTipoMascota = resultSet.getString("descripcion_tipo_mascota");

                TipoMascota tipoMascota = new TipoMascota(idTipoMascota,descripcionTipoMascota);

                tiposMascotas.add(tipoMascota);
            }

            return tiposMascotas;
        }

    }
}
