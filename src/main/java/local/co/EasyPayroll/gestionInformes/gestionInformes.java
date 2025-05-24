package local.co.EasyPayroll.gestionInformes;

import java.util.Scanner;

import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;

public class gestionInformes {

    public static void mostrarInformes(String rolActual) {
            
        Scanner scanner = new Scanner(System.in);        
        boolean continuar = true;

        while (continuar) {
            limpiarPantalla.limpiarConsola();

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
                    informePlanillaPagos.mostrarPlanilla();
                    break;
                case 2:                  
                    volantesDePago.mostrarVolanteEmpleado();
                    break;
                case 9:
                    continuar = false;
                    limpiarPantalla.limpiarConsola();
                    System.out.println("Saliendo de la gestión de Informes...");
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