package com.guille;

import com.guille.persistencia.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDatosTestDAO {

    private final ConexionBD conexionBD;

    public BaseDatosTestDAO( ConexionBD conexionBD){
        this.conexionBD = conexionBD;
    }

    public void borrarDatos() throws SQLException{
        try(Connection connection = this.conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement("TRUNCATE TABLE duenios,mascotas,historias_clinicas,consultas,veterinarios")){
            statement.executeUpdate();
        }
    }

    public void cambiarEstadoMascota( int idMascota, boolean estado ) throws SQLException{
        String sql = "UPDATE mascotas SET activo_mascota = ? WHERE id_mascota = ?";

        try ( Connection connection = this.conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setBoolean(1,estado);
            statement.setInt(2,idMascota);

            int resultado = statement.executeUpdate();

            if ( resultado != 1)
                throw new SQLException("No se pudo actualizar el estado de la mascota");
        }
    }

    public void cambiarEstadoDuenio( int idDuenio, boolean estado ) throws SQLException{
        String sql = "UPDATE duenios SET activo_duenio = ? WHERE id_duenio = ?";

        try ( Connection connection = this.conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setBoolean(1,estado);
            statement.setInt(2,idDuenio);

            int resultado = statement.executeUpdate();

            if ( resultado != 1)
                throw new SQLException("No se pudo actualizar el estado del dueño.");
        }
    }

    public void cambiarEstadoVeterinario( int idVeterinario, boolean estado ) throws SQLException{
        String sql = "UPDATE veterinarios SET activo_veterinario = ? WHERE id_veterinario = ?";

        try ( Connection connection = this.conexionBD.obtenerConexion(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setBoolean(1,estado);
            statement.setInt(2,idVeterinario);

            int resultado = statement.executeUpdate();

            if ( resultado != 1)
                throw new SQLException("No se pudo actualizar el estado del veterinario.");
        }
    }

}
