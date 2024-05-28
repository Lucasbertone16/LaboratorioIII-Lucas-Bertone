package tubanco.ConexionDB;

import java.sql.*;

import tubanco.model.Cliente;

public class ClienteDAO {
    Conexion conexion = new Conexion();
    PreparedStatement statement = null;
     public void crearCliente(Cliente cliente) {
        try {
            Connection connection = conexion.ConectarDB();
            statement = connection.prepareStatement("INSERT INTO usuario (nombre, apellido, dni, fechaNacimiento, fechaAlta, banco, tipoPersona) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellido());
            statement.setLong(3, cliente.getDni());
            statement.setString(4, String.valueOf(cliente.getFechaNacimiento()));
            statement.setString(5, String.valueOf(cliente.getFechaAlta()));
            statement.setString(6, cliente.getBanco());
            statement.setString(7, cliente.getTipoPersona());
            statement.executeUpdate();
            System.out.println("Cliente creado exitosamente.");
        } catch (SQLException e) {
            System.out.println("Error al crear el cliente: " + e.getMessage());
        }
    }

    public void eliminarCliente(int identificador) {
        try {
            Connection connection = conexion.ConectarDB();
            statement=connection.prepareStatement("DELETE FROM usuario WHERE dni=?");
            statement.setInt(1, identificador);
            int filasAfectadas= statement.executeUpdate();
            if (filasAfectadas>0) {
                System.out.println("Se elimino correctamente al usuario con identificador: "+ identificador);
            }
            else{
                System.out.println("Cliente no encontrado en la base de datos");
            }
            statement.close(); // Cerramos el statement
        } catch (SQLException e) {
            System.out.println("Error en la QUERY: "+ e.getMessage());
        }
    }

    public void modificarCliente(int identificador){

    }

    public void mostarCliente(int identificador){

    }
}