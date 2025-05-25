package com.douc.bankboston.utilidades;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GestorEntradaSalida {
    private static Scanner scanner = new Scanner(System.in); // Crea un nuevo Scanner

    public static String obtenerTextoDeScanner(String mensaje) {
        String lineaLeida;
        System.out.print(mensaje); // Muestra el mensaje al usuario
        lineaLeida = scanner.nextLine().trim(); // Lee la línea completa
        return lineaLeida;
    }

    public static Long obtenerNumeroCuentaEntrada(String mensaje) {
        Long numeroCuenta = null;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print(mensaje);
            try {
                numeroCuenta = scanner.nextLong();
                scanner.nextLine(); // Limpiar el buffer: consume el resto de la línea, incluyendo el '\n'
                entradaValida = true; // Si llegamos aquí, la entrada es válida
            } catch (InputMismatchException e) {
                // Captura si el usuario no introduce un entero
                System.out.println("Entrada inválida. Por favor, introduce un número válido.");
                scanner.nextLine(); // Importante: Limpiar el buffer de la entrada incorrecta para evitar un bucle infinito
            } catch (Exception e) {
                // Captura cualquier otra excepción inesperada
                System.out.println("Ocurrió un error inesperado al leer la entrada: " + e.getMessage());
                scanner.nextLine(); // Limpiar el buffer por si acaso
            }
        }
        return numeroCuenta;
    }

    public static long obtenerMontoEntrada(String mensaje) {
        Long monto = null;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print(mensaje);
            try {
                monto = scanner.nextLong();
                scanner.nextLine(); // Limpiar el buffer: consume el resto de la línea, incluyendo el '\n'
                if (monto > 0) {
                    entradaValida = true; // Si llegamos aquí, la entrada es válida
                } else {
                    System.out.println("Entrada inválida. Por favor, introduce un monto válido.");
                }
            } catch (InputMismatchException e) {
                // Captura si el usuario no introduce un entero
                System.out.println("Entrada inválida. Por favor, introduce un monto válido.");
                scanner.nextLine(); // Importante: Limpiar el buffer de la entrada incorrecta para evitar un bucle infinito
            } catch (Exception e) {
                // Captura cualquier otra excepción inesperada
                System.out.println("Ocurrió un error inesperado al leer la entrada: " + e.getMessage());
                scanner.nextLine(); // Limpiar el buffer por si acaso
            }
        }
        return monto;
    }

    public static String obtenerRutDeEntrada(String mensaje) {
        String rut = "";
        do {
            rut = obtenerTextoDeScanner(mensaje);
            if (!validarFormatoRut(rut)) {
                System.out.println("Formato incorrecto. Ejemplo válido: 1.123.456-7");
            }
        } while (!validarFormatoRut(rut));
        return rut;
    }

    public static boolean validarFormatoRut(String rut) {
        return rut.matches("^\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]$");
    }

    public static String obtenerTextoDeEntrada(String mensaje) {
        String texto = "";
        do {
            texto = obtenerTextoDeScanner(mensaje);
        } while (isFieldBlank(texto));
        return texto;
    }

    public static boolean isFieldBlank(String field) {
        if (field.isBlank()) {
            System.out.println("Campo requerido, ingrese valor.");
            return true;
        }
        return false;
    }

    public static void clearConsole(Integer cantidadLineas) {
        for (int i = 0; i <= cantidadLineas; i++) {
            System.out.println();
        }
    }

    public static void clearConsole() {
        Integer cantidadLineas = 15;
        for (int i = 0; i <= cantidadLineas; i++) {
            System.out.println();
        }
    }
}
