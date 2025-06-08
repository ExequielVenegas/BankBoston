package com.douc.bankboston.modelos.cliente;

import com.douc.bankboston.modelos.cuenta.base.CuentaBancaria;
import com.douc.bankboston.modelos.cuenta.tipos.CuentaBancariaAhorro;
import com.douc.bankboston.modelos.cuenta.tipos.CuentaBancariaCorriente;
import com.douc.bankboston.modelos.cuenta.tipos.CuentaBancariaCredito;
import com.douc.bankboston.modelos.cuenta.tipos.CuentaBancariaDigital;

import static com.douc.bankboston.utilidades.GestorEntradaSalida.*;
import static com.douc.bankboston.constantes.Constantes.*;

// Implementamos la nueva interfaz
public class Cliente implements mostrarInformacionCliente {

    //atributos
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rut;
    private String domicilio;
    private String comuna;
    private String numeroTelefono;
    private Long numeroCuenta;
    private String tipoCuenta;
    private CuentaBancaria cuentaBancaria;


    // constructor
    private Cliente(String nombre, String apellidoPaterno, String apellidoMaterno,
                    String rut, String domicilio, String comuna,
                    String numeroTelefono, Long numeroCuenta, String tipoCuenta) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.rut = rut.toUpperCase();
        this.domicilio = domicilio;
        this.comuna = comuna;
        this.numeroTelefono = numeroTelefono;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta=tipoCuenta;
        this.cuentaBancaria = crearCuentaNueva(tipoCuenta,numeroCuenta);
    }

    // setters

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public void setNumeroCuenta(Long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setCuenta(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    // getters


    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getRut() {
        return rut;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getComuna() {
        return comuna;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public Long getNumeroCuenta() {
        return numeroCuenta;
    }

    public CuentaBancaria getCuenta() {
        return cuentaBancaria;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }
// métodos custom

    //metodo fabrica, es un metodo que devuelve un objeto de esta clase
    public static Cliente crearCliente() {
        String rutCliente = obtenerRutDeEntrada("Ingrese rut del cliente (con puntos y guión): ");
        String nombreCliente = obtenerTextoDeEntrada("Ingrese nombre del cliente: ");
        String apellidoPaternoCliente = obtenerTextoDeEntrada("Ingrese apellido paterno del cliente: ");
        String apellidoMaternoCliente = obtenerTextoDeEntrada("Ingrese apellido materno del cliente: ");
        String domicilioCliente = obtenerTextoDeEntrada("Ingrese domicilio del cliente: ");
        String comunaCliente = obtenerTextoDeEntrada("Ingrese comuna del cliente: ");
        String numeroTelefoCliente = obtenerTextoDeEntrada("Ingrese número de teléfono del cliente: ");
        Long numeroCuenta = obtenerNumeroCuentaEntrada("Ingrese número de cuenta a crear (9 dígitos): ");
        String tipoCuenta = obtenerTextoDeEntrada("Ingrese tipo de cuenta (corriente/ahorro/credito/digital): ").toUpperCase();

        return new Cliente(nombreCliente, apellidoPaternoCliente, apellidoMaternoCliente,
                rutCliente, domicilioCliente, comunaCliente, numeroTelefoCliente, numeroCuenta, tipoCuenta );
    }

    public static Cliente crearClientePrueba() {
        {
            return new Cliente("Nombre prueba", "Paterno prueba", "materno prueba",
                    "1.111.111-1", "Calle Prueba", "Comuna prueba", "987654321", 123456789L, "CORRIENTE");
        }
    }

    // Renombrar el metodo para implementar la interfaz y sea más genérico
    @Override
    public void mostrarInformacion() {
        System.out.println("--- DATOS DEL CLIENTE ---");
        System.out.println("Rut              : " + getRut().toUpperCase());
        System.out.println("Nombre           : " + getNombre().toUpperCase());
        System.out.println("Apellido Paterno : " + getApellidoPaterno().toUpperCase());
        System.out.println("Apellido Materno : " + getApellidoMaterno().toUpperCase());
        System.out.println("Domicilo         : " + getDomicilio().toUpperCase());
        System.out.println("Comuna           : " + getComuna().toUpperCase());
        System.out.println("Teléfono         : " + getNumeroTelefono());
        System.out.println("Número de cuenta : " + getNumeroCuenta());
        System.out.println("Tipo de cuenta   : " + getTipoCuenta());
        System.out.println("Saldo            : $ " + getCuenta().getSaldo());
        System.out.println("-------------------------");
    }

    public CuentaBancaria crearCuentaNueva (String tipoCuenta, Long numeroCuenta){

        switch (tipoCuenta){
            case OPCION_CUENTA_CORRIENTE:
                return new CuentaBancariaCorriente(numeroCuenta);
            case OPCION_CUENTA_AHORRO:
                return new CuentaBancariaAhorro(numeroCuenta);
            case OPCION_CUENTA_CREDITO:
                return new CuentaBancariaCredito(numeroCuenta);
            case OPCION_CUENTA_DIGITAL:
                return new CuentaBancariaDigital(numeroCuenta);

        }
        return null;
        //todo: manejar este return
    }
}