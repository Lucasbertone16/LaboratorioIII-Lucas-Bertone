package tubanco.Inputs;

import java.util.Scanner;
import tubanco.model.Cliente; 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteInput {
    protected static List<Cliente> clientes = new ArrayList<>();
    protected static Scanner scanner = new Scanner(System.in);
    protected static int contadorIdentificadores = 0;

    public ClienteInput(Scanner scanner) {
        this.scanner = scanner;
    }
    

    public Cliente ingresarCliente(){
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

        clientes.add(cliente);

        return cliente;
    }

    public void eliminarCliente(int identificador) {
        Cliente clienteAEliminar = null;
        System.out.println("Ingrese el identificador del cliente que desea eliminar: ");
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificador() == identificador) {
                clienteAEliminar = cliente;
                break;
            }
        }
        if (clienteAEliminar != null) {
            clientes.remove(clienteAEliminar);
            System.out.println("Cliente eliminado exitosamente.");
        } else {
            System.out.println("No se encontró ningún cliente con el identificador especificado.");
        }
    }

    public void modificarCliente(int identificador, String atributo) {
        Cliente clienteAModificar = null;
        System.out.println("Ingrese el identificador del cliente y luego el atributo que desea modificar: ");
        for (Cliente cliente : clientes) {
            if (cliente.getIdentificador() == identificador) {
                clienteAModificar = cliente;
                break;
            }
        }
    
        if (clienteAModificar != null) {
            if (atributo.equalsIgnoreCase("identificador")|| atributo.equalsIgnoreCase("banco")) {
                System.out.println("No se puede modificar el identificador, ni el banco!!");
            } else {
                switch (atributo.toLowerCase()) {
                    case "nombre":
                        System.out.println("Ingrese el nuevo nombre del cliente: ");
                        String nuevoNombre = scanner.nextLine();
                        clienteAModificar.setNombre(nuevoNombre);
                        break;
                    case "apellido":
                        System.out.println("Ingrese el nuevo apellido del cliente: ");
                        String nuevoApellido = scanner.nextLine();
                        clienteAModificar.setApellido(nuevoApellido);
                        break;
                    case "dni":
                        System.out.println("Ingrese el nuevo DNI del cliente: ");
                        long nuevoDNI = scanner.nextLong();
                        clienteAModificar.setDni(nuevoDNI);
                        break;
                    case "fechaNacimiento":
                    LocalDate fechaNacimiento = null;
                    boolean fechaValida = false;
                    while (!fechaValida) {
                        System.out.println("Ingrese la nueva fecha de nacimiento del cliente (Formato: YYYY-MM-DD): ");
                        String fechaStr = scanner.nextLine();
                        if (validarFormatoFecha(fechaStr)) {
                            LocalDate fechaIngresada = LocalDate.parse(fechaStr);
                            if (!fechaIngresada.equals(clienteAModificar.getFechaNacimiento())) { // Verifica si la nueva fecha es diferente a la fecha actual
                                fechaNacimiento = fechaIngresada;
                                fechaValida = true;
                            } else {
                                System.out.println("La nueva fecha de nacimiento debe ser diferente a la fecha actual del cliente.");
                            }
                        } else {
                            System.out.println("Formato de fecha inválido. Ingrese la fecha en formato YYYY-MM-DD:");
                        }
                    }
                    clienteAModificar.setFechaNacimiento(fechaNacimiento);
                    break;
                    case "fechaAlta":
                    LocalDate fechaAlta = null;
                    boolean fechaValida2 = false;
                    while (!fechaValida2) {
                        System.out.println("Ingrese la nueva fecha de alta del cliente (Formato: YYYY-MM-DD): ");
                        String fechaStr = scanner.nextLine();
                        if (validarFormatoFecha(fechaStr)) {
                            LocalDate fechaIngresada = LocalDate.parse(fechaStr);
                            if (!fechaIngresada.equals(clienteAModificar.getFechaAlta())) { // Verifica si la nueva fecha es diferente a la fecha actual
                                fechaAlta = fechaIngresada;
                                fechaValida = true;
                            } else {
                                System.out.println("La nueva fecha de alta debe ser diferente a la fecha actual del cliente.");
                            }
                        } else {
                            System.out.println("Formato de fecha inválido. Ingrese la fecha en formato YYYY-MM-DD:");
                        }
                    }
                    clienteAModificar.setFechaAlta(fechaAlta);
                    break;
                    default:
                        System.out.println("Atributo no válido.");
                        break;
                }
            }
        } else {
            System.out.println("No se encontró ningún cliente con el identificador especificado.");
        }
    }
    
    public void mostrarCliente(int identificador) {
        for (Cliente cliente : clientes) {
            System.out.println("Identificador: " + cliente.getIdentificador());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Apellido: " + cliente.getApellido());
            System.out.println("DNI: " + cliente.getDni());
            System.out.println("Banco: " + cliente.getBanco());
            System.out.println("Fecha de nacimiento: " + cliente.getFechaNacimiento());
            System.out.println("Fecha de alta: " + cliente.getFechaAlta());
            System.out.println("--------------------------------------");
        }
    }

    private boolean validarFormatoFecha(String fechaStr) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        return fechaStr.matches(regex);
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


    public static int getContadorIdentificadores() {
        return contadorIdentificadores;
    }


    public static void setContadorIdentificadores(int contadorIdentificadores) {
        ClienteInput.contadorIdentificadores = contadorIdentificadores;
    } 
    
    
 }
