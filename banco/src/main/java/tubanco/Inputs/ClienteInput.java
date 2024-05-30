package tubanco.Inputs;

import java.util.Scanner;

import tubanco.ConexionDB.*;
import tubanco.Exceptions.FormatoFechaIncorrectoException;
import tubanco.Exceptions.menorDeEdadException;
import tubanco.model.Cliente; 
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ClienteInput {
    protected static List<Cliente> clientes = new ArrayList<>();
    protected static Scanner scanner = new Scanner(System.in);

    public ClienteInput(Scanner scanner) {
    }
    

    public Cliente ingresarCliente() {
        Cliente cliente = new Cliente(); 

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
        String banco = scanner.nextLine();
        cliente.setBanco(banco);

        System.out.println("Su identificador es: " + cliente.getDni());
        cliente.setIdentificador(cliente.getDni());

        System.out.println("Ingrese la fecha de nacimiento del cliente (Formato: YYYY-MM-DD): ");
        LocalDate fechaNacimiento = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                fechaNacimiento = LocalDate.parse(scanner.nextLine());
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

        cliente.setFechaAlta(LocalDate.now());
        System.out.println("Su fecha de alta es: " + cliente.getFechaAlta());

        System.out.println("Ingrese el tipo de persona del cliente (Juridica - Fisica): ");
        String tipoPersona = scanner.nextLine();
        cliente.setTipoPersona(tipoPersona);

        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.crearCliente(cliente);

        return cliente;
    }

    public void eliminarCliente(int identificador) {
        ClienteDAO clienteDAO= new ClienteDAO();
        clienteDAO.eliminarCliente(identificador);
    }

    public void modificarCliente() {
        boolean continuar = true;
        do {
            System.out.println("Ingrese el identificador del cliente:");
            int identificador = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt()
    
            System.out.println("Seleccione el atributo a modificar:");
            System.out.println("1. Nombre");
            System.out.println("2. Apellido");
            System.out.println("3. Fecha de Nacimiento");
            System.out.println("4. Banco");
            System.out.println("5. Tipo de Persona");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida, por favor seleccione una opción válida.");
                continuar = true; // Volver a pedir la selección
                continue; // Saltar al inicio del bucle
            }
    
            String atributoModificar;
            switch (opcion) {
                case 1:
                    atributoModificar = "nombre";
                    break;
                case 2:
                    atributoModificar = "apellido";
                    break;
                case 3:
                    atributoModificar = "fechaNacimiento";
                    break;
                case 4:
                    atributoModificar = "banco";
                    break;
                case 5:
                    atributoModificar = "tipoPersona";
                    break;
                default:
                    System.out.println("Opción inválida, por favor seleccione una opción válida.");
                    continuar = true; // Volver a pedir la selección
                    continue; // Saltar al inicio del bucle
            }

            if (opcion==3) {
                String nuevoValor;
                do {
                    try {
                        System.out.println("Ingrese el nuevo valor de la fecha en formato año-mes-dia: ");
                        nuevoValor = scanner.nextLine().trim();
                        validarFormatoFecha(nuevoValor);
                        menorDeEdadException(nuevoValor);
                        break;
                    } catch (FormatoFechaIncorrectoException | menorDeEdadException e) {
                        System.out.println(e.getMessage());
                    }
                    
                } while (continuar);
            }
            else{
                System.out.println("Ingrese el nuevo valor para " + atributoModificar + ":");
                String nuevoValor = scanner.nextLine().trim();
    
                ClienteDAO clienteDAO = new ClienteDAO();
                clienteDAO.modificarCliente(identificador, atributoModificar, nuevoValor);
            }
    
    
            System.out.println("¿Desea modificar otro atributo del mismo cliente? (s/n)");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            continuar = respuesta.equals("s");
    
        } while (continuar);
    
    }
    
    public void mostrarCliente(int identificador) {
        ClienteDAO clienteDAO= new ClienteDAO();
        clienteDAO.mostrarCliente(identificador);
    }

    private void validarFormatoFecha(String fechaStr) throws FormatoFechaIncorrectoException {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";

        if (!fechaStr.matches(regex)) {
            throw new FormatoFechaIncorrectoException("Formato de fecha incorrecto. Debe ser YYYY-MM-DD.");
        }
    }

    private void menorDeEdadException(String fechaStr) throws menorDeEdadException{
        LocalDate fechaActual= LocalDate.now();
        LocalDate fechaNacimiento = LocalDate.parse(fechaStr);
        Period edad = Period.between(fechaNacimiento, fechaActual);

        if (edad.getYears() < 18) {
            throw new menorDeEdadException("La persona debe ser mayor de 18 años.");
        }
    }
    



    public static List<Cliente> getClientes() {
        return clientes;
    }


    public static void setClientes(List<Cliente> clientes) {
        ClienteInput.clientes = clientes;
    }


    public static Scanner getScanner() {
        return scanner;
    }


    public static void setScanner(Scanner scanner) {
        ClienteInput.scanner = scanner;
    }


    
    
    
 }