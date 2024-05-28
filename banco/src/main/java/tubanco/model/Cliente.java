package tubanco.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Cliente extends Persona{
   protected String banco;
   protected LocalDate fechaAlta;
   protected String tipoPersona;
   protected Set<CuentaBancaria> cuentas = new HashSet<>();
   protected long identificador;

   public Cliente(){}

public void agregarCuenta(CuentaBancaria cuenta) {
    cuentas.add(cuenta);
    cuenta.setCliente(this);
}


public void eliminarCuenta(CuentaBancaria cuenta) {
    cuentas.remove(cuenta);
    cuenta.setCliente(null);
}

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Set<CuentaBancaria> getCuentas() {
        return cuentas;
    }

    public void addCuenta(CuentaBancaria cuenta) {
        this.cuentas.add(cuenta);
    }

    public void setCuentas(Set<CuentaBancaria> cuentas) {
        this.cuentas = cuentas;
    }

    public long getIdentificador() {
        return identificador;
    }

    public void setIdentificador(long identificador) {
        this.identificador = identificador;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
    
}
