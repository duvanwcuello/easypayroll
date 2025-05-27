
package local.co.GestionNovedades;


import java.util.*;

import local.co.GestionSeguridad.MenuUsuarios;
import local.co.GestionUtilidades.LimpiarPantalla;
import local.co.GestionUtilidades.SimulacionPrograma;



public class Novedades {

    public static void menuNovedades(String rolActual) {
        
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        LimpiarPantalla.limpiarConsola();

        while (continuar) {

            

            System.out.println("----------------------------------------------------");
            System.out.println("|               GESTIÓN DE NOVEDADES               |");
            System.out.println("----------------------------------------------------");
            System.out.println("| 1. Consulta de novedades                         |");
            System.out.println("| 2. Registro masivo de novedades                  |");
            System.out.println("| 3. Registro de novedades por empleado            |");
            System.out.println("| 4. Atras                                         |");
            System.out.println("| 0. Salir                                         |");
            System.out.println("----------------------------------------------------\n");

            System.out.print("- Seleccione una opción: ");
            int opcionNovedades = scanner.nextInt();
            scanner.nextLine();

            switch (opcionNovedades) {
                case 1:
                    LimpiarPantalla.limpiarConsola();
                   // consolidadoNovedades();
                    break;
                case 2:
                    LimpiarPantalla.limpiarConsola();
                    //registroMasivoNovedades();
                    break;
                case 3:
                    LimpiarPantalla.limpiarConsola();
                   // registrarNovedadesxEmpleado();
                    break;
                case 4:                    
                    MenuUsuarios.menuPrincipalUsuario(rolActual);
                    break;
                case 0:
                    continuar = false;
                    System.out.println("\nOperación cancelada por el usuario.");
                    System.out.println("Saliendo..."); 
                    System.exit(0);
                default:
                    System.out.println("Opción no válida, por favor, seleccione una opción válida.");
                    SimulacionPrograma.continuarConTeclado();
            }
        }
    }

   
   
   
   
}