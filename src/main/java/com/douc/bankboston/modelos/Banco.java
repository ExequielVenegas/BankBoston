package com.douc.bankboston.modelos;

import java.util.HashMap;

import static com.douc.bankboston.constantes.Constantes.NOMBRE_BANCO;
import static com.douc.bankboston.modelos.CuentaAhorro.*;


public class Banco {

    private String nombreBanco;
    private HashMap<String, Cliente> clientes;
    private HashMap<Long, Cuenta> cuentas;
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

    public HashMap<Long, Cuenta> getCuentas() {
        return cuentas;
    }

    private void setCuentas(HashMap<Long, Cuenta> cuentas) {
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

    public boolean verificarClienteExiste(String rutCliente) { // metodo para revisar si el cliente ya existe antes de crearlo
        boolean existeCliente = false;
        if (clientes.containsKey(rutCliente)) {
            existeCliente = true;
        }
        return existeCliente;
    }

    public boolean verificarCuentaExiste(long numeroCuenta) { // metodo para revisar la cuenta ya existe antes de crearlo
        boolean existeCuenta = false;
        if (cuentas.containsKey(numeroCuenta)) {
            existeCuenta = true;
        }
        return existeCuenta;
    }

    public void mostrarDatosCliente(String rutCliente) {
        if (verificarClienteExiste(rutCliente)) {
            clientes.get(rutCliente).mostrarDatos();
        } else {
            System.out.println("No se ha encontrado cliente con ese rut.");
        }
    }

    public void depositarMontoCuenta(Long numeroCuenta, long monto) {
        if (verificarCuentaExiste(numeroCuenta)) {
            if (monto > 0) {
                cuentas.get(numeroCuenta).depositarMonto(monto);
                System.out.println("¡Depósito realizado de manera exitosa! ✅");
                System.out.println("Usted tiene un saldo actual de: $ " + cuentas.get(numeroCuenta).getSaldo() + " CLP");
            } else {
                System.out.println("Monto no valido, no se ha realizado transacción. ❌");
            }
        } else {
            System.out.println("No se ha encontrado cuenta con ese número.");
        }
    }

    //todo: sería buena idea implementar girar y depositar como interfaces?
    public void girarMontoCuenta(Long numeroCuenta, long monto) {
        if (verificarCuentaExiste(numeroCuenta)) {
            if (monto > 0) {
                long saldoCalculado = cuentas.get(numeroCuenta).getSaldo() - monto;
                if (saldoCalculado >= 0) {
                    cuentas.get(numeroCuenta).girarMonto(monto);
                    System.out.println("¡Giro realizado de manera exitosa!✅");
                    System.out.println("Usted tiene un saldo actual de: $" + cuentas.get(numeroCuenta).getSaldo() + " CLP");
                } else {
                    System.out.println("Saldo insuficiente, no se ha realizado transacción. ❌");
                }
            } else {
                System.out.println("Monto no valido, no se ha realizado transacción.");
            }
        } else {
            System.out.println("No se ha encontrado cuenta con ese número.");
        }
    }

    public void mostrarSaldo(Long numeroCuenta) {
        if (verificarCuentaExiste(numeroCuenta)) {
            System.out.println("Saldo actual: $" + cuentas.get(numeroCuenta).getSaldo() + " CLP");
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