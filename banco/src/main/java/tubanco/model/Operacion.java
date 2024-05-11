package tubanco.model;

import java.time.LocalDateTime;
import java.util.List;

public class Operacion {

    public enum TipoOperacion {
        DEPOSITO,
        RETIRO,
        TRANSFERENCIA,
        CONSULTA_SALDO
    }

    private LocalDateTime fechaOperacion;
    private TipoOperacion tipoOperacion;
    private List<MovimientoCuenta> movimientos;

    public Operacion(LocalDateTime fechaOperacion, TipoOperacion tipoOperacion, List<MovimientoCuenta> movimientos) {
        this.fechaOperacion = fechaOperacion;
        this.tipoOperacion = tipoOperacion;
        this.movimientos = movimientos;
    }

    public Operacion(){
        
    }

    public LocalDateTime getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(LocalDateTime fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public List<MovimientoCuenta> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoCuenta> movimientos) {
        this.movimientos = movimientos;
    }

    public void realizarDeposito(CuentaBancaria cuenta, double monto) {
        MovimientoCuenta movimiento = new MovimientoCuenta(LocalDateTime.now(), MovimientoCuenta.TipoOperacion.DEPOSITO, monto);
        cuenta.agregarMovimiento(movimiento);
    }

    public void realizarRetiro(CuentaBancaria cuenta, double monto) {
        MovimientoCuenta movimiento = new MovimientoCuenta(LocalDateTime.now(), MovimientoCuenta.TipoOperacion.RETIRO, monto);
        cuenta.agregarMovimiento(movimiento);
    }

    public void realizarTransferencia(CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino, double monto) {
        MovimientoCuenta movimientoSalida = new MovimientoCuenta(LocalDateTime.now(), MovimientoCuenta.TipoOperacion.TRANSFERENCIA, -monto);
        MovimientoCuenta movimientoEntrada = new MovimientoCuenta(LocalDateTime.now(), MovimientoCuenta.TipoOperacion.TRANSFERENCIA, monto);
        cuentaOrigen.agregarMovimiento(movimientoSalida);
        cuentaDestino.agregarMovimiento(movimientoEntrada);
    }

    public double consultarSaldo(CuentaBancaria cuenta) {
        double saldo = 0;
        List<MovimientoCuenta> movimientos = cuenta.getMovimientos();
        for (MovimientoCuenta movimiento : movimientos) {
            saldo += movimiento.getMonto();
        }
        return saldo;
    }
    
}