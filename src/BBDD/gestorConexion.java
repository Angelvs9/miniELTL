package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class gestorConexion {
    private Connection conexion;

    public gestorConexion() {
        try {
            String carpeta = ConfigLoader.get().getProperty("db.carpeta");
            String nombreBD = ConfigLoader.get().getProperty("db.nombre");
            String user = ConfigLoader.get().getProperty("db.user");
            String password = ConfigLoader.get().getProperty("db.password");

            String url = "jdbc:h2:" + carpeta + nombreBD;

            conexion = DriverManager.getConnection(url, user, password);
            if (conexion != null) {
                System.out.println("conectado->" + nombreBD);
            }
        } catch (SQLException ex) {
            Logger.getLogger(gestorConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void cerrar() {
        try {
            conexion.close();
            System.out.println("cerrado");
        } catch (SQLException ex) {
            Logger.getLogger(gestorConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}