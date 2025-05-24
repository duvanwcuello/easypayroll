/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.co.EasyPayroll.gestionNovedades;

import java.util.Scanner;

import local.co.EasyPayroll.gestionInformes.informesNovedades;
import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;

public class gestionNovedades {
    
    public static void gestionNovedades(String rolActual){
                 
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
                
        while(continuar){
            limpiarPantalla.limpiarConsola();
            System.out.println("");
            System.out.println("----------------------------------------");
            System.out.println("|        GESTION DE NOVEDADES.         |");
            System.out.println("----------------------------------------");
            System.out.println("| 1. Registrar Novedades por Empleado. |");
            System.out.println("| 2. Registro masivo de novedades      |");
            System.out.println("| 3. Informes de Novedades.            |");
            System.out.println("| 9. Atras                             |");
            System.out.println("| 0. Salir.                            |");
            System.out.println("---------------------------------------|");
            
            System.out.print("Seleccione una opción: ");
            int selecciondeUsuario = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (selecciondeUsuario) {
               case 1:
                   registroNovedades.registrarNovedadesxEmpleado();;
                   break;
               case 2:
                    registroNovedades.registroMasivoNovedades();
                    break;
                case 3:
                    informesNovedades.consolidadoNovedades();
                    break;
                case 9:
                    continuar = false;
                    limpiarPantalla.limpiarConsola();
                    System.out.println("Saliendo de la gestión de Novedades...");
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
                    System.out.println("Opción no válida. Intente de nuevo.");                
           }
          
        } 
        
    }

}
    

