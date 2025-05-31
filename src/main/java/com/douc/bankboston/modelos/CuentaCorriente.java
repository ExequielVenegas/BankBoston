package com.douc.bankboston.modelos;

public class CuentaCorriente extends Cuenta {

    Long limiteSobregiro;
    Long sobregiroActual; // cantidad de dinero en la que estoy sobregirado

    public CuentaCorriente(Long numeroCuenta) {
        super(numeroCuenta); // Llama al constructor de la clase padre
        this.limiteSobregiro = 50000L; // se crea con un limite de 50000 de sobregiro
        this.sobregiroActual = 0L; // se crea con 0 de sobregiro
    }

    // Constructor con saldo inicial (Ejemplo de sobrecarga en subclase)
    public CuentaCorriente(Long numeroCuenta, Long saldoInicial) {
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

    // Implementación del nuevo método abstracto de cuenta
    @Override
    public void mostrarDetalleCuenta() {
        System.out.println("--- DETALLE CUENTA CORRIENTE ---");
        System.out.println("Número de Cuenta    : " + getNumeroCuenta());
        System.out.println("Saldo Actual        : $" + getSaldo());
        System.out.println("Límite de Sobregiro : $" + getLimiteSobregiro());
        System.out.println("Sobregiro Actual    : $" + getSobregiroActual());
        System.out.println("--------------------------------");
    }
}