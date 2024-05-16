package tubanco.Inputs;

import java.time.LocalDate;
import java.util.Scanner;

import tubanco.model.*;


public class CuentaBancariaInput {
    private Scanner scanner;

    public CuentaBancariaInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public CuentaBancaria crearCuentaBancaria(Cliente cliente) {
        if (cliente.getCuentas() != null && !cliente.getCuentas().isEmpty()) {
            System.out.println("El cliente ya tiene una cuenta bancaria asociada.");
            return null; // Opcional: Retornar null para indicar que no se pudo crear la cuenta
        }

        CuentaBancaria cuenta = new CuentaBancaria();

        System.out.println("Ingrese el numero de la cuenta: ");
        int numeroCuenta = scanner.nextInt(); 
        cuenta.setNumeroCuenta(numeroCuenta);

        scanner.nextLine(); // Consumir la nueva línea pendiente

        System.out.println("Ingrese el tipo de cuenta (AHORRO o CORRIENTE):");
        String tipoCuenta = scanner.nextLine().toUpperCase(); // Convertir a mayúsculas
        cuenta.setTipoCuenta(CuentaBancaria.TipoCuenta.valueOf(tipoCuenta)); // Asignación de tipo TipoCuenta

        System.out.println("El balance de la cuenta comenzará siendo 0. Si necesita ingresar dinero deposite dinero en su cuenta");

        System.out.println("Ingrese la fecha de creación de la cuenta (Formato: YYYY-MM-DD): ");
        LocalDate fechaCreacion = null;
        boolean fechaValida = false;
        while (!fechaValida) {
            try {
                fechaCreacion = LocalDate.parse(scanner.nextLine());
                fechaValida = true;
            } catch (Exception e) {
                System.out.println("Formato de fecha inválido. Ingrese la fecha en formato YYYY-MM-DD:");
            }
        }
        cuenta.setFechaCreacion(fechaCreacion.atStartOfDay()); 

        cliente.addCuenta(cuenta); 

        return cuenta;
    }
    public void realizarOperaciones(CuentaBancaria cuenta) {
        Operacion operacion = new Operacion();

        System.out.println("Seleccione la operación que desea realizar:");
        System.out.println("1. Depósito");
        System.out.println("2. Retiro");
        System.out.println("3. Consulta de saldo");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Ingrese el monto a depositar:");
                double montoDeposito = scanner.nextDouble();
                operacion.realizarDeposito(cuenta, montoDeposito);
                System.out.println("Depósito realizado exitosamente.");
                break;
            case 2:
                System.out.println("Ingrese el monto a retirar:");
                double montoRetiro = scanner.nextDouble();
                operacion.realizarRetiro(cuenta, montoRetiro);
                System.out.println("Retiro realizado exitosamente.");
                break;
            case 3:
                double saldo = operacion.consultarSaldo(cuenta);
                System.out.println("Saldo actual: " + saldo);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    public void mostrarMovimientos(CuentaBancaria cuenta) {
        System.out.println("Movimientos de la cuenta bancaria:");
    
        for (MovimientoCuenta movimiento : cuenta.getMovimientos()) {
            System.out.println("Tipo: " + movimiento.getTipoOperacion());
            System.out.println("Fecha: " + movimiento.getHoraMovimiento());
            System.out.println("Monto: " + movimiento.getMonto());
            System.out.println("--------------------------------------");
        }
    }
}
