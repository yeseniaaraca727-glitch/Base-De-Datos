package pe.edu.upeu.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Esta clase se encarga de manejar la conexión con la base de datos SQLite
public class DatabaseConnection {

    // URL de conexión (ruta del archivo .db)
    private static final String URL = "jdbc:sqlite:data/persona_db";

    // Método para obtener la conexión
    public static Connection getConnection() {
        try {
            // Intento establecer la conexión con SQLite
            Connection conn = DriverManager.getConnection(URL);

            System.out.println(" Conexión exitosa a SQLite");

            return conn;

        } catch (SQLException e) {
            System.out.println(" Error de conexión: " + e.getMessage());
            return null;
        }
    }
}