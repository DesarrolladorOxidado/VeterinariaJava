package com.guille.persistencia.dao;

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

public class VeterinarioDAO extends Dao{

    public VeterinarioDAO(ConexionBD conexionBD){
        super(conexionBD);
    }


    public List<Veterinario> obtenerVeterinarios() throws SQLException {

        List<Veterinario> veterinarios = new ArrayList<>();

        try (Connection connection = this.conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM veterinarios ORDER BY apellido_veterinario, nombre_veterinario"); ResultSet resultado = statement.executeQuery()){

            while ( resultado.next()){
                int id = resultado.getInt("id_veterinario");
                String nombre = resultado.getString("nombre_veterinario");
                String apellido = resultado.getString("apellido_veterinario");
                String tipoDocumentoST = resultado.getString("tipo_documento_veterinario");
                String numeroDocumento = resultado.getString("numero_documento_veterinario");
                String telefono = resultado.getString("telefono_veterinario");
                String matricula = resultado.getString("matricula_veterinario");
                LocalDateTime fechaAlta = resultado.getObject("fecha_alta_veterinario",LocalDateTime.class);

                TipoDocumento tipo = TipoDocumento.obtenerTipoDocumento(tipoDocumentoST);

                Veterinario veterinario = new Veterinario(id,nombre,apellido,tipo,numeroDocumento,telefono,fechaAlta,matricula);

                veterinarios.add(veterinario);
            }
        }

        return veterinarios;
    }

    public Veterinario obtenerVeterinarioPorDocumento(TipoDocumento tipoDocumento, String numeroDocumento) throws SQLException{

        Veterinario veterinario = null;

        try( Connection connection = this.conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM veterinarios WHERE tipo_documento_veterinario = ? AND  numero_documento_veterinario = ?" )){

            statement.setString(1, tipoDocumento.getCodigo());
            statement.setString(2,numeroDocumento);

            try( ResultSet resultado = statement.executeQuery()){
                if ( resultado.next()){
                    int id = resultado.getInt("id_veterinario");
                    String nombre = resultado.getString("nombre_veterinario");
                    String apellido = resultado.getString("apellido_veterinario");
                    String telefono = resultado.getString("telefono_veterinario");
                    String matricula = resultado.getString("matricula_veterinario");
                    LocalDateTime fechaAlta = resultado.getObject("fecha_alta_veterinario",LocalDateTime.class);

                    veterinario = new Veterinario(id,nombre,apellido,tipoDocumento,numeroDocumento,telefono,fechaAlta,matricula);

                }
            }

        }

        return veterinario;
    }

    public Veterinario obtenerVeterinarioPorMatricula(String matricula) throws SQLException{

        Veterinario veterinario = null;

        try( Connection connection = this.conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM veterinarios WHERE matricula_veterinario = ?" )){

            statement.setString(1, matricula);

            try( ResultSet resultado = statement.executeQuery()){
                if ( resultado.next()){
                    int id = resultado.getInt("id_veterinario");
                    String nombre = resultado.getString("nombre_veterinario");
                    String apellido = resultado.getString("apellido_veterinario");
                    String telefono = resultado.getString("telefono_veterinario");
                    String tipoDocumentoST = resultado.getString("tipo_documento_veterinario");
                    String numeroDocumento = resultado.getString("numero_documento_veterinario");
                    LocalDateTime fechaAlta = resultado.getObject("fecha_alta_veterinario",LocalDateTime.class);

                    TipoDocumento tipoDocumento = TipoDocumento.obtenerTipoDocumento(tipoDocumentoST);

                    veterinario = new Veterinario(id,nombre,apellido,tipoDocumento,numeroDocumento,telefono,fechaAlta,matricula);

                }
            }

        }

        return veterinario;
    }

    public Veterinario registrarVeterinario(Veterinario veterinario) throws SQLException{

        Veterinario veterinarioBD = null;

        String sql = "INSERT INTO veterinarios(" +
                "nombre_veterinario," +
                "apellido_veterinario," +
                "tipo_documento_veterinario," +
                "numero_documento_veterinario," +
                "telefono_veterinario," +
                "matricula_veterinario," +
                "fecha_alta_veterinario)" +
                "VALUES(?,?,?,?,?,?,?) " +
                "RETURNING id_veterinario";

        try (Connection connection  = this.conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,veterinario.getNombre());
            statement.setString(2,veterinario.getApellido());
            statement.setString(3,veterinario.getTipoDocumento().getCodigo());
            statement.setString(4,veterinario.getNumeroDocumento());
            statement.setString(5,veterinario.getTelefono());
            statement.setString(6,veterinario.getMatricula());
            statement.setObject(7,veterinario.getFechaAlta());

            try( ResultSet resultado = statement.executeQuery()){
                if ( resultado.next()){
                    int id = resultado.getInt("id_veterinario");
                    veterinarioBD = new Veterinario(id,veterinario.getNombre(),veterinario.getApellido(),veterinario.getTipoDocumento(),veterinario.getNumeroDocumento(),veterinario.getTelefono(),veterinario.getFechaAlta(),veterinario.getMatricula());
                }
            }
        }

        return veterinarioBD;
    }

    public boolean existenVeterinarios() throws SQLException{
        try(Connection connection = this.conexionBD.obtenerConexion();PreparedStatement statement = connection.prepareStatement("SELECT EXISTS(SELECT 1 FROM veterinarios)"); ResultSet resultSet = statement.executeQuery()){
            if (resultSet.next())
                return resultSet.getBoolean(1);
        }

        return false;
    }
}
