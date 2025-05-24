package com.douc.bankboston.modelos;

public class Cuenta {
    private int numeroCuenta;
    private long saldo = 0; // todas las cuentas se inicializan con cero


    // constructor
    public Cuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    // setters

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    // getters

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public long getSaldo() {
        return saldo;
    }

    // metodos customs
    public void depositarMonto(long monto) {
        setSaldo(getSaldo() + monto);
    }

    public void girarMonto(long monto) {
        setSaldo(getSaldo() - monto);
    }
}