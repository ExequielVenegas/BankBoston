package com.douc.bankboston.modelos.cuenta.tipos;

import com.douc.bankboston.modelos.cuenta.base.CuentaBancaria;

public class CuentaBancariaCorriente extends CuentaBancaria {

    Long limiteSobregiro;
    Long sobregiroActual; // cantidad de dinero en la que estoy sobregirado
    private double tasaInteresSobregiro = 0.05; // Tasa de interés/cargo por sobregiro (5%)
    private Long cargoPorInteresSobregiro = 0L; // Cargo generado por el sobregiro

    public CuentaBancariaCorriente(Long numeroCuenta) {
        super(numeroCuenta); // Llama al constructor de la clase padre
        this.limiteSobregiro = 50000L; // se crea con un limite de 50000 de sobregiro
        this.sobregiroActual = 0L; // se crea con 0 de sobregiro
    }

    // Constructor con saldo inicial (Ejemplo de sobrecarga en subclase)
    public CuentaBancariaCorriente(Long numeroCuenta, Long saldoInicial) {
        super(numeroCuenta, saldoInicial);
        this.limiteSobregiro = 50000L;
        this.sobregiroActual = 0L;
    }

    //getters and setters


    public Long getLimiteSobregiro() {
        return limiteSobregiro;
    }

    public Long getSobregiroActual() {
        return sobregiroActual;
    }

    public void setLimiteSobregiro(Long limiteSobregiro) {
        this.limiteSobregiro = limiteSobregiro;
    }

    public void setSobregiroActual(Long sobregiroActual) {
        this.sobregiroActual = sobregiroActual;
    }

    @Override
    public void girarMonto(Long monto) {
        Long saldoActual = getSaldo();
        // Calcular el total disponible incluyendo el límite de sobregiro restante
        Long disponibleTotal = saldoActual + (limiteSobregiro - sobregiroActual);

        if (monto > disponibleTotal) {
            System.out.println("Giro no realizado. Fondos insuficientes, límite de sobregiro excedido ❌");
        } else {
            if (saldoActual >= monto) {
                // Hay suficiente saldo
                setSaldo(saldoActual - monto);
                System.out.println("Giro exitoso. Nuevo saldo: $" + getSaldo());
            } else {
                // Necesita usar sobregiro
                Long montoASobregirar = monto - saldoActual;
                setSobregiroActual(sobregiroActual + montoASobregirar);
                setSaldo(0L); // El saldo queda en 0
                System.out.println("Giro realizado con sobregiro.");
                System.out.println("Sobregiro utilizado: $" + montoASobregirar);
                System.out.println("Sobregiro total actual: $" + getSobregiroActual());
                System.out.println("Sobregiro disponible: $" + (limiteSobregiro - getSobregiroActual()));
            }
        }
    }

    @Override
    public void depositarMonto (Long monto){
        if (monto <= 0) {
            System.out.println("Monto de depósito no válido ❌");
            return;
        }

        if (sobregiroActual > 0) {
            // Si hay sobregiro, primero se paga el sobregiro
            if (monto >= sobregiroActual) {
                Long remanente = monto - sobregiroActual;
                setSobregiroActual(0L);
                setSaldo(getSaldo() + remanente);
                System.out.println("Sobregiro pagado completamente. Nuevo saldo: $" + getSaldo());
            } else {
                setSobregiroActual(sobregiroActual - monto);
                System.out.println("Se han pagado $" + monto + " de su sobregiro. Sobregiro restante: $" + getSobregiroActual());
            }
        } else {
            // No hay sobregiro, solo se deposita al saldo
            setSaldo(getSaldo() + monto);
            System.out.println("Depósito realizado. Saldo actual: $" + getSaldo());
        }
    }

    // Implementación del nuevo metodo abstracto de cuenta
    @Override
    public void mostrarDetalleCuenta() {
        System.out.println("--- DETALLE CUENTA CORRIENTE ---");
        System.out.println("Número de Cuenta    : " + getNumeroCuenta());
        System.out.println("Saldo Actual        : $" + getSaldo());
        System.out.println("Límite de Sobregiro : $" + getLimiteSobregiro());
        System.out.println("Sobregiro Actual    : $" + getSobregiroActual());
        System.out.println("--------------------------------");
    }

    @Override
    public void calcularInteres() {
        if (sobregiroActual > 0) {
            // Si hay sobregiro, se calcula un cargo por interés
            this.cargoPorInteresSobregiro = (long) (sobregiroActual * tasaInteresSobregiro);
            System.out.println("Se ha generado un cargo por interés de sobregiro de " + cargoPorInteresSobregiro);
            // Se peude aplicar este cargo al sobregiro actual o descontarlo del próximo depósito
            // setSobregiroActual(getSobregiroActual() + cargoPorInteresSobregiro);
        } else {
            this.cargoPorInteresSobregiro = 0L;
            System.out.println("No hay sobregiro activo, no se genera cargo por interés.");
        }
    }
}