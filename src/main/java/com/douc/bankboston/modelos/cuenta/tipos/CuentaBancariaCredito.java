package com.douc.bankboston.modelos.cuenta.tipos;

import com.douc.bankboston.modelos.cuenta.base.CuentaBancaria;

public class CuentaBancariaCredito extends CuentaBancaria {
    private Long lineaCreditoTotal;
    private Long lineaCreditoUtilizada;
    private double tasaInteresCredito = 0.02; // Tasa de interés mensual (2%)
    private Long interesesGenerados = 0L; // Atributo para almacenar los intereses

    public CuentaBancariaCredito(Long numeroCuenta) {
        super(numeroCuenta, 0L); // Las cuentas de crédito no tienen un "saldo" tradicional, su "saldo" es la deuda. El saldo de la superclase puede representar 0 o deuda actual.
        this.lineaCreditoTotal = 200000L;
        this.lineaCreditoUtilizada = 0L;
        // En una cuenta de crédito, el "saldo" de la clase padre podría representar la deuda acumulada
        // o simplemente no usarse si solo se maneja con lineaCreditoUtilizada.
        // Para simplificar, mantendremos la deuda en lineaCreditoUtilizada y el saldo de la superclase en 0 o no lo usaremos directamente.
    }

    // Constructor con línea de crédito inicial específica (Ejemplo de sobrecarga en subclase)
    public CuentaBancariaCredito(Long numeroCuenta, Long lineaInicial) {
        super(numeroCuenta, 0L); // Asumimos 0 saldo inicial, la lógica es por línea de crédito
        this.lineaCreditoTotal = lineaInicial;
        this.lineaCreditoUtilizada = 0L;
    }

    //getters and setters

    public Long getLineaCreditoTotal() {
        return lineaCreditoTotal;
    }


    public Long getLineaCreditoUtilizada() {
        return lineaCreditoUtilizada;
    }


    public void setLineaCreditoTotal(Long lineaCreditoTotal) {
        this.lineaCreditoTotal = lineaCreditoTotal;
    }


    public void setLineaCreditoUtilizada(Long lineaCreditoUtilizada) {
        this.lineaCreditoUtilizada = lineaCreditoUtilizada;
    }

    public Long getLineaCreditoDisponible(){
        return lineaCreditoTotal- getLineaCreditoUtilizada();
    }

    @Override
    public void girarMonto(Long monto) {
        if (monto <= 0) {
            System.out.println("Monto a girar no válido ❌");
            return;
        }
        if(getLineaCreditoDisponible()>=monto){
            setLineaCreditoUtilizada(getLineaCreditoUtilizada() + monto);
            System.out.println("Se han girado: $" + monto + " de su línea de crédito.");
            System.out.println("Cupo utilizado: $" + getLineaCreditoUtilizada());
            System.out.println("Cupo disponible: $" + getLineaCreditoDisponible());
        } else {
            System.out.println("Giro no realizado. No tiene cupo suficiente ❌");
        }
    }

    @Override
    public void depositarMonto(Long monto) {
        if (monto <= 0) {
            System.out.println("Monto de pago no válido ❌");
            return;
        }

        if(lineaCreditoUtilizada==0) {
            System.out.println("No hay deuda activa en esta cuenta de crédito. No se requieren pagos.");
            return;
        }

        if(monto>lineaCreditoUtilizada) {
            System.out.println("El monto que está intentando pagar ($" + monto + ") excede la deuda actual ($" + lineaCreditoUtilizada + ").");
            System.out.println("Por favor, ingrese un monto menor o igual a la deuda restante.");
            return;
        }

        lineaCreditoUtilizada -= monto;
        System.out.println("Se han pagado: $" + monto + " a su cuenta de crédito.");
        System.out.println("Cupo actual disponible: $" + getLineaCreditoDisponible());
        System.out.println("Deuda restante: $" + lineaCreditoUtilizada);
    }

    // Implementación del nuevo metodo abstracto
    @Override
    public void mostrarDetalleCuenta() {
        System.out.println("--- DETALLE CUENTA DE CRÉDITO ---");
        System.out.println("Número de Cuenta       : " + getNumeroCuenta());
        System.out.println("Línea de Crédito Total : $" + getLineaCreditoTotal());
        System.out.println("Línea de Crédito Utilizada: $" + getLineaCreditoUtilizada());
        System.out.println("Línea de Crédito Disponible: $" + getLineaCreditoDisponible());
        System.out.println("---------------------------------");
    }

    @Override
    public void calcularInteres() {
        // El interés se calcula sobre la línea de crédito utilizada
        if (lineaCreditoUtilizada > 0) {
            this.interesesGenerados = (long) (lineaCreditoUtilizada * tasaInteresCredito);
            System.out.println("Se han generado intereses de " + interesesGenerados + " sobre la línea de crédito utilizada.");
            // Se puede añadir estos intereses a la línea de crédito utilizada,
            // lo que aumentaría la deuda si no se pagan.
            // setLineaCreditoUtilizada(getLineaCreditoUtilizada() + interesesGenerados);
        } else {
            this.interesesGenerados = 0L;
            System.out.println("No hay deuda activa en la línea de crédito, no se generan intereses.");
        }
    }
}