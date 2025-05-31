package com.douc.bankboston.modelos;

public class CuentaAhorro extends Cuenta{
    private int limiteGirosAnuales;
    private int contadorGiros;
    private double tasaInteresAnual;
    private double interesAnual;

    public CuentaAhorro(Long numeroCuenta) {
        super(numeroCuenta); // Llama al constructor de la clase padre
        this.limiteGirosAnuales=7; // solo tiene 7 giros anuales disponibles antes de perder intereses
        this.contadorGiros=0; // se inicializa en 0
        this.tasaInteresAnual=0.6;
    }

    // Constructor con saldo inicial (Ejemplo de sobrecarga en subclase, aunque ya hay en la superclase) sin usar.
    public CuentaAhorro(Long numeroCuenta, Long saldoInicial) {
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

    // Implementación del nuevo método abstracto de clase Cuenta
    @Override
    public void mostrarDetalleCuenta() {
        System.out.println("--- DETALLE CUENTA DE AHORRO ---");
        System.out.println("Número de Cuenta: " + getNumeroCuenta());
        System.out.println("Saldo Actual    : $" + getSaldo());
        System.out.println("Giros Realizados: " + getContadorGiros() + " de " + getLimiteGirosAnuales());
        System.out.println("Tasa Interés Anual: " + (tasaInteresAnual * 100) + "%");
        System.out.println("--------------------------------");
    }
}