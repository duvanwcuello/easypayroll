/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.co.EasyPayroll.gestionNomina;

import java.util.Scanner;

import local.co.EasyPayroll.gestionInformes.informesNomina;
import local.co.EasyPayroll.gestionNovedades.novedades;

public class gestionNominas {
    
    public static void gestionNomina(){
        
        //se construye el menu
        
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        while(continuar){
            System.out.println("");
          //  System.out.println("-------------------------------");
            System.out.println("\n        GESTION DE NOMINA.");
            System.out.println("-------------------------------");
            System.out.println("1. Registrar Novedades.");
            System.out.println("2. Liquidar Nomina.");
            System.out.println("3. Informes de Nomina.");
            System.out.println("0. volver al menu anterior.");
            System.out.println("-------------------------------");
            System.out.print("Seleccione una opción: ");
            
            int selecciondeUsuario = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (selecciondeUsuario) {
               case 1:
                   novedades.registrarNovedades();
                   break;
               case 2:
                    procesarNominaMes.procesarNomina();
                    break;
                case 3:
                    informesNomina.motrarInformes();
                case 0:
                     continuar = false;
                    System.out.println("Saliendo de la gestión de Nomina...");
                    break;
                default:
                    scanner.close();
                    System.out.println("Opción no válida. Intente de nuevo.");                
           }
          
        } 
        
    }

}
    

