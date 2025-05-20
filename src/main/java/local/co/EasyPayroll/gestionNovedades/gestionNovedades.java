/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.co.EasyPayroll.gestionNovedades;

import java.util.Scanner;

public class gestionNovedades {
    
    public static void gestionNomina(){
        
        //se construye el menu
        
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        while(continuar){
            System.out.println("");
          //  System.out.println("-------------------------------");
            System.out.println("\n        GESTION DE NOVEDADES.");
            System.out.println("-------------------------------");
            System.out.println("1. Registrar Novedades.");
            System.out.println("2. Informes de Novedades.");
            System.out.println("0. Salir.");
            System.out.println("-------------------------------");
            System.out.print("Seleccione una opción: ");
            
            int selecciondeUsuario = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (selecciondeUsuario) {
               case 1:
                   novedades.registrarNovedades();
                   break;
               case 2:
                    
                    break;
                case 0:
                     continuar = false;
                    System.out.println("Saliendo de la gestión de Novedades...");
                    break;
                default:
                    scanner.close();
                    System.out.println("Opción no válida. Intente de nuevo.");                
           }
          
        } 
        
    }

}
    

