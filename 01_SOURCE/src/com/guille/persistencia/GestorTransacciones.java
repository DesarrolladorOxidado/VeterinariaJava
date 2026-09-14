package com.guille.persistencia;

import java.sql.Connection;
import java.sql.SQLException;

public class GestorTransacciones {

    private final ConexionBD conexionBD;

    public GestorTransacciones(ConexionBD conexionBD){
        this.conexionBD = conexionBD;
    }

    public <T> T ejecutar(OperacionTransaccional<T> operacion ) throws SQLException{
        try(Connection connection = this.conexionBD.obtenerConexion()){
            connection.setAutoCommit(false);

            try {
                T resultado = operacion.ejecutar(connection);

                connection.commit();

                return resultado;
            }catch (SQLException e){
                try {
                    connection.rollback();
                }catch (SQLException rollbackException){
                    e.addSuppressed(rollbackException);
                }
                throw e;
            }
        }
    }
}
