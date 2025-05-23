package com.douc.bankboston;
import javax.xml.transform.Source;
import java.util.ArrayList;

public class Banco {

    private static ArrayList<Cliente> clientes; // si son privado hay que hacer setters y getters?
    private static ArrayList<Cuenta> cuentas;

    // constructor
    public Banco (){
        clientes= new ArrayList<Cliente>();
        cuentas= new ArrayList<Cuenta>();
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    // métodos
    // no se si esto sea necesario pero se me ocurrió que podría ser util

    public static boolean isClienteExist(Cliente cliente){ // metodo para revisar si el cliente ya existe antes de crearlo
        boolean isExists= false;
        for (Cliente c : clientes){
            if(c.getRut()==cliente.getRut()){
                isExists=true;
            }
        }
        return isExists;
    }


    public void agregarCliente (Cliente cliente){ // suponiendo un sistema de un cliente = una cuenta
        if(isClienteExist(cliente)){
            System.out.println("Cliente ya existe. No es posible registrarlo nuevamente");
        }else{
            clientes.add(cliente); // agrego al cliente a mi lista de clientes
            System.out.println("El cliente rut: " + cliente.getRut() + " ha sido registrado existosamente");
        }
    }

    public static boolean isCuentaExist(Cuenta cuenta){
        boolean isExists= false;
        for (Cuenta cu : cuentas){
            if(cu.getNumeroCuenta()==cuenta.getNumeroCuenta()){
                isExists=true;
            }
        }
        return isExists;
    }


    public void agregarCuenta (Cuenta cuenta){
        if(isCuentaExist(cuenta)){
            System.out.println("Cuenta ya existe. No es posible crearla nuevamente");
        }else{
            cuentas.add(cuenta);
            System.out.println("La cuenta número: " + cuenta.getNumeroCuenta() + " ha sido creada existosamente");
        }
    }

    public static boolean validarRutCliente (String rutTarget) { // verificar si rut existe asociado a un cliente
        for(Cliente c : getClientes()){
        if (c.getRut().equalsIgnoreCase(rutTarget)) {
            return true;
        }
        }
            System.out.println("Este rut no está asociado a un cliente de este banco.");
            return false;
    }

    public static Cliente buscarCliente(Cliente cliente, String rutTarget){
        if(validarRutCliente(rutTarget)){
            cliente.verDatosCliente();
            return cliente;
        }else{
            System.out.println("Cliente no existe");
            return ";
        }
    }


    }
