package local.co.EasyPayroll.GestionNomina;

import java.util.Scanner;

import local.co.EasyPayroll.GestionInformes.GestionInformes;
import local.co.EasyPayroll.GestionUtilidades.LimpiarPantalla;
import local.co.EasyPayroll.GestionUtilidades.SimulacionPrograma;

public class GestionNominas {
    
    public static void gestionNomina(String rolActual) {
          
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while(continuar){
            LimpiarPantalla.limpiarConsola();

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
                    LimpiarPantalla.limpiarConsola();
                    ProcesarNominaMes.procesarNomina();
                    break;
                case 2:
                    LimpiarPantalla.limpiarConsola();
                    GestionInformes.mostrarInformes(rolActual);
                case 9:
                    continuar = false;
                    LimpiarPantalla.limpiarConsola();
                    System.out.println("Saliendo de la gestión de Nomina...");
                     SimulacionPrograma.simulaEjecucion();
                    System.err.println("Retrornando al Menu Principal...");
                     SimulacionPrograma.simulaEjecucion();
                    LimpiarPantalla.limpiarConsola();
                    break;
                case 0:
                    continuar = false;
                    LimpiarPantalla.limpiarConsola();
                    System.out.println("¡Operacion Cancelada!...");
                     SimulacionPrograma.simulaEjecucion();

                    System.out.println("Cerrando Sesion...");
                     SimulacionPrograma.simulaEjecucion();

                    System.out.println("Cerrando Programa...");
                    SimulacionPrograma.continuarPrograma();

                    LimpiarPantalla.limpiarConsola();
                    System.exit(0);
                    break;
                default:
                    System.out.println("ERROR: Opción no válida. Intente de nuevo.");                
            }
        } 
    }
}