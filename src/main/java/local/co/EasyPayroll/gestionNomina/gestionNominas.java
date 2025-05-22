package local.co.EasyPayroll.gestionNomina;

import java.util.Scanner;

import local.co.EasyPayroll.gestionInformes.informesNomina;
import local.co.EasyPayroll.utilidades.limpiarPantalla;

public class gestionNominas {
    
    public static void gestionNomina(String rolActual) {
          
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while(continuar){

            limpiarPantalla.limpiarConsola();

            System.out.println("--------------------------------");
            System.out.println("|       GESTION DE NOMINA      |");
            System.out.println("--------------------------------");
            System.out.println("| 1. Liquidar Nomina           |");
            System.out.println("| 2. Informes de Nomina        |");
            System.out.println("| 0. Atras                     |");
            System.out.println("--------------------------------\n");

            System.out.print("Seleccione una opción: ");
            
            int selecciondeUsuario = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (selecciondeUsuario) {

                case 1:

                    limpiarPantalla.limpiarConsola();
                    procesarNominaMes.procesarNomina();
                    break;

                case 2:

                    limpiarPantalla.limpiarConsola();
                    informesNomina.mostrarInformes(rolActual);

                case 0:

                    continuar = false;
                    break;

                default:

                    scanner.close();
                    System.out.println("Opción no válida. Intente de nuevo.");                
            }
        } 
    }
}