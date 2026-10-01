package com.guille.persistencia.dao;

import com.guille.modelos.Duenio;
import com.guille.modelos.TipoDocumento;
import com.guille.persistencia.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DuenioDAO extends Dao {

    public DuenioDAO(ConexionBD conexionBD){
        super(conexionBD);
    }

    public List<Duenio> obtenerDueniosActivos() throws SQLException {

        List<Duenio> duenios = new ArrayList<>();

        String sql = "SELECT D.*, TD.descripcion_documento FROM duenios D INNER JOIN tipos_documentos TD ON D.tipo_documento_duenio = TD.id_tipo_documento WHERE D.activo_duenio = true";

        try (Connection connection = this.conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql); ResultSet resultado = statement.executeQuery()){

            while ( resultado.next()){
                int id = resultado.getInt("id_duenio");
                String nombre = resultado.getString("nombre_duenio");
                String apellido = resultado.getString("apellido_duenio");
                String tipoDocumentoST = resultado.getString("tipo_documento_duenio");
                String descripcionTipoDocumentoST = resultado.getString("descripcion_documento");
                String numeroDocumento = resultado.getString("numero_documento_duenio");
                String telefono = resultado.getString("telefono_duenio");
                LocalDateTime fechaAlta = resultado.getObject("fecha_alta_duenio", LocalDateTime.class);
                boolean activo = resultado.getBoolean("activo_duenio");

                TipoDocumento tipoDocumento = new TipoDocumento(tipoDocumentoST,descripcionTipoDocumentoST);

                Duenio duenio = new Duenio(id,nombre,apellido,tipoDocumento,numeroDocumento,telefono,fechaAlta,activo);

                duenios.add(duenio);
            }
        }

        return duenios;
    }

    public Duenio obtenerDuenioPorDocumento(TipoDocumento tipoDocumento, String numeroDocumento) throws SQLException{

        Duenio duenio = null;

        try( Connection connection = this.conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM duenios WHERE tipo_documento_duenio = ? AND  numero_documento_duenio = ?" )){

            statement.setString(1, tipoDocumento.getIdTipoDocumento());
            statement.setString(2,numeroDocumento);

            try( ResultSet resultado = statement.executeQuery()){
                if ( resultado.next()){
                    int id = resultado.getInt("id_duenio");
                    String nombre = resultado.getString("nombre_duenio");
                    String apellido = resultado.getString("apellido_duenio");
                    String telefono = resultado.getString("telefono_duenio");
                    LocalDateTime fechaAlta = resultado.getObject("fecha_alta_duenio", LocalDateTime.class);
                    boolean activo = resultado.getBoolean("activo_duenio");

                    duenio = new Duenio(id,nombre,apellido,tipoDocumento,numeroDocumento,telefono,fechaAlta,activo);

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
                "telefono_duenio," +
                "fecha_alta_duenio)" +
                "VALUES(?,?,?,?,?,?) " +
                "RETURNING id_duenio, activo_duenio";

        try (Connection connection  = this.conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,duenio.getNombre());
            statement.setString(2,duenio.getApellido());
            statement.setString(3,duenio.getTipoDocumento().getIdTipoDocumento());
            statement.setString(4,duenio.getNumeroDocumento());
            statement.setString(5,duenio.getTelefono());
            statement.setObject(6,duenio.getFechaAlta());

            try( ResultSet resultado = statement.executeQuery()){
                if ( resultado.next()){
                    int id = resultado.getInt("id_duenio");
                    boolean activo = resultado.getBoolean("activo_duenio");

                    duenioBD = new Duenio(id,duenio.getNombre(),duenio.getApellido(),duenio.getTipoDocumento(),duenio.getNumeroDocumento(),duenio.getTelefono(),duenio.getFechaAlta(),activo);
                }
            }
        }

        return duenioBD;
    }

    public void actualizarDuenio(Duenio duenio) throws SQLException{
        String sql = "UPDATE duenios " +
                "SET nombre_duenio = ?, " +
                "apellido_duenio = ?, " +
                "tipo_documento_duenio = ?, " +
                "numero_documento_duenio = ?, " +
                "telefono_duenio = ? " +
                "WHERE id_duenio = ?";

        try(Connection connection = conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,duenio.getNombre());
            statement.setString(2,duenio.getApellido());
            statement.setString(3,duenio.getTipoDocumento().getIdTipoDocumento());
            statement.setString(4,duenio.getNumeroDocumento());
            statement.setString(5,duenio.getTelefono());
            statement.setInt(6,duenio.getIdDuenio());

            int resultado = statement.executeUpdate();

            if ( resultado != 1 )
                throw new SQLException("No se pudo actualizar el dueño");
        }
    }

    public boolean tieneMascotas(int idDuenio) throws SQLException{

        String sql = "SELECT EXISTS(SELECT 1 FROM mascotas WHERE id_duenio_mascota = ?)";

        try( Connection connection = conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,idDuenio);

            try( ResultSet resultSet = statement.executeQuery()){
                if ( resultSet.next())
                    return resultSet.getBoolean(1);
            }
        }

        return false;
    }
}
