package com.douc.bankboston.modelos.banco;

import com.douc.bankboston.modelos.cliente.Cliente;
import com.douc.bankboston.modelos.cuenta.base.CuentaBancaria;

import java.util.HashMap;

import static com.douc.bankboston.constantes.Constantes.NOMBRE_BANCO;

public class Banco {

    private String nombreBanco;
    private HashMap<String, Cliente> clientes;
    private HashMap<Long, CuentaBancaria> cuentas;
    private HashMap<Long, String> relacionCuentaCliente;

    // constructor
    public Banco() {
        this.nombreBanco = NOMBRE_BANCO;
        this.clientes = new HashMap<>();
        this.cuentas = new HashMap<>();
        this.relacionCuentaCliente = new HashMap<>();
    }

    public Banco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
        this.clientes = new HashMap<>();
        this.cuentas = new HashMap<>();
        this.relacionCuentaCliente = new HashMap<>();
    }

    // metodos getter y setter
    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    private void setClientes(HashMap<String, Cliente> clientes) {
        this.clientes = clientes;
    }

    public HashMap<Long, CuentaBancaria> getCuentas() {
        return cuentas;
    }

    private void setCuentas(HashMap<Long, CuentaBancaria> cuentas) {
        this.cuentas = cuentas;
    }

    public HashMap<Long, String> getRelacionCuentaCliente() {
        return relacionCuentaCliente;
    }

    private void setRelacionCuentaCliente(HashMap<Long, String> relacionCuentaCliente) {
        this.relacionCuentaCliente = relacionCuentaCliente;
    }


    // métodos

    public void agregarCliente(Cliente cliente) { // suponiendo un sistema de un cliente = una cuenta
        if (verificarClienteExiste(cliente.getRut())) {
            System.out.println("    Cliente ya existe. No es posible registrarlo nuevamente ❌");
        } else {
            if (verificarCuentaExiste(cliente.getCuenta().getNumeroCuenta())) {
                System.out.println("    Cuenta ya existe. No es posible registrarla nuevamente ❌");
            } else {
                clientes.put(cliente.getRut(), cliente); // agrego al cliente a mi mapa de clientes
                cuentas.put(cliente.getNumeroCuenta(), cliente.getCuenta()); // agrego la cuenta a mi mape de cuentas
                relacionCuentaCliente.put(cliente.getNumeroCuenta(), cliente.getRut()); // agrego la relacion de cuenta y cliente
                System.out.println("_________________________________________________________________________________________");
                System.out.println("    El cliente rut: " + cliente.getRut() + " ha sido registrado existosamente ✅");
                System.out.println("_________________________________________________________________________________________");

            }
        }
    }

    public boolean verificarClienteExiste(String rutCliente) {
        boolean existeCliente = false;
        if (clientes.containsKey(rutCliente)) {
            existeCliente = true;
        }
        return existeCliente;
    }

    public boolean verificarCuentaExiste(long numeroCuenta) {
        boolean existeCuenta = false;
        if (cuentas.containsKey(numeroCuenta)) {
            existeCuenta = true;
        }
        return existeCuenta;
    }

    public void mostrarDatosCliente(String rutCliente) {
        if (verificarClienteExiste(rutCliente)) {
            // Usamos el metodo de la interfaz que Cliente implementa
            clientes.get(rutCliente).mostrarInformacion();
            // Y llamamos al nuevo metodo abstracto de Cuenta para mostrar los detalles específicos
            clientes.get(rutCliente).getCuenta().mostrarDetalleCuenta();
        } else {
            System.out.println("No se ha encontrado cliente con ese rut.");
        }
    }

    public void depositarMontoCuenta(Long numeroCuenta, long monto) {
        if (verificarCuentaExiste(numeroCuenta)) {
            if (monto > 0) {
                cuentas.get(numeroCuenta).depositarMonto(monto);
            } else {
                System.out.println("Monto no valido, no se ha realizado transacción. ❌");
            }
        } else {
            System.out.println("No se ha encontrado cuenta con ese número.");
        }
    }

    public void girarMontoCuenta(Long numeroCuenta, long monto) {
        if (verificarCuentaExiste(numeroCuenta)) {
            if (monto > 0) {
                // La lógica de saldo insuficiente ahora se maneja dentro de cada girarMonto de la subclase
                cuentas.get(numeroCuenta).girarMonto(monto);
            } else {
                System.out.println("Monto no valido, no se ha realizado transacción. ❌");
            }
        } else {
            System.out.println("No se ha encontrado cuenta con ese número.");
        }
    }

    public void mostrarSaldo(Long numeroCuenta) {
        if (verificarCuentaExiste(numeroCuenta)) {
            System.out.println("Saldo actual: $" + cuentas.get(numeroCuenta).getSaldo());
        } else {
            System.out.println("No se ha encontrado cuenta con ese número.");
        }
    }

    //todo: pense que podria ser util a futuro
    /*
    public void aplicarInteresAnual () {
        if (ContadorGiros <= limiteGirosAnuales) { // conserva el interés
            interesAnual = (getSaldo() * tasaInteresAnual);
            setSaldo((long) (getSaldo() + interesAnual));
            System.out.println("Su cuenta de ahorro ha incorporado un interés anual de: " + tasaInteresAnual);
            System.out.println("Saldo actual: $" + getSaldo());
        } else {
            System.out.println("Ha realizado mas de 7 giros en el periodo. Su cuenta de ahorro no ha registrado intereses.");
            System.out.println("Saldo actual: $" + getSaldo());
        }
    }
     */
}