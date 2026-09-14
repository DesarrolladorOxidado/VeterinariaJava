package com.guille.persistencia;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface OperacionTransaccional <T>{

    T ejecutar(Connection connection) throws SQLException;
}
