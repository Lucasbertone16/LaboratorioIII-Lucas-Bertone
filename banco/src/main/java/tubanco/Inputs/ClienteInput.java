package tubanco.Inputs;

import java.util.Scanner;
import java.sql.*;
import tubanco.model.Cliente; 
import tubanco.ConexionDB.Conexion;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteInput {
    protected static Scanner scanner = new Scanner(System.in);
    protected static int contadorIdentificadores = 0;
    Conexion conexion = new Conexion();

    public ClienteInput(Scanner scanner) {
        this.scanner = scanner;
    }
    

    public void ingresarCliente(){
        Cliente cliente= new Cliente(); 

        System.out.println("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();
        cliente.setNombre(nombre);

        System.out.println("Ingrese el apellido del cliente: ");
        String apellido = scanner.nextLine();
        cliente.setApellido(apellido);
        
        System.out.println("Ingrese el DNI del cliente: ");
        long dni = 0;
        
        while (true) {
            if (scanner.hasNextLong()) {
                dni = scanner.nextLong();
                if (String.valueOf(dni).length() == 8) {
                    break;
                } else {
                    System.out.println("El DNI debe ser de 8 cifras.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido para el DNI.");
                scanner.next(); 
            }
        }
        
        cliente.setDni(dni);
        scanner.nextLine(); 

        System.out.println("Ingrese el banco del cliente: ");
        String banco=scanner.nextLine();
        cliente.setBanco(banco);

        cliente.setIdentificador(contadorIdentificadores++);
        cliente.setIdentificador(contadorIdentificadores);
        System.out.println("El identificador del cliente es: " + cliente.getIdentificador());

        System.out.println("Ingrese la fecha de nacimiento del cliente (Formato: YYYY-MM-DD): ");
        LocalDate fechaNacimiento = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                fechaNacimiento = LocalDate.parse(scanner.nextLine());
                // Validar que el cliente tenga al menos 18 años
                LocalDate fechaHoy = LocalDate.now();
                LocalDate fechaMayorEdad = fechaHoy.minusYears(18);
                if (fechaNacimiento.isBefore(fechaMayorEdad)) {
                    fechaValida = true;
                } else {
                    System.out.println("El cliente debe tener al menos 18 años de edad.");
                }
            } catch (Exception e) {
                System.out.println("Formato de fecha inválido. Ingrese la fecha en formato YYYY-MM-DD:");
            }
        }
        cliente.setFechaNacimiento(fechaNacimiento);

        


        System.out.println("La fecha de creacion de la cuenta es: " + LocalDate.now());
        cliente.setFechaAlta(LocalDate.now());

        Connection conn=conexion.ConectarDB();
        String sql="INSERT INTO usuario (nombre, apellido, dni, fechaNacimiento, fechaAlta, banco, tipoPersona) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement prepareStatement=conn.prepareStatement(sql)) {
            prepareStatement.setString(1, cliente.getNombre());
            prepareStatement.setString(2, cliente.getApellido());
            prepareStatement.setLong(3, cliente.getDni());
            prepareStatement.setDate(4, Date.valueOf(cliente.getFechaNacimiento()));


        } catch (SQLException e) {
            
        }


        
    }

    

    private boolean validarFormatoFecha(String fechaStr) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        return fechaStr.matches(regex);
    }




    public static Scanner getScanner() {
        return scanner;
    }


    public static void setScanner(Scanner scanner) {
        ClienteInput.scanner = scanner;
    }


    public static int getContadorIdentificadores() {
        return contadorIdentificadores;
    }


    public static void setContadorIdentificadores(int contadorIdentificadores) {
        ClienteInput.contadorIdentificadores = contadorIdentificadores;
    } 
    
    
 }
