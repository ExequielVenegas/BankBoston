package com.douc.bankboston.modelos.cuenta.base;

public abstract class CuentaBancaria {
    private Long numeroCuenta;
    private Long saldo; // Se puede inicializar en 0L o no, dependiendo de la lógica que quieras forzar en los constructores.

    // Constructor 1: Inicializa numeroCuenta y saldo (sobrecarga de métodos)
    public CuentaBancaria(Long numeroCuenta, Long saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
    }

    // Constructor 2: Inicializa solo numeroCuenta, saldo por defecto en 0 (sobrecarga de métodos)
    public CuentaBancaria(Long numeroCuenta) {
        this(numeroCuenta, 0L); // Llama al primer constructor, inicializando saldo en 0
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

    // Métodos abstractos (deben ser implementados por las subclases)
    public abstract void depositarMonto(Long monto);

    public abstract void girarMonto(Long monto);

    // Nuevo metodo abstracto requerido (sobreescritura)
    public abstract void mostrarDetalleCuenta(); // Este metodo será implementado de forma específica por cada tipo de cuenta

    public abstract void calcularInteres(); // Declaración correcta de un método abstracto

}