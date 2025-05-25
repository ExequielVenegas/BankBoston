package com.douc.bankboston;

import com.douc.bankboston.modelos.Banco;
import com.douc.bankboston.modelos.Cliente;

import static com.douc.bankboston.constantes.Constantes.LISTA_OPCIONES;
import static com.douc.bankboston.constantes.Constantes.OPCION_SALIR;
import static com.douc.bankboston.utilidades.GestorEntradaSalida.*;

public class Main {

    public static void main(String[] args) {
        Banco bankBoston = new Banco();
        String opcMenu = ""; //opcion de menu

        do {
            mostrarMenu();
            opcMenu = obtenerTextoDeScanner("Por favor ingrese el número de la acción que desea realizar: ");
            clearConsole();
            if (!LISTA_OPCIONES.contains(opcMenu)) {
                System.out.println("\n Opción inválida intente nuevamente");
            } else {
                aplicarTransacciones(opcMenu, bankBoston);
            }
        } while (!OPCION_SALIR.equalsIgnoreCase(opcMenu));

    }

    private static void mostrarMenu() {
        System.out.println("\n=========== BIENVENIDO/A AL SISTEMA DE GESTIÓN DE CUENTAS DE BANK BOSTON ============= \n");
        System.out.println("|______________________________ MENÚ PRINCIPAL _________________________|");
        System.out.println("|1. Registrar cliente                                                   |");
        System.out.println("|2. Ver datos cliente                                                   |");
        System.out.println("|3. Depositar                                                           |");
        System.out.println("|4. Girar                                                               |");
        System.out.println("|5. Consultar saldo                                                     |");
        System.out.println("|6. Salir.                                                              |");
        System.out.println("|-----------------------------------------------------------------------|");
    }

    private static void aplicarTransacciones(String opcMenu, Banco bankBoston) {
        switch (opcMenu) {
            case "1": // registrar cliente_____________________________________________________________________________
                registrarCliente(bankBoston, false);
                break;
            case "2":
                verDatosCliente(bankBoston);
                break;
            case "3": // depositar
                depositar(bankBoston);
                break;
            case "4": //girar
                girar(bankBoston);
                break;
            case "5": // consultar saldo
                consultarSaldo(bankBoston);
                break;
            case "6": // salir
                System.out.println("Saliendo del programa. Gracias por usar el sistema de gestión de BANK BOSTON");
                break;
            case "T": // prueba
                registrarCliente(bankBoston, true);
                break;
        }
    }

    private static void registrarCliente(Banco bankBoston, boolean esPrueba) {
        Cliente clienteNuevo = Cliente.crearCliente(esPrueba);
        bankBoston.agregarCliente(clienteNuevo);
    }

    private static void verDatosCliente(Banco bankBoston) {
        if (bankBoston.getClientes().isEmpty()) {
            System.out.println("No se ha registrado ningún cliente. Registre cliente en opción 1 del menú principal");
            return;
        } else {
            // pedir datos para buscar información de cliente
            String rutTarget = obtenerRutDeEntrada("Ingrese rut del cliente a buscar (incluya puntos y guión): ").toUpperCase();
            bankBoston.mostrarDatosCliente(rutTarget);
        }
    }

    private static void consultarSaldo(Banco bankBoston) {
        if (bankBoston.getCuentas().isEmpty()) {
            System.out.println("No se ha registrado ningúna cuenta. Registre cliente en opción 1 del menú principal");
            return;
        } else {
            Long cuentaTarget = 0L;
            do {
                cuentaTarget = obtenerNumeroCuentaEntrada("Ingrese número de cuenta a consultar saldo: ");
                bankBoston.mostrarSaldo(cuentaTarget);
            } while (cuentaTarget <= 0);
        }
    }

    private static void girar(Banco bankBoston) {
        if (bankBoston.getCuentas().isEmpty()) {
            System.out.println("No se ha registrado ningúna cuenta. Registre cliente en opción 1 del menú principal");
            return;
        } else {
            Long cuentaTarget = 0L;
            do {
                cuentaTarget = obtenerNumeroCuentaEntrada("Ingrese número de cuenta a girar: ");
                long monto = obtenerMontoEntrada("Ingrese monto a girar: ");
                bankBoston.girarMontoCuenta(cuentaTarget, monto);
            } while (cuentaTarget <= 0);
        }
    }

    private static void depositar(Banco bankBoston) {
        if (bankBoston.getCuentas().isEmpty()) {
            System.out.println("No se ha registrado ningúna cuenta. Registre cliente en opción 1 del menú principal");
            return;
        } else {
            Long cuentaTarget = 0L;
            do {
                cuentaTarget = obtenerNumeroCuentaEntrada("Ingrese número de cuenta a depositar: ");
                long monto = obtenerMontoEntrada("Ingrese monto a depositar: ");
                bankBoston.depositarMontoCuenta(cuentaTarget, monto);
            } while (cuentaTarget <= 0);
        }
    }
}
