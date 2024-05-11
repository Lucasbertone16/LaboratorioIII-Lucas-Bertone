package tubanco.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CuentaBancaria {

    public enum TipoCuenta {
        AHORRO,
        CORRIENTE
    }
    protected Cliente cliente;
    protected String nombre;
    protected LocalDateTime fechaCreacion;
    protected TipoCuenta tipoCuenta;
    protected int balance;
    protected int numeroCuenta;
    private List<MovimientoCuenta> movimientos=new ArrayList<>();



    public String getNombre() {
        return nombre;
    }

    public CuentaBancaria setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public CuentaBancaria setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public int getBalance() {
        return balance;
    }

    public CuentaBancaria setBalance(int balance) {
        this.balance = balance;
        return this;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public List<MovimientoCuenta> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoCuenta> movimientos) {
        this.movimientos = movimientos;
    }

    public void agregarMovimiento(MovimientoCuenta movimiento) {
        movimientos.add(movimiento);
    }
}