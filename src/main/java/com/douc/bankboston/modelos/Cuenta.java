package com.douc.bankboston.modelos;

public abstract class Cuenta {
    private Long numeroCuenta;
    private Long saldo = 0L; // todas las cuentas se inicializan con cero


    // constructor
    public Cuenta(Long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    // setters

    public void setNumeroCuenta(Long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setSaldo(Long saldo) {
        this.saldo = saldo;
    }

    // getters

    public Long getNumeroCuenta() {
        return numeroCuenta;
    }

    public Long getSaldo() {
        return saldo;
    }

    // metodos customs
    public abstract void depositarMonto(Long monto);

    public abstract void girarMonto (Long monto);

}