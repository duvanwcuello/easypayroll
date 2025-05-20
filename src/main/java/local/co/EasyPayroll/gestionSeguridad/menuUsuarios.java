package local.co.EasyPayroll.gestionSeguridad;

import java.util.Scanner;

import local.co.EasyPayroll.gestionContrato.*;
import local.co.EasyPayroll.gestionNomina.*;
import local.co.EasyPayroll.gestionNovedades.novedades;
import local.co.EasyPayroll.gestionParametrosGenerales.gestionParametros;
import local.co.EasyPayroll.gestionEmpleado.*;
import local.co.EasyPayroll.gestionInformes.informesNomina;

public class menuUsuarios {
    
    public static void // o String 
    menuPrincipalUsuario(String rolUsuarioIngresado) {
    
        if (rolUsuarioIngresado.equalsIgnoreCase("ADMINISTRADOR")) {
               menuUsuarioAdministrador(rolUsuarioIngresado);
            } else if (rolUsuarioIngresado.equalsIgnoreCase("AUXILIAR")) {
                menuUsuarioAuxiliar(rolUsuarioIngresado);
            } else if (rolUsuarioIngresado.equalsIgnoreCase("COORDINADOR")) {
                menuUsuarioCoordinador (rolUsuarioIngresado);
            } else {
                System.out.println("ROL NO RECONOCIDO");
            }
           // return rolUsuarioIngresado;
    }

    public static void menuUsuarioAdministrador(String rolActual){
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        while(continuar){
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("        MENU PRINCIPAL.");
            System.out.println("------------------------------------");
            System.out.println("1. Gestionar Usuarios.");
            System.out.println("2. Gestionar Empleados.");
            System.out.println("3. Gestionar Contratos.");
            System.out.println("4. Gestionar Novedades.");
            System.out.println("5. Gestionar Nomina.");
            System.out.println("6. Informes Informes de Nomina");
            System.out.println("7. Parametros Generales");
            System.out.println("8. Salir.");
            System.out.println("-----------------------------------");
            System.out.print("Seleccione una opción: ");
            
            int selecciondeUsuario = scanner.nextInt();
            scanner.nextLine(); 
       
            switch (selecciondeUsuario) {
                 case 1:
                    gestionUsuarios.menuGestionUsuario(rolActual);
                    break;
                 case 2:
                    gestionEmpleados.gestionEmpleado(rolActual);
                    break;
                 case 3:
                    gestionContratos.gestionContrato();
                    break;
                 case 4:
                    novedades.registrarNovedades();
                    break;
                 case 5:
                    gestionNominas.gestionNomina();
                    break;
                 case 6:
                    informesNomina.motrarInformes();
                    break;
                 case 7:
                    gestionParametros.menuGestionParametros();
                    break;
                case 8:
                    continuar = false;
                    System.out.println("Operación cancelada por el usuario.");
                    System.out.println("Saliendo..."); 
                    System.exit(0);
                    break;
                default:
                    scanner.close();
                    System.out.println("Opción no válida. Intente de nuevo.");   
                    
            }
        }
    }
        
    public static void menuUsuarioAuxiliar(String rolActual){
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        while(continuar){
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("        MENU PRINCIPAL.");
            System.out.println("------------------------------------");
            System.out.println("1. Gestionar Empleados.");
            System.out.println("2. Gestionar Contratos.");
            System.out.println("3. Gestionar Novedades.");
            System.out.println("4. Ver Informes de Nomina.");
            System.out.println("5. Salir.");
            System.out.println("------------------------------------");
            System.out.print("Seleccione una opción: ");
            
            int selecciondeUsuario = scanner.nextInt();
            scanner.nextLine(); 
       
            switch (selecciondeUsuario) {
                 case 1:
                     gestionEmpleados.gestionEmpleado(rolActual);
                     break;
                 case 2:
                     gestionContratos.gestionContrato();
                     break;
                 case 3:
                     novedades.registrarNovedades();
                     break;
                 case 4:
                     informesNomina.motrarInformes();
                     break;               
                 case 5:
                    continuar = false;
                    System.out.println("Operación cancelada por el usuario.");
                    System.out.println("Saliendo..."); 
                    System.exit(0);
                    break;
                default:
                    scanner.close();
                    System.out.println("Opción no válida. Intente de nuevo.");   
                    
                     
            }
        }
    }
    
    public static void menuUsuarioCoordinador (String rolActual){
        
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        while(continuar){
            System.out.println("");
            System.out.println("------------------------------------");
            System.out.println("        MENU PRINCIPAL.");
            System.out.println("------------------------------------");
            System.out.println("1. Gestionar Empleados.");
            System.out.println("2. Gestionar Contratos.");
            System.out.println("3. Gestionar Novedades.");
            System.out.println("4. Gestionar Nomina.");
            System.out.println("5. Ver Informes de Nomina");
            System.out.println("0. Salir.");
            System.out.println("------------------------------------");
            System.out.print("Seleccione una opción: ");
            
            int selecciondeUsuario = scanner.nextInt();
            scanner.nextLine(); 
       
            switch (selecciondeUsuario) {
                 case 1:
                     gestionEmpleados.gestionEmpleado(rolActual);
                     break;
                 case 2:
                     gestionContratos.gestionContrato();
                     break;
                 case 3:
                     novedades.registrarNovedades();
                     break;
                 case 4:
                     gestionNominas.gestionNomina();
                     break;
                 case 5:
                    informesNomina.motrarInformes();
                    break;
                case 6:
                    continuar = false;
                    System.out.println("Operación cancelada por el usuario.");
                    System.out.println("Saliendo..."); 
                    System.exit(0);
                    break;
                default:
                    scanner.close();
                    System.out.println("Opción no válida. Intente de nuevo.");   
                    
            }
        }
    }
}