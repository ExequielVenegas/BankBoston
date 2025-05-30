package com.douc.bankboston.modelos;

public class CuentaCorriente extends Cuenta {

    Long limiteSobregiro;
    Long sobregiroActual; // cantidad de dinero en la que estoy sobregirado

    public CuentaCorriente(Long numeroCuenta, Long sobregiroActual) {
        super(numeroCuenta);
        this.limiteSobregiro = 50000L; // se crea con un limite de 50000 de sobregiro
        this.sobregiroActual = 0L; // se crea con 0 de sobregiro
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

    //todo: no se como incorporar esta logica al metodo girar en banco
    @Override
    public void girarMonto(Long monto) {
        Long saldoActual = getSaldo();
        // si el saldo es suficiente
        if(saldoActual>=monto){
            setSaldo(saldoActual-monto); // establecer el valor de saldo a saldo-monto
            System.out.println("Giro exitoso. Nuevo saldo: $" + getSaldo()); // obtener el saldo nuevo
        }else{
            //si no hay saldo suficiente
            Long disponible = saldoActual + (limiteSobregiro-sobregiroActual); // almacena el total de lo que se puede girar
            if(disponible>= monto){
                setSobregiroActual(sobregiroActual + (monto -saldoActual)); // cuanto estoy sobregirado y acumula el valor anterior de sobregiro
                setSaldo(0L);
                Long sobregiroDisponible = limiteSobregiro-sobregiroActual;
                System.out.println("Operación realizada con sobregiro.");
                System.out.println("Sobregiro utilizado: $ " + sobregiroActual);
                System.out.println("Sobregiro disponible: $ " + sobregiroDisponible);
            }else{
                System.out.println("Fondos insuficientes. Limite de sobregiro excedido.");
            }
        }

    }
    // método depositar se mantiene igual.
}


