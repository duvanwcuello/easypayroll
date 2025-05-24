package local.co.EasyPayroll.gestionSeguridad;

import java.util.InputMismatchException;
import java.util.Scanner;


import local.co.EasyPayroll.aplicacion;
import local.co.EasyPayroll.gestionContrato.gestionContratos;
import local.co.EasyPayroll.gestionNomina.*;
import local.co.EasyPayroll.gestionNovedades.gestionNovedades;
import local.co.EasyPayroll.gestionNovedades.novedades;
import local.co.EasyPayroll.gestionParametrosGenerales.gestionParametros;
import local.co.EasyPayroll.gestionUsuario.gestionUsuarios;
import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;
import local.co.EasyPayroll.gestionEmpleado.*;
import local.co.EasyPayroll.gestionInformes.gestionInformes;

public class menuUsuarios {
    
    public static void menuPrincipalUsuario(String rolUsuarioIngresado) {
    
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
        
        try{
            while(continuar){
                System.out.println("");
                System.out.println("-------------------------------");
                System.out.println("|        MENU PRINCIPAL       |");
                System.out.println("-------------------------------");
                System.out.println("| 1. Gestionar Usuarios       |");
                System.out.println("| 2. Gestionar Empleados      |");
                System.out.println("| 3. Gestionar Contratos      |");
                System.out.println("| 4. Gestionar Novedades      |");
                System.out.println("| 5. Gestionar Nomina         |");
                System.out.println("| 6. Gestionar Informes.      |");    
                System.out.println("| 7. Parametros Generales     |");
                System.out.println("| 9. Cerar Sesion             |");
                System.out.println("| 0. Salir                    |");
                System.out.println("-------------------------------");
                
                System.out.print("Seleccione una opción: ");
                int selecciondeUsuario = scanner.nextInt();
                scanner.nextLine(); 
                            
                switch (selecciondeUsuario){
                    case 1:
                        limpiarPantalla.limpiarConsola();
                        simulacionPrograma.simulaEjecucion();
                        gestionUsuarios.menuGestionUsuarios(rolActual);
                        simulacionPrograma.continuarPrograma();
                        limpiarPantalla.limpiarConsola();
                        break;
                    case 2:
                        limpiarPantalla.limpiarConsola();
                        simulacionPrograma.simulaEjecucion();
                        gestionEmpleados.gestionEmpleado(rolActual);
                        simulacionPrograma.continuarPrograma();
                        limpiarPantalla.limpiarConsola();
                        break;
                    case 3:
                        limpiarPantalla.limpiarConsola();
                        simulacionPrograma.simulaEjecucion();
                        gestionContratos.gestionContrato(rolActual);
                        simulacionPrograma.continuarPrograma();
                        limpiarPantalla.limpiarConsola();
                        break;
                    case 4:
                        limpiarPantalla.limpiarConsola();
                        simulacionPrograma.simulaEjecucion();
                        gestionNovedades.gestionNovedades(rolActual);
                        simulacionPrograma.continuarPrograma();
                        limpiarPantalla.limpiarConsola();
                        break;
                    case 5:
                        limpiarPantalla.limpiarConsola();
                        simulacionPrograma.simulaEjecucion();
                        gestionNominas.gestionNomina(rolActual);
                        simulacionPrograma.continuarPrograma();
                        limpiarPantalla.limpiarConsola();
                        break;
                    case 6:
                        limpiarPantalla.limpiarConsola();
                        simulacionPrograma.simulaEjecucion();
                        gestionInformes.mostrarInformes(rolActual);
                        simulacionPrograma.continuarPrograma();
                        limpiarPantalla.limpiarConsola();
                        break;
                    case 7:
                        limpiarPantalla.limpiarConsola();
                        simulacionPrograma.simulaEjecucion();
                        gestionParametros.menuGestionParametros();
                        simulacionPrograma.continuarPrograma();
                        limpiarPantalla.limpiarConsola();
                        break;
                    case 9:
                        continuar = false;
                        System.out.println("Cerrando Sesion...");
                        simulacionPrograma.simulaEjecucion();
                        aplicacion.main(null);
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
                        System.out.println("Opción no válida. Intente de nuevo.");            
                }
            }
        }catch (InputMismatchException e){
            System.out.println("¡Error! Debes ingresar un número entero.");
            menuPrincipalUsuario(rolActual);
        }
    }
    

    
    public static void menuUsuarioCoordinador (String rolActual){
        
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        try{
            while(continuar){
                System.out.println("");
                System.out.println("-------------------------------");
                System.out.println("|        MENU PRINCIPAL       |");
                System.out.println("-------------------------------");
                System.out.println("| 1. Gestionar Empleados      |");
                System.out.println("| 2. Gestionar Contratos      |");
                System.out.println("| 3. Gestionar Novedades      |");
                System.out.println("| 4. Gestionar Nomina         |");
                System.out.println("| 5. Gestion de Informes      |");
                System.out.println("| 9. Cerar Sesion             |");
                System.out.println("| 0. Salir                    |");
                System.out.println("-------------------------------");

                System.out.print("Seleccione una opción: ");
                int selecciondeUsuario = scanner.nextInt();
                scanner.nextLine(); 
        
                switch (selecciondeUsuario){
                    case 1:
                        gestionEmpleados.gestionEmpleado(rolActual);
                        break;
                    case 2:
                        gestionContratos.gestionContrato(rolActual);
                        break;
                    case 3:
                        novedades.menuNovedades(rolActual);
                        break;
                    case 4:
                        gestionNominas.gestionNomina(rolActual);;
                        break;
                    case 5:
                    gestionInformes.mostrarInformes(rolActual);;
                    break;
                    case 9:
                        System.out.println("Cerrando Sesion...");
                        simulacionPrograma.simulaEjecucion();
                        aplicacion.main(null);
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
                        System.out.println("Opción no válida. Intente de nuevo.");                   
                }   
            }
        }catch (InputMismatchException e){
            System.out.println("¡Error! Debes ingresar un número entero.");
            menuPrincipalUsuario(rolActual);
        }
    }

    public static void menuUsuarioAuxiliar(String rolActual){
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        try{
            while(continuar){
                System.out.println("");
                System.out.println("-------------------------------");
                System.out.println("|        MENU PRINCIPAL       |");
                System.out.println("-------------------------------");
                System.out.println("| 1. Gestionar Empleados      |");
                System.out.println("| 2. Gestionar Contratos      |");
                System.out.println("| 3. Gestionar Novedades      |");
                System.out.println("| 4. Gestionar Informes       |");
                System.out.println("| 9. Cerar Sesion             |");
                System.out.println("| 0. Salir                    |");
                System.out.println("-----------------------------\n");

                System.out.print("Seleccione una opción: ");
                int selecciondeUsuario = scanner.nextInt();
                scanner.nextLine(); 
        
                switch (selecciondeUsuario) {
                    case 1:
                        gestionEmpleados.gestionEmpleado(rolActual);
                        break;
                    case 2:
                        gestionContratos.gestionContrato(rolActual);;
                        break;
                    case 3:
                        novedades.menuNovedades(rolActual);;
                        break;
                    case 4:
                        gestionInformes.mostrarInformes(rolActual);;
                        break;               
                    case 9:
                        System.out.println("Cerrando Sesion...");
                        simulacionPrograma.simulaEjecucion();
                        aplicacion.main(null);
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
                        System.out.println("Opción no válida. Intente de nuevo.");      
                }
            }
        }catch (InputMismatchException e){
            System.out.println("¡Error! Debes ingresar un número entero.");
            menuPrincipalUsuario(rolActual);
        }
    }
}