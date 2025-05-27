/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.co.EasyPayroll.GestionNovedades;

import java.util.Scanner;

import local.co.EasyPayroll.gestionInformes.InformesNovedades;
import local.co.EasyPayroll.gestionUtilidades.LimpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.SimulacionPrograma;

public class GestionNovedades {
    
    public static void gestionNovedades(String rolActual){
                 
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
                
        while(continuar){
            LimpiarPantalla.limpiarConsola();
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
                   RegistroNovedades.registrarNovedadesxEmpleado();;
                   break;
               case 2:
                    RegistroNovedades.registroMasivoNovedades();
                    break;
                case 3:
                    InformesNovedades.consolidadoNovedades();
                    break;
                case 9:
                    continuar = false;
                    LimpiarPantalla.limpiarConsola();
                    System.out.println("Saliendo de la gestión de Novedades...");
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
                    System.out.println("Opción no válida. Intente de nuevo.");                
           }
          
        } 
        
    }

}
    

