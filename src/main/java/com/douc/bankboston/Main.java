package com.douc.bankboston;
import java.util.Scanner;

import static com.douc.bankboston.Banco.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);

        int opcMenu=0; //opcion de menu

        do {
            System.out.println("\n=========== BIENVENIDO/A AL SISTEMA DE GESTIÓN DE CUENTAS DE BANK BOSTON ============= \n");
            System.out.println("|______________________________ MENÚ PRINCIPAL _________________________|");
            System.out.println("|1. Registrar cliente                                                   |");
            System.out.println("|2. Ver datos cliente                                                   |");
            System.out.println("|3. Depositar                                                           |");
            System.out.println("|4. Girar                                                               |");
            System.out.println("|5. Consultar saldo                                                     |");
            System.out.println("|6. Salir.                                                              |");
            System.out.println("|-----------------------------------------------------------------------|");
            System.out.println("Por favor ingrese el número de la acción que desea realizar: ");
            opcMenu=scan.nextInt();
            scan.nextLine();

            if(opcMenu<1 || opcMenu>6){
                System.out.println("\n Opción inválida intente nuevamente");
            }
        }while(opcMenu<1 || opcMenu>6);

        switch (opcMenu){
            case 1: // registrar cliente_____________________________________________________________________________
                // aqui aun no tengo claro como implementarlo
                Cliente cliente1 = new Cliente("Francis", "Álvarez", "Figueroa", "12.222.333-1","AV. Siempreviva 123", "Santiago Centro","123123222");

            case 2: // ver datos cliente_____________________________________________________________________________

                // revisar que haya registros de clientes
                if(getClientes().isEmpty()){
                    System.out.println("No ha registrado ningún cliente. Registre cliente en opción 1 del menú principal");
                    break;
                }else{
                    // pedir datos para buscar información de cliente
                    String rutTarget = "";
                    do {
                        System.out.println("Ingrese rut del cliente a buscar (incluya puntos y guión): ");
                        rutTarget = scan.nextLine();


                    }while(rutTarget.length()>10 || rutTarget.length()>12 || rutTarget.isBlank());
                }
            case 3: // depositar
            case 4: //girar
            case 5: // consultar saldo
            case 6: // salir
                System.out.println("Saliendo del programa. Gracias por usar el sistema de gestión de BANK BOSTON");
                break;
        }


    }
}
