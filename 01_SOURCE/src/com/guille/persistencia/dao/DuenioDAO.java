package com.guille.persistencia.dao;

import com.guille.modelos.Duenio;
import com.guille.modelos.TipoDocumento;
import com.guille.persistencia.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DuenioDAO {

    public List<Duenio> obtenerDuenios() throws SQLException {

        List<Duenio> duenios = new ArrayList<>();
        ConexionBD conexionBD = new ConexionBD();
        try (Connection connection = conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM duenios"); ResultSet resultado = statement.executeQuery()){

            while ( resultado.next()){
                int id = resultado.getInt("id_duenio");
                String nombre = resultado.getString("nombre_duenio");
                String apellido = resultado.getString("apellido_duenio");
                String tipoDocumentoST = resultado.getString("tipo_documento_duenio");
                String numeroDocumento = resultado.getString("numero_documento_duenio");
                String telefono = resultado.getString("telefono_duenio");

                TipoDocumento tipo = TipoDocumento.obtenerTipoDocumento(tipoDocumentoST);

                Duenio duenio = new Duenio(id,nombre,apellido,tipo,numeroDocumento,telefono);

                duenios.add(duenio);
            }
        }

        return duenios;
    }

    public Duenio obtenerDuenioPorDocumento(TipoDocumento tipoDocumento, String numeroDocumento) throws SQLException{

        ConexionBD conexionBD = new ConexionBD();
        Duenio duenio = null;

        try( Connection connection = conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM duenios WHERE tipo_documento_duenio = ? AND  numero_documento_duenio = ?" )){

            statement.setString(1, tipoDocumento.getCodigo());
            statement.setString(2,numeroDocumento);

            try( ResultSet resultado = statement.executeQuery()){
                if ( resultado.next()){
                    int id = resultado.getInt("id_duenio");
                    String nombre = resultado.getString("nombre_duenio");
                    String apellido = resultado.getString("apellido_duenio");
                    String telefono = resultado.getString("telefono_duenio");

                    duenio = new Duenio(id,nombre,apellido,tipoDocumento,numeroDocumento,telefono);

                }
            }

        }

        return duenio;
    }

    public Duenio registrarDuenio(Duenio duenio) throws SQLException{

        Duenio duenioBD = null;

        String sql = "INSERT INTO duenios(" +
                "nombre_duenio," +
                "apellido_duenio," +
                "tipo_documento_duenio," +
                "numero_documento_duenio," +
                "telefono_duenio)" +
                "VALUES(?,?,?,?,?) " +
                "RETURNING id_duenio";

        ConexionBD conexionBD = new ConexionBD();

        try (Connection connection  = conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,duenio.getNombre());
            statement.setString(2,duenio.getApellido());
            statement.setString(3,duenio.getTipoDocumento().getCodigo());
            statement.setString(4,duenio.getNumeroDocumento());
            statement.setString(5,duenio.getTelefono());

            try( ResultSet resultado = statement.executeQuery()){
                if ( resultado.next()){
                    int id = resultado.getInt("id_duenio");
                    duenioBD = new Duenio(id,duenio.getNombre(),duenio.getApellido(),duenio.getTipoDocumento(),duenio.getNumeroDocumento(),duenio.getTelefono());
                }
            }
        }

        return duenioBD;
    }
}
