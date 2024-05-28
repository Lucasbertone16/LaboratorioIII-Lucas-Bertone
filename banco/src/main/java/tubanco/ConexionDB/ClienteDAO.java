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

    public void mostrarCliente(int identificador){
        try {
            Connection connection = conexion.ConectarDB();
            statement=connection.prepareStatement("SELECT * FROM usuario WHERE dni=?");
            statement.setInt(1, identificador);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String nombre=rs.getString("nombre");
                String apellido=rs.getString("apellido");
                identificador=rs.getInt("dni");
                String fechaNacimiento=rs.getString("fechaNacimiento");
                String fechaAlta=rs.getString("fechaAlta");
                String banco=rs.getString("banco");
                String tipoPersona=rs.getString("tipoPersona");


                System.out.println("Sus datos son: \n" + "Nombre: " + nombre + "\n" + "Apellido: " + apellido + "\n" + "DNI: " + identificador + "\n" + "Fecha de nacimiento: " + fechaNacimiento + "\n" + "Fecha de alta: " + fechaAlta + "\n" + "Banco: " + banco + "\n" + "Tipo de persona: " + tipoPersona);                
            }
            statement.execute();
            } catch (SQLException e) {
            System.out.println("Error en la QUERY: "+ e.getMessage());
        }
    }
}