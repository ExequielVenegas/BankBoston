package com.douc.bankboston;

import java.util.Scanner;
import static com.douc.bankboston.Banco.getClientes;

public class Cliente {

    //atributos
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String rut;
    private String domicilio;
    private String comuna;
    private String numeroTelefono;
    private int numeroCuenta= 123456789; // primera cuenta correlativa
    private Cuenta cuenta;

    // constructor
    public Cliente(String nombre, String apellidoPaterno, String apellidoMaterno, String rut, String domicilio, String comuna, String numeroTelefono, int numeroCuenta, int saldo) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.rut = rut;
        this.domicilio = domicilio;
        this.comuna = comuna;
        this.numeroTelefono = numeroTelefono;
        this.cuenta = new Cuenta(numeroCuenta,saldo); // crear cuenta
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

    // métodos custom

    public void verDatosCliente (){
        System.out.println("---------------- DATOS DE CLIENTE-------------------");
        System.out.println("Número cuenta   : " + getNumeroCuenta());
        System.out.println("Nombre          : " + getNombre() + " " +  getApellidoPaterno() + " " +  getApellidoMaterno());
        System.out.println("Rut             : " + getRut());
        System.out.println("Domicilo        : " +  getDomicilio());
        System.out.println("Comuna          : " + getComuna());
        System.out.println("Teléfono        : " + getNumeroTelefono());
        System.out.println("---------------------------------------------------");
    }

}
