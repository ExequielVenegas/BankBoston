package com.douc.bankboston.modelos;

public class CuentaCredito extends Cuenta{
    private Long lineaCreditoTotal;
    private Long lineaCreditoUtilizada;

    public CuentaCredito(Long numeroCuenta) {
        super(numeroCuenta);
        this.lineaCreditoTotal = 200000L; // credito inicial
        this.lineaCreditoUtilizada = 0L; // al momento de abrir la cuenta
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
        if(getLineaCreditoDisponible()>=monto){
            setLineaCreditoUtilizada(getLineaCreditoUtilizada() + monto); // aumento la linea de credito utilizada
            System.out.println("Se han girado: $ " + monto + " de su línea de crédito.");
            System.out.println("Cupo utilizado: $ " + getLineaCreditoUtilizada());
            System.out.println("Cupo disponible: $ " + getLineaCreditoDisponible());
        }else{
            System.out.println("giro no realizado. no tiene cupo suficiente.");
        }
    }

    @Override
    public void depositarMonto(Long monto) {
        if(lineaCreditoUtilizada==0) {
            System.out.println("No hay deuda, no se permiten pagos ni depósitos");
            return;
        }

        if(monto>lineaCreditoUtilizada) {
            System.out.println("El monto que está intentando pagar excede al cupo utilizado.");
            System.out.println("Solo puede pagar hasta: $" + lineaCreditoUtilizada);
            return;
        }

        lineaCreditoUtilizada -= monto;
            System.out.println("Se han pagado: $ " + monto + " a su cuenta de crédito.");
            System.out.println("Cupo actual: $ " + getLineaCreditoDisponible());
            System.out.println("Deuda restante: $ " + lineaCreditoUtilizada);
        }

}
