package com.douc.bankboston.modelos;

import java.util.HashMap;

import static com.douc.bankboston.constantes.Constantes.NOMBRE_BANCO;

public class Banco {

    private String nombreBanco;
    private HashMap<String, Cliente> clientes;
    private HashMap<Integer, Cuenta> cuentas;
    private HashMap<Integer, String> relacionCuentaCliente;

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

    public HashMap<Integer, Cuenta> getCuentas() {
        return cuentas;
    }

    private void setCuentas(HashMap<Integer, Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public HashMap<Integer, String> getRelacionCuentaCliente() {
        return relacionCuentaCliente;
    }

    private void setRelacionCuentaCliente(HashMap<Integer, String> relacionCuentaCliente) {
        this.relacionCuentaCliente = relacionCuentaCliente;
    }


    // métodos

    public void agregarCliente(Cliente cliente) { // suponiendo un sistema de un cliente = una cuenta
        if (verificarClienteExiste(cliente.getRut())) {
            System.out.println("Cliente ya existe. No es posible registrarlo nuevamente");
        } else {
            if (verificarCuentaExiste(cliente.getCuenta().getNumeroCuenta())) {
                System.out.println("Cuenta ya existe. No es posible registrarla nuevamente");
            } else {
                clientes.put(cliente.getRut(), cliente); // agrego al cliente a mi mapa de clientes
                cuentas.put(cliente.getNumeroCuenta(), cliente.getCuenta()); // agrego la cuenta a mi mape de cuentas
                relacionCuentaCliente.put(cliente.getNumeroCuenta(), cliente.getRut()); // agrego la relacion de cuenta y cliente
                System.out.println("El cliente rut: " + cliente.getRut() + " ha sido registrado existosamente");
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

    public void depositarMontoCuenta(Integer numeroCuenta, long monto) {
        if (verificarCuentaExiste(numeroCuenta)) {
            if (monto > 0) {
                cuentas.get(numeroCuenta).depositarMonto(monto);
                System.out.println("¡Depósito realizado de manera exitosa!");
                System.out.println("Usted tiene un saldo actual de " + cuentas.get(numeroCuenta).getSaldo() + " pesos");
            } else {
                System.out.println("Monto no valido, no se ha realizado transaccion.");
            }
        } else {
            System.out.println("No se ha encontrado cuenta con ese número.");
        }
    }

    public void girarMontoCuenta(Integer numeroCuenta, long monto) {
        if (verificarCuentaExiste(numeroCuenta)) {
            if (monto > 0) {
                long saldoCalculado = cuentas.get(numeroCuenta).getSaldo() - monto;
                if (saldoCalculado >= 0) {
                    cuentas.get(numeroCuenta).girarMonto(monto);
                    System.out.println("¡Giro realizado de manera exitosa!");
                    System.out.println("Usted tiene un saldo actual de " + cuentas.get(numeroCuenta).getSaldo() + " pesos");
                } else {
                    System.out.println("Saldo insuficiente, no se ha realizado transaccion.");
                }
            } else {
                System.out.println("Monto no valido, no se ha realizado transaccion.");
            }
        } else {
            System.out.println("No se ha encontrado cuenta con ese número.");
        }
    }

    public void mostrarSaldo(Integer numeroCuenta) {
        if (verificarCuentaExiste(numeroCuenta)) {
            System.out.println("Saldo actual: " + cuentas.get(numeroCuenta).getSaldo());
        } else {
            System.out.println("No se ha encontrado cuenta con ese número.");
        }
    }



    /*
    public static boolean validarRutCliente(String rutTarget) { // verificar si rut existe asociado a un cliente
        for (Cliente c : getClientes()) {
            if (c.getRut().equalsIgnoreCase(rutTarget)) {
                return true;
            }
        }
        System.out.println("Este rut no está asociado a un cliente de este banco.");
        return false;
    }
     */

    /*
    public static Cliente buscarCliente(Cliente cliente, String rutTarget){
        if(validarRutCliente(rutTarget)){
            cliente.verDatosCliente();
            return cliente;
        }else{
            System.out.println("Cliente no existe");
            return
        }
    }
     */

}