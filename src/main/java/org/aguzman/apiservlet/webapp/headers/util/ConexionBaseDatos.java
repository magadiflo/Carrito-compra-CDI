package org.aguzman.apiservlet.webapp.headers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {

    private static String URL = "jdbc:mysql://localhost:3306/bd_java_curso_master?serverTimezone=America/Lima";
    private static String USERNAME = "admin";
    private static String PASSWORD = "magadiflo";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

}
