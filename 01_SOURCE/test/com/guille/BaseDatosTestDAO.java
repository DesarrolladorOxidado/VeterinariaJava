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

}
