package com.douc.bankboston;

import com.douc.bankboston.modelos.Banco;
import com.douc.bankboston.modelos.Cliente;
import com.douc.bankboston.modelos.MostrarInformacion; // Importamos la interfaz si queremos usarla directamente en Main

import static com.douc.bankboston.constantes.Constantes.*;
import static com.douc.bankboston.utilidades.GestorEntradaSalida.*;

public class Main {

    public static void main(String[] args) {
        Banco bankBoston = new Banco();
        String opcMenu = ""; //opcion de menu

        do {
            mostrarMenu();
            opcMenu = obtenerTextoDeScanner("Por favor ingrese el número de la acción que desea realizar:\n ");
            clearConsole();
            if (!LISTA_OPCIONES.contains(opcMenu)) {
                System.out.println("\n Opción inválida intente nuevamente");
            } else {
                aplicarTransacciones(opcMenu, bankBoston);
            }
        } while (!OPCION_SALIR.equalsIgnoreCase(opcMenu));

    }

    private static void mostrarMenu() {
        System.out.println("=========== 🏦 BIENVENIDO/A AL SISTEMA DE GESTIÓN DE CUENTAS DE BANK BOSTON ============");
        mostrarEncabezado(MENU_PRINCIPAL);
        System.out.println("|           1. Registrar cliente                                                        |");
        System.out.println("|           2. Ver datos cliente                                                        |");
        System.out.println("|           3. Depositar                                                                |");
        System.out.println("|           4. Girar                                                                    |");
        System.out.println("|           5. Consultar saldo                                                          |");
        System.out.println("|           6. Ver datos cuenta                                                         |");
        System.out.println("|           7. Salir                                                                    |");
        System.out.println("|_______________________________________________________________________________________|");
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
            case "6": // consultar cuenta
                verDatosCuenta(bankBoston);
                break;
            case "7": // salir
                System.out.println("Saliendo del programa. Gracias por usar el sistema de gestión de BANK BOSTON 🏦");
                break;
            case "T": // prueba
                registrarCliente(bankBoston, true);
                break;
        }
    }

    private static void registrarCliente(Banco bankBoston, boolean esPrueba) {
        mostrarEncabezado(MENU_REGISTRAR_CLIENTE);
        Cliente clienteNuevo = Cliente.crearCliente(esPrueba);
        bankBoston.agregarCliente(clienteNuevo);
        clearConsole();
    }

    private static void verDatosCliente(Banco bankBoston) {
        mostrarEncabezado(MENU_DATOS_CLIENTE);
        if (bankBoston.getClientes().isEmpty()) {
            System.out.println("No se ha registrado ningún cliente. Registre cliente en opción 1 del menú principal");
            clearConsole();
            return;
        } else {
            String rutTarget = obtenerRutDeEntrada("Ingrese rut del cliente a buscar (incluya puntos y guión): ").toUpperCase();
            bankBoston.mostrarDatosCliente(rutTarget);
        }
        clearConsole();
    }

    private static void verDatosCuenta(Banco bankBoston) {
        mostrarEncabezado(MENU_DATOS_CUENTA); // Usamos la nueva constante para el encabezado
        if (bankBoston.getCuentas().isEmpty()) {
            System.out.println("No se ha registrado ninguna cuenta. Registre cliente en opción 1 del menú principal");
            clearConsole();
            return;
        } else {
            Long cuentaTarget = obtenerNumeroCuentaEntrada("Ingrese el número de cuenta para ver los detalles: ");
            if (bankBoston.verificarCuentaExiste(cuentaTarget)) {
                bankBoston.getCuentas().get(cuentaTarget).mostrarDetalleCuenta(); // Llama al método polimórfico
            } else {
                System.out.println("No se ha encontrado cuenta con ese número.");
            }
        }
        clearConsole();
    }

    private static void consultarSaldo(Banco bankBoston) {
        mostrarEncabezado(MENU_CONSULTAR_SALDO);
        if (bankBoston.getCuentas().isEmpty()) {
            System.out.println("No se ha registrado ningúna cuenta. Registre cliente en opción 1 del menú principal");
            clearConsole();
            return;
        } else {
            Long cuentaTarget = 0L;
            do {
                cuentaTarget = obtenerNumeroCuentaEntrada("Ingrese número de cuenta a consultar saldo: ");
                bankBoston.mostrarSaldo(cuentaTarget);
            } while (cuentaTarget <= 0);
        }
        clearConsole();
    }

    private static void girar(Banco bankBoston) {
        mostrarEncabezado(MENU_GIRAR);
        if (bankBoston.getCuentas().isEmpty()) {
            System.out.println("No se ha registrado ningúna cuenta. Registre cliente en opción 1 del menú principal");
            clearConsole();
            return;
        } else {
            Long cuentaTarget = 0L;
            do {
                cuentaTarget = obtenerNumeroCuentaEntrada("Ingrese número de cuenta a girar: ");
                long monto = obtenerMontoEntrada("Ingrese monto a girar: ");
                bankBoston.girarMontoCuenta(cuentaTarget, monto);
            } while (cuentaTarget <= 0);
        }
        clearConsole();
    }

    private static void depositar(Banco bankBoston) {
        mostrarEncabezado(MENU_DEPOSITAR);
        if (bankBoston.getCuentas().isEmpty()) {
            System.out.println("No se ha registrado ningúna cuenta. Registre cliente en opción 1 del menú principal");
            clearConsole();
            return;
        } else {
            Long cuentaTarget = 0L;
            do {
                cuentaTarget = obtenerNumeroCuentaEntrada("Ingrese número de cuenta a depositar: ");
                long monto = obtenerMontoEntrada("Ingrese monto a depositar: ");
                bankBoston.depositarMontoCuenta(cuentaTarget, monto);
            } while (cuentaTarget <= 0);
        }
        clearConsole();
    }
    private static void mostrarEncabezado (String tipoMenu){
        System.out.println("_________________________________________________________________________________________");
        System.out.println("                                 📚 MENÚ: " + tipoMenu );
        System.out.println("_________________________________________________________________________________________");
    }
}