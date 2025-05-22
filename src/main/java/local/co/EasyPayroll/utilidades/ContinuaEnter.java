package local.co.EasyPayroll.utilidades;

import java.util.Scanner;

public class ContinuaEnter {

    static public void PressEnter(char mode){

        Scanner sc = new Scanner(System.in);

        if (mode == 'C') {

            System.out.print("Presiona Enter dos veces para continuar...");

        } else if (mode == 'E') {

            System.out.print("Presiona Enter dos veces para salir...");
        }

        try {

            String seguir = sc.nextLine();
            sc.nextLine();

            if (seguir.equalsIgnoreCase("exit")) {
                limpiarPantalla.limpiarConsola();
            }
        }

        catch(Exception e){

            System.err.println("ERROR: " + e.getMessage());
            
        }

        limpiarPantalla.limpiarConsola();
    }
}