package com.douc.bankboston.modelos.cuenta.tipos;

import com.douc.bankboston.modelos.cuenta.base.CuentaBancaria;

public class CuentaBancariaAhorro extends CuentaBancaria {
    private int limiteGirosAnuales;
    private int contadorGiros;
    private double tasaInteresAnual;
    private double interesGenerado; // Nuevo atributo para almacenar el interés calculado

    public CuentaBancariaAhorro(Long numeroCuenta) {
        super(numeroCuenta); // Llama al constructor de la clase padre
        this.limiteGirosAnuales=7; // solo tiene 7 giros anuales disponibles antes de perder intereses
        this.contadorGiros=0; // se inicializa en 0
        this.tasaInteresAnual=0.006;
    }

    // Constructor con saldo inicial (Ejemplo de sobrecarga en subclase, aunque ya hay en la superclase) sin usar.
    public CuentaBancariaAhorro(Long numeroCuenta, Long saldoInicial) {
        super(numeroCuenta, saldoInicial);
        this.limiteGirosAnuales = 7;
        this.contadorGiros = 0;
        this.tasaInteresAnual = 0.006;
    }

    // getter and setters


    public int getLimiteGirosAnuales() {
        return limiteGirosAnuales;
    }

    public int getContadorGiros() {
        return contadorGiros;
    }

    public double getTasaInteresAnual() {
        return tasaInteresAnual;
    }

    public void setLimiteGirosAnuales(int limiteGirosAnuales) {
        this.limiteGirosAnuales = limiteGirosAnuales;
    }

    public void setContadorGiros(int contadorGiros) {
        this.contadorGiros = contadorGiros;
    }

    public void setTasaInteresAnual(double tasaInteresAnual) {
        this.tasaInteresAnual = tasaInteresAnual;
    }

    @Override
    public void girarMonto(Long monto) {
        if(getSaldo()>=monto){
            setSaldo(getSaldo() - monto);
            contadorGiros++;
            System.out.println("Giro realizado. Saldo actual: $" + getSaldo());
            System.out.println("Giros restantes para el interés anual: " + (limiteGirosAnuales - contadorGiros));
        }else{
            System.out.println("Fondos insuficientes para realizar el giro ❌");
        }
    }

    @Override
    public void depositarMonto (Long monto){
        setSaldo(getSaldo()+ monto);
        System.out.println("Depósito realizado. Saldo actual: $" + getSaldo());
    }

    // Implementación del nuevo metodo abstracto de clase Cuenta
    @Override
    public void mostrarDetalleCuenta() {
        System.out.println("--- DETALLE CUENTA DE AHORRO ---");
        System.out.println("Número de Cuenta: " + getNumeroCuenta());
        System.out.println("Saldo Actual    : $" + getSaldo());
        System.out.println("Giros Realizados: " + getContadorGiros() + " de " + getLimiteGirosAnuales());
        System.out.println("Tasa Interés Anual: " + (tasaInteresAnual * 100) + "%");
        System.out.println("--------------------------------");
    }

    @Override
    public void calcularInteres() {
        // Interés simple anual.
        // Se penaliza si se excede el límite de giros.
        if (contadorGiros <= limiteGirosAnuales) {
            this.interesGenerado = getSaldo() * tasaInteresAnual;
            System.out.println("Se ha calculado un interés de " + String.format("%.2f", interesGenerado) + " para su cuenta de ahorro.");
        } else {
            this.interesGenerado = 0.0;
            System.out.println("No se ha generado interés este período debido a que ha excedido el límite de giros anuales.");
        }
        // Se puede añadir el interés al saldo aquí o en otro metodo para aplicarlo.
        // setSaldo(getSaldo() + (long) interesGenerado);
    }
}