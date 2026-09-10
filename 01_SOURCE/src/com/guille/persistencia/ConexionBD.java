package com.guille.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL_DESARROLLO = "jdbc:postgresql://localhost:5432/veterinaria";
    private static final String USUARIO_DESARROLLO = "veterinaria_app";
    private static final String PASSWORD_DESARROLLO = System.getenv("VETERINARIA_DB_PASSWORD");

    private static final String URL_TEST = "jdbc:postgresql://localhost:5432/veterinaria_test";
    private static final String USUARIO_TEST = "veterinaria_test_app";
    private static final String PASSWORD_TEST = System.getenv("VETERINARIA_TEST_DB_PASSWORD");


    public enum Ambiente{
        DESARROLLO,
        TEST
    }


    private String url;
    private String usuario;
    private String password;

    public ConexionBD(Ambiente ambiente){

        switch (ambiente ){
            case DESARROLLO -> {
                this.url = URL_DESARROLLO;
                this.usuario = USUARIO_DESARROLLO;
                this.password = PASSWORD_DESARROLLO;
            }
            case TEST -> {
                this.url = URL_TEST;
                this.usuario = USUARIO_TEST;
                this.password = PASSWORD_TEST;
            }
        }

    }

    public Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(this.url,this.usuario,this.password);
    }
}
