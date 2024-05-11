package tubanco.model;
import java.util.Scanner;

public class Menu {
    protected Scanner scanner;
    protected ClienteInput clienteInput;
    protected CuentaBancariaInput cuentaBancariaInput;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.clienteInput = new ClienteInput(scanner);
        this.cuentaBancariaInput = new CuentaBancariaInput(scanner);
    }

    public void menuPrincipal() {
        int opcion;
        do {
            System.out.println("0. Salir");
            System.out.println("1. Crear cliente");
            System.out.println("2. Modificar cliente");
            System.out.println("3. Mostrar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Crear cuenta bancaria");
            System.out.println("6. Realizar operaciones");
            System.out.println("7. Consultar movimientos");
            
            System.out.print("Elige una opcion: ");
            opcion = this.scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 0:
                    System.out.println("Saliendo del programa");
                    break;
                case 1:
                    clienteInput.ingresarCliente();
                    break;
                case 2:
                System.out.println("Ingrese el identificador del cliente que desea modificar: ");
                int identificadorClienteModificar = scanner.nextInt();
                scanner.nextLine();
            
                System.out.println("Ingrese el atributo que desea modificar (nombre, apellido, dni, banco, fechaNacimiento, fechaAlta): ");
                String atributoModificar = scanner.nextLine();
            
                clienteInput.modificarCliente(identificadorClienteModificar, atributoModificar);
                    break;
                case 3:
                System.out.println("Ingrese el identificador del cliente que desea mostrar: ");
                int identificadorClienteMostrar = scanner.nextInt();
                clienteInput.mostrarCliente(identificadorClienteMostrar);
                    break;
                case 4:
                    System.out.println("Ingrese el identificador del cliente que desea eliminar: ");
                    int identificadorClienteEliminar = scanner.nextInt();
                    scanner.nextLine();
                    clienteInput.eliminarCliente(identificadorClienteEliminar);
                    break;
                case 5:
                    System.out.println("Ingrese el identificador del cliente para asociar la cuenta bancaria: ");
                    int identificadorClienteCrearCuenta = scanner.nextInt();
                    scanner.nextLine();
                    Cliente clienteCrearCuenta = obtenerClientePorIdentificador(identificadorClienteCrearCuenta);
                    if (clienteCrearCuenta != null) {
                        cuentaBancariaInput.crearCuentaBancaria(clienteCrearCuenta);
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;
                case 6:
                    System.out.println("Ingrese el número de cuenta para realizar operaciones: ");
                    int numeroCuentaOperaciones = scanner.nextInt();
                    scanner.nextLine();
                    CuentaBancaria cuentaOperaciones = obtenerCuentaPorNumero(numeroCuentaOperaciones);
                    if (cuentaOperaciones != null) {
                        cuentaBancariaInput.realizarOperaciones(cuentaOperaciones);
                    } else {
                        System.out.println("Cuenta bancaria no encontrada.");
                    }
                    break;
                case 7:
                System.out.println("Ingrese el número de cuenta para consultar movimientos: ");
                int numeroCuentaConsultar = scanner.nextInt();
                scanner.nextLine();
            
               
                CuentaBancaria cuentaConsultar = null;
                for (Cliente cliente : ClienteInput.getClientes()) {
                    for (CuentaBancaria cuenta : cliente.getCuentas()) {
                        if (cuenta.getNumeroCuenta() == numeroCuentaConsultar) {
                            cuentaConsultar = cuenta;
                            break;
                        }
                    }
                    if (cuentaConsultar != null) {
                        break;
                    }
                }
            
                if (cuentaConsultar != null) {
                    
                    cuentaBancariaInput.mostrarMovimientos(cuentaConsultar);
                } else {
                    System.out.println("No se encontró ninguna cuenta con el número especificado.");
                }
                    break;
                default:
                    System.out.println("ERROR, VOLVER A INTENTAR NUEVAMENTE");
                    break;
            }

        } while (opcion != 0);
    }

    // Método para obtener un cliente por su identificador
    private Cliente obtenerClientePorIdentificador(int identificador) {
        for (Cliente cliente : ClienteInput.getClientes()) {
            if (cliente.getIdentificador() == identificador) {
                return cliente;
            }
        }
        return null;
    }

    
    private CuentaBancaria obtenerCuentaPorNumero(int numeroCuenta) {
        for (Cliente cliente : ClienteInput.getClientes()) {
            for (CuentaBancaria cuenta : cliente.getCuentas()) {
                if (cuenta.getNumeroCuenta() == numeroCuenta) {
                    return cuenta;
                }
            }
        }
        return null;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public ClienteInput getClienteInput() {
        return clienteInput;
    }

    public void setClienteInput(ClienteInput clienteInput) {
        this.clienteInput = clienteInput;
    }

    public CuentaBancariaInput getCuentaBancariaInput() {
        return cuentaBancariaInput;
    }

    public void setCuentaBancariaInput(CuentaBancariaInput cuentaBancariaInput) {
        this.cuentaBancariaInput = cuentaBancariaInput;
    }

    
}