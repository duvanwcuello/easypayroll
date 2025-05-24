
package local.co.EasyPayroll.gestionNovedades;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import local.co.EasyPayroll.gestionSeguridad.menuUsuarios;
import local.co.EasyPayroll.gestionUtilidades.datosDeUsoGeneral;
import local.co.EasyPayroll.gestionUtilidades.formatoMoneda;
import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;


public class novedades {

    public static void menuNovedades(String rolActual) {
        
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        limpiarPantalla.limpiarConsola();

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
                    limpiarPantalla.limpiarConsola();
                   // consolidadoNovedades();
                    break;
                case 2:
                    limpiarPantalla.limpiarConsola();
                    //registroMasivoNovedades();
                    break;
                case 3:
                    limpiarPantalla.limpiarConsola();
                   // registrarNovedadesxEmpleado();
                    break;
                case 4:                    
                    menuUsuarios.menuPrincipalUsuario(rolActual);
                    break;
                case 0:
                    continuar = false;
                    System.out.println("\nOperación cancelada por el usuario.");
                    System.out.println("Saliendo..."); 
                    System.exit(0);
                default:
                    System.out.println("Opción no válida, por favor, seleccione una opción válida.");
                    simulacionPrograma.continuarConTeclado();
            }
        }
    }

   
   
   
   
}