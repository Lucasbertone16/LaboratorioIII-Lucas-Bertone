package tubanco.ConexionDB;
import java.sql.*;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/serviciob    ancario";
    private static final String USER = "root";
    private static final String PASSWORD = "pelambre02";

    public Connection ConectarDB() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("SE CONECTO A LA BASE DE DATOS");
        } catch (SQLException e) {
            System.out.println("Error en la conexion debido a: " + e);
        }
        return conexion;
    }
}
