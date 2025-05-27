package local.co.GestionSeguridad;

import java.util.InputMismatchException;
import java.util.Scanner;

import local.co.Aplicacion;
import local.co.GestionContratos.GestionContratos;
import local.co.GestionEmpleados.GestionEmpleados;
import local.co.GestionInformes.GestionInformes;
import local.co.GestionNomina.GestionNominas;
import local.co.GestionNovedades.GestionNovedades;
import local.co.GestionNovedades.Novedades;
import local.co.GestionParametrosLegales.GestionParametros;
import local.co.GestionUsuarios.GestionUsuarios;
import local.co.GestionUtilidades.LimpiarPantalla;
import local.co.GestionUtilidades.SimulacionPrograma;



public class MenuUsuarios {
    
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
                System.out.println("|------------------------------------------------------------------|");
                System.out.println("|                         MENU PRINCIPAL                           |");
                System.out.println("|------------------------------------------------------------------|");
                System.out.println("| 1. Gestionar Usuarios.                                           |");
                System.out.println("| 2. Gestionar Empleados.                                          |");
                System.out.println("| 3. Gestionar Contratos.                                          |");
                System.out.println("| 4. Gestionar Novedades.                                          |");
                System.out.println("| 5. Gestionar Nomina.                                             |");
                System.out.println("| 6. Gestionar Informes.                                           |");    
                System.out.println("| 7. Parametros Generales.                                         |");
                System.out.println("| 8. Cambiar Contraseña.                                           |");
                System.out.println("| 9. Cerar Sesion.                                                 |");
                System.out.println("| 0. Salir.                                                        |");
                System.out.println("|-------------------------------------------------------------------|");
                
                System.out.print("Seleccione una opción: ");
                int selecciondeUsuario = scanner.nextInt();
                scanner.nextLine(); 
                            
                switch (selecciondeUsuario){
                    case 1:
                        LimpiarPantalla.limpiarConsola();
                        SimulacionPrograma.simulaEjecucion();
                        GestionUsuarios.menuGestionUsuarios(rolActual);
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;
                    case 2:
                        LimpiarPantalla.limpiarConsola();
                        SimulacionPrograma.simulaEjecucion();
                        GestionEmpleados.gestionEmpleado(rolActual);
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;
                    case 3:
                        LimpiarPantalla.limpiarConsola();
                        SimulacionPrograma.simulaEjecucion();
                        GestionContratos.gestionContrato(rolActual);
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;
                    case 4:
                        LimpiarPantalla.limpiarConsola();
                        SimulacionPrograma.simulaEjecucion();
                        GestionNovedades.gestionNovedades(rolActual);
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;
                    case 5:
                        LimpiarPantalla.limpiarConsola();
                        SimulacionPrograma.simulaEjecucion();
                        GestionNominas.gestionNomina(rolActual);
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;
                    case 6:
                        LimpiarPantalla.limpiarConsola();
                        SimulacionPrograma.simulaEjecucion();
                        GestionInformes.mostrarInformes(rolActual);
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;
                    case 7:
                        LimpiarPantalla.limpiarConsola();
                        SimulacionPrograma.simulaEjecucion();
                        GestionParametros.menuGestionParametros();
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;
                    case 8:
                        LimpiarPantalla.limpiarConsola();
                        SimulacionPrograma.simulaEjecucion();
                        GestiondeContrasenias.cambiarContraseñiaUsuario();
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();

                        break;
                    case 9:
                        continuar = false;
                        System.out.println("Cerrando Sesion...");
                        SimulacionPrograma.simulaEjecucion();
                        Aplicacion.main(null);
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
        }catch (InputMismatchException e){
            System.out.println("¡Error! Debes ingresar un número entero.");
            SimulacionPrograma.continuarPrograma();
            LimpiarPantalla.limpiarConsola();
            menuPrincipalUsuario(rolActual);
        }
    }
    

    
    public static void menuUsuarioCoordinador (String rolActual){
        
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        try{
            while(continuar){
                System.out.println("");
                System.out.println("|------------------------------------------------------------------|");
                System.out.println("|                          MENU PRINCIPAL                          |");
                System.out.println("|------------------------------------------------------------------|");
                System.out.println("| 1. Gestionar Empleados                                           |");
                System.out.println("| 2. Gestionar Contratos                                           |");
                System.out.println("| 3. Gestionar Novedades                                           |");
                System.out.println("| 4. Gestionar Nomina                                              |");
                System.out.println("| 5. Gestion de Informes                                           |");
                System.out.println("| 8. Cambiar Contraseña.                                           |");
                System.out.println("| 9. Cerar Sesion                                                  |");
                System.out.println("| 0. Salir                                                         |");
                System.out.println("|------------------------------------------------------------------|");

                System.out.print("Seleccione una opción: ");
                int selecciondeUsuario = scanner.nextInt();
                scanner.nextLine(); 
        
                switch (selecciondeUsuario){
                    case 1:
                        LimpiarPantalla.limpiarConsola();
                        SimulacionPrograma.simulaEjecucion();
                        GestionEmpleados.gestionEmpleado(rolActual);
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;
                    case 2:
                        LimpiarPantalla.limpiarConsola();
                        SimulacionPrograma.simulaEjecucion();
                        GestionContratos.gestionContrato(rolActual);
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;
                    case 3:
                        LimpiarPantalla.limpiarConsola();
                        SimulacionPrograma.simulaEjecucion();
                        Novedades.menuNovedades(rolActual);
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;
                    case 4:
                        LimpiarPantalla.limpiarConsola();
                        SimulacionPrograma.simulaEjecucion();
                        GestionNominas.gestionNomina(rolActual);;
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;
                    case 5:
                        LimpiarPantalla.limpiarConsola();
                        SimulacionPrograma.simulaEjecucion();
                        GestionInformes.mostrarInformes(rolActual);
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;
                    case 8:
                        LimpiarPantalla.limpiarConsola();
                        SimulacionPrograma.simulaEjecucion();
                        GestiondeContrasenias.cambiarContraseñiaUsuario();
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;
                    case 9:
                        System.out.println("Cerrando Sesion...");
                        SimulacionPrograma.simulaEjecucion();
                        Aplicacion.main(null);
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
                        SimulacionPrograma.simulaEjecucion();                   
                }   
            }
        }catch (InputMismatchException e){
            System.out.println("¡Error! Debes ingresar un número entero.");
            SimulacionPrograma.continuarPrograma();
            LimpiarPantalla.limpiarConsola();
            menuPrincipalUsuario(rolActual);
        }
    }

    public static void menuUsuarioAuxiliar(String rolActual){
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        try{
            while(continuar){
                System.out.println("");
                System.out.println("|------------------------------------------------------------------|");
                System.out.println("|                          MENU PRINCIPAL                          |");
                System.out.println("|------------------------------------------------------------------|");
                System.out.println("| 1. Gestionar Empleados                                           |");
                System.out.println("| 2. Gestionar Contratos                                           |");
                System.out.println("| 3. Gestionar Novedades                                           |");
                System.out.println("| 4. Gestionar Informes                                            |");
                System.out.println("| 8. Cambiar Contraseña.                                           |");
                System.out.println("| 9. Cerar Sesion                                                  |");
                System.out.println("| 0. Salir                                                         |");
                System.out.println("|------------------------------------------------------------------|");

                System.out.print("Seleccione una opción: ");
                int selecciondeUsuario = scanner.nextInt();
                scanner.nextLine(); 
        
                switch (selecciondeUsuario) {
                    case 1:
                        GestionEmpleados.gestionEmpleado(rolActual);
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;
                    case 2:
                        GestionContratos.gestionContrato(rolActual);
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;
                    case 3:
                        Novedades.menuNovedades(rolActual);
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;
                    case 4:
                        GestionInformes.mostrarInformes(rolActual);
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;            
                    case 8:
                        GestiondeContrasenias.cambiarContraseñiaUsuario();
                        SimulacionPrograma.simulaEjecucion();
                        LimpiarPantalla.limpiarConsola();
                        break;   
                    case 9:
                        System.out.println("Cerrando Sesion...");
                        SimulacionPrograma.simulaEjecucion();
                        Aplicacion.main(null);
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
                        SimulacionPrograma.simulaEjecucion(); 
                        LimpiarPantalla.limpiarConsola();     
                }
            }
        }catch (InputMismatchException e){
            System.out.println("¡Error! Debes ingresar un número entero.");
            SimulacionPrograma.continuarPrograma();
            LimpiarPantalla.limpiarConsola();
            menuPrincipalUsuario(rolActual);
        }
    }
}