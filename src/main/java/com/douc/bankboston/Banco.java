package com.douc.bankboston;
import java.util.ArrayList;
import java.util.Scanner;

public class Banco {

    static int numeroCuenta = 123456789;

    private static ArrayList<Cliente> clientes; // si son privado hay que hacer setters y getters?
    private static ArrayList<Cuenta> cuentas;

    // constructor
    public Banco() {
        clientes = new ArrayList<Cliente>();
        cuentas = new ArrayList<Cuenta>();
    }

    public static ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public static ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    // métodos
    // no se si esto sea necesario pero se me ocurrió que podría ser util
    public static boolean isClienteExist(Cliente cliente) { // metodo para revisar si el cliente ya existe antes de crearlo
        boolean isExists = false;
        for (Cliente c : clientes) {
            if (c.getRut() == cliente.getRut()) {
                isExists = true;
            }
        }
        return isExists;
    }


    public void agregarCliente(Cliente cliente) { // suponiendo un sistema de un cliente = una cuenta
        if (isClienteExist(cliente)) {
            System.out.println("Cliente ya existe. No es posible registrarlo nuevamente");
        } else {
            clientes.add(cliente); // agrego al cliente a mi lista de clientes
            System.out.println("El cliente rut: " + cliente.getRut() + " ha sido registrado existosamente");
        }
    }

    public static boolean isCuentaExist(Cuenta cuenta) {
        boolean isExists = false;
        for (Cuenta cu : cuentas) {
            if (cu.getNumeroCuenta() == cuenta.getNumeroCuenta()) {
                isExists = true;
            }
        }
        return isExists;
    }


    public void agregarCuenta(Cuenta cuenta) {
        if (isCuentaExist(cuenta)) {
            System.out.println("Cuenta ya existe. No es posible crearla nuevamente");
        } else {
            cuentas.add(cuenta);
            System.out.println("La cuenta número: " + cuenta.getNumeroCuenta() + " ha sido creada existosamente");
        }
    }


    public static Cliente buscarClientePorRut(String rutTarget) {
        for (Cliente c : getClientes()) {
            if (c.getRut().equalsIgnoreCase(rutTarget)) {
                c.verDatosCliente();
                return c;
            }
        }
                System.out.println("Cliente no existe");
                return null;
    }


    public static boolean validarFormatoRut(String rut) {
        return rut.matches("^\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]$");
    }

    public static boolean isFieldBlank(String field){
        if(field.isBlank()){
            System.out.println("Campo requerido, ingrese valor");
            return true;
        }
        return false;
    }

    public static void registrarCliente() {
        Scanner scan = new Scanner(System.in);

        //datos a registrar
         String nombre;
         String apellidoPaterno;
         String apellidoMaterno;
         String rut;
         String domicilio;
         String comuna;
         String numeroTelefono;


        System.out.println("-----------------REGISTRO CLIENTE ---------------------");
        System.out.println("Para registrar cliente complete los siguientes campos: ");

        do {
            System.out.println("Ingrese RUT (con puntos y guión):");
            rut = scan.nextLine();
            if (!validarFormatoRut(rut)) {
                System.out.println("Formato incorrecto. Ejemplo válido: 1.123.456-7");
            }
        } while (!validarFormatoRut(rut));

        // esta fue la solucion que se me ocurrio pero creo que es muy repetitiva quiza
        do {
            System.out.println("Ingrese nombre:");
             nombre = scan.nextLine();
        }while(isFieldBlank(nombre));

        do {
            System.out.println("Ingrese apellido paterno:");
            apellidoPaterno = scan.nextLine();
        }while(isFieldBlank(apellidoPaterno));

        do {
            System.out.println("Ingrese apellido materno:");
            apellidoMaterno = scan.nextLine();
        }while(isFieldBlank(apellidoMaterno));

        do {
            System.out.println("ingrese domicilio");
            domicilio = scan.nextLine();
        } while (isFieldBlank(domicilio));

        do {
            System.out.println("Ingrese comuna:");
            comuna = scan.nextLine();
        }while(isFieldBlank(comuna));

        do {
            System.out.println("Ingrese número de teléfono");
            numeroTelefono = scan.nextLine();
        }while(isFieldBlank(numeroTelefono));

        System.out.println("Número de cuenta corriente: " + numeroCuenta);
        int saldo = 0; // saldo inicial

            Cliente nuevoCliente = new Cliente(rut, nombre, apellidoPaterno, apellidoMaterno, domicilio, comuna, numeroTelefono, numeroCuenta, saldo);
            clientes.add(nuevoCliente);
            System.out.println("¡Cliente registrado exitosamente!");
        }
    }
