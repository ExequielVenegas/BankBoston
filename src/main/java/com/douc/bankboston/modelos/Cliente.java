package com.douc.bankboston.modelos;

import static com.douc.bankboston.utilidades.GestorEntradaSalida.*;

public class Cliente {

    //atributos
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rut;
    private String domicilio;
    private String comuna;
    private String numeroTelefono;
    private int numeroCuenta;
    private Cuenta cuenta;


    // constructor
    private Cliente(String nombre, String apellidoPaterno, String apellidoMaterno,
                    String rut, String domicilio, String comuna,
                    String numeroTelefono, int numeroCuenta) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.rut = rut.toUpperCase();
        this.domicilio = domicilio;
        this.comuna = comuna;
        this.numeroTelefono = numeroTelefono;
        this.numeroCuenta = numeroCuenta;
        this.cuenta = new Cuenta(this.numeroCuenta);
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

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
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

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    // métodos custom

    //metodo fabrica, es un metodo que devuelve un objeto de esta clase
    public static Cliente crearCliente() {
        System.out.println("---------------- REGISTRO DE CLIENTE-------------------");
        String rutCliente = obtenerRutDeEntrada("Ingrese rut del cliente (con puntos y guión): ");
        String nombreCliente = obtenerTextoDeEntrada("Ingrese nombre del cliente: ");
        String apellidoPaternoCliente = obtenerTextoDeEntrada("Ingrese apellido paterno del cliente: ");
        String apellidoMaternoCliente = obtenerTextoDeEntrada("Ingrese apellido materno del cliente: ");
        String domicilioCliente = obtenerTextoDeEntrada("Ingrese domicilio del cliente: ");
        String comunaCliente = obtenerTextoDeEntrada("Ingrese comuna del cliente: ");
        String numeroTelefoCliente = obtenerTextoDeEntrada("Ingrese número de telefono del cliente: ");
        Integer numeroCuenta = obtenerNumeroCuentaEntrada("Ingrese número de cuenta corriente: ");

        return new Cliente(nombreCliente, apellidoPaternoCliente, apellidoMaternoCliente,
                rutCliente, domicilioCliente, comunaCliente, numeroTelefoCliente, numeroCuenta);
    }

    public void mostrarDatos() {
        System.out.println("---------------- DATOS DE CLIENTE-------------------");
        System.out.println("Rut              : " + getRut());
        System.out.println("Nombre           : " + getNombre());
        System.out.println("Apellido Paterno : " + getApellidoPaterno());
        System.out.println("Apellido Materno : " + getApellidoMaterno());
        System.out.println("Domicilo         : " + getDomicilio());
        System.out.println("Comuna           : " + getComuna());
        System.out.println("Teléfono         : " + getNumeroTelefono());
        System.out.println("Número de cuenta : " + getNumeroCuenta());
        System.out.println("Saldo            : " + getCuenta().getSaldo());
        System.out.println("---------------------------------------------------");
    }
}
