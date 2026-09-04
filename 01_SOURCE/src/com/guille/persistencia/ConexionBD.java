package com.guille.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:postgresql://localhost:5432/veterinaria";
    private static final String USUARIO = "veterinaria_app";
    private static final String PASSWORD = System.getenv("VETERINARIA_DB_PASSWORD");

    public Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL,USUARIO,PASSWORD);
    }
}
