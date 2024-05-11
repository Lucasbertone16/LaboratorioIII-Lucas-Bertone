package tubanco.model;

import java.time.LocalDateTime;

public class MovimientoCuenta {
    public enum TipoOperacion {
        DEPOSITO,
        RETIRO,
        TRANSFERENCIA
    }
    
   protected LocalDateTime horaMovimiento;
   protected TipoOperacion tipoOperacion;
   protected double monto;

    public MovimientoCuenta(LocalDateTime horaMovimiento, TipoOperacion tipoOperacion, double monto) {
        this.horaMovimiento = horaMovimiento;
        this.tipoOperacion = tipoOperacion;
        this.monto = monto;
    }

    public LocalDateTime getHoraMovimiento() {
        return horaMovimiento;
    }

    public void setHoraMovimiento(LocalDateTime horaMovimiento) {
        this.horaMovimiento = horaMovimiento;
    }

    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(TipoOperacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}

