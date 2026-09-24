package com.guille.persistencia.dao;

import com.guille.modelos.Duenio;
import com.guille.modelos.TipoDocumento;
import com.guille.modelos.Veterinario;
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

    public List<Duenio> obtenerDuenios() throws SQLException {

        List<Duenio> duenios = new ArrayList<>();

        try (Connection connection = this.conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM duenios ORDER BY apellido_duenio, nombre_duenio"); ResultSet resultado = statement.executeQuery()){

            while ( resultado.next()){
                int id = resultado.getInt("id_duenio");
                String nombre = resultado.getString("nombre_duenio");
                String apellido = resultado.getString("apellido_duenio");
                String tipoDocumentoST = resultado.getString("tipo_documento_duenio");
                String numeroDocumento = resultado.getString("numero_documento_duenio");
                String telefono = resultado.getString("telefono_duenio");
                LocalDateTime fechaAlta = resultado.getObject("fecha_alta_duenio", LocalDateTime.class);

                TipoDocumento tipo = TipoDocumento.obtenerTipoDocumento(tipoDocumentoST);

                Duenio duenio = new Duenio(id,nombre,apellido,tipo,numeroDocumento,telefono,fechaAlta);

                duenios.add(duenio);
            }
        }

        return duenios;
    }

    public Duenio obtenerDuenioPorDocumento(TipoDocumento tipoDocumento, String numeroDocumento) throws SQLException{

        Duenio duenio = null;

        try( Connection connection = this.conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM duenios WHERE tipo_documento_duenio = ? AND  numero_documento_duenio = ?" )){

            statement.setString(1, tipoDocumento.getCodigo());
            statement.setString(2,numeroDocumento);

            try( ResultSet resultado = statement.executeQuery()){
                if ( resultado.next()){
                    int id = resultado.getInt("id_duenio");
                    String nombre = resultado.getString("nombre_duenio");
                    String apellido = resultado.getString("apellido_duenio");
                    String telefono = resultado.getString("telefono_duenio");
                    LocalDateTime fechaAlta = resultado.getObject("fecha_alta_duenio", LocalDateTime.class);

                    duenio = new Duenio(id,nombre,apellido,tipoDocumento,numeroDocumento,telefono,fechaAlta);

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
                "RETURNING id_duenio";

        try (Connection connection  = this.conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,duenio.getNombre());
            statement.setString(2,duenio.getApellido());
            statement.setString(3,duenio.getTipoDocumento().getCodigo());
            statement.setString(4,duenio.getNumeroDocumento());
            statement.setString(5,duenio.getTelefono());
            statement.setObject(6,duenio.getFechaAlta());

            try( ResultSet resultado = statement.executeQuery()){
                if ( resultado.next()){
                    int id = resultado.getInt("id_duenio");
                    duenioBD = new Duenio(id,duenio.getNombre(),duenio.getApellido(),duenio.getTipoDocumento(),duenio.getNumeroDocumento(),duenio.getTelefono(),duenio.getFechaAlta());
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
            statement.setString(3,duenio.getTipoDocumento().getCodigo());
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
