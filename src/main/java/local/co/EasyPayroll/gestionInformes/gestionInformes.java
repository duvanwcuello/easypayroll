package local.co.EasyPayroll.gestionInformes;

import java.util.Scanner;

import local.co.EasyPayroll.gestionUtilidades.LimpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.SimulacionPrograma;

public class GestionInformes {

    public static void mostrarInformes(String rolActual) {
            
        Scanner scanner = new Scanner(System.in);        
        boolean continuar = true;

        while (continuar) {
            LimpiarPantalla.limpiarConsola();

            System.out.println("-----------------------------------");
            System.out.println("|    MOSTRAR INFORMES DE NOMINA   |");
            System.out.println("-----------------------------------");  
            System.out.println("| 1. Planilla de pago de Nomina   |");
            System.out.println("| 2. Mostrar Volante de pago      |");
            System.out.println("| 9. Atras                        |");
            System.out.println("| 0. Salir                        |");
            System.out.println("-----------------------------------\n");

            System.out.print("- Seleccione una Opcion: ");
            int seleccion = scanner.nextInt();

            switch(seleccion){
                case 1:
                    InformePlanillaPagos.mostrarPlanilla();
                    break;
                case 2:                  
                    VolantesDePago.mostrarVolanteEmpleado();
                    break;
                case 9:
                    continuar = false;
                    LimpiarPantalla.limpiarConsola();
                    System.out.println("Saliendo de la gestión de Informes...");
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