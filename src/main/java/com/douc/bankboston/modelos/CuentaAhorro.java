package com.douc.bankboston.modelos;

public class CuentaAhorro extends Cuenta{
    private int limiteGirosAnuales;
    private int contadorGiros;
    private double tasaInteresAnual;
    private double interesAnual;

    public CuentaAhorro(Long numeroCuenta) {
        super(numeroCuenta);
        this.limiteGirosAnuales = 7;  // solo tiene 7 giros anuales disponibles antes de perder intereses
        this.contadorGiros=0; // se inicializa en 0
        this.tasaInteresAnual=0.6;
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
        }else{
            System.out.println("fondos insuficientes para realizar el giro");
        }
    }

    public void depositarMonto (Long monto){
        setSaldo(getSaldo()+ monto);
    }
}
