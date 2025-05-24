package local.co.EasyPayroll.gestionNomina;

import java.util.Scanner;

import local.co.EasyPayroll.gestionInformes.gestionInformes;
import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;

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
            System.out.println("| 9. Atras                     |");
            System.out.println("| 0. Salir                     |");
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
                    gestionInformes.mostrarInformes(rolActual);
                case 9:
                    continuar = false;
                    limpiarPantalla.limpiarConsola();
                    System.out.println("Saliendo de la gestión de Nomina...");
                    simulacionPrograma.continuarPrograma();
                    System.err.println("Retrornando al Menu Principal...");
                    simulacionPrograma.continuarPrograma();
                    limpiarPantalla.limpiarConsola();
                    break;
                case 0:
                    continuar = false;
                    limpiarPantalla.limpiarConsola();
                    System.out.println("¡Operacion Cancelada!...");
                    simulacionPrograma.continuarPrograma();

                    System.out.println("Cerrando Sesion...");
                    simulacionPrograma.continuarPrograma();

                    System.out.println("Cerrando Programa...");
                    simulacionPrograma.continuarPrograma();

                    limpiarPantalla.limpiarConsola();
                    System.exit(0);
                    break;
                default:
                    scanner.close();
                    System.out.println("ERROR: Opción no válida. Intente de nuevo.");                
            }
        } 
    }
}