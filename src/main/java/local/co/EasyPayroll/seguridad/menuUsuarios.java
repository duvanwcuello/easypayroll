package local.co.EasyPayroll.seguridad;

import java.util.Scanner;
import local.co.EasyPayroll.gestionEmpleado.gestionEmpleados;
import local.co.EasyPayroll.gestionInformes.informesNomina;
import local.co.EasyPayroll.gestionNomina.gestionNominas;
import local.co.EasyPayroll.gestionNovedades.novedades;
import local.co.EasyPayroll.gestionParametrosGenerales.gestionParametros;
import local.co.EasyPayroll.utilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionContrato.*;

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
    }

    public static void menuUsuarioAdministrador(String rolActual){

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while(continuar){

            limpiarPantalla.limpiarConsola();

            System.out.println("-------------------------------");
            System.out.println("|        MENU PRINCIPAL       |");
            System.out.println("-------------------------------");
            System.out.println("| 1. Gestionar Usuarios       |");
            System.out.println("| 2. Gestionar Empleados      |");
            System.out.println("| 3. Gestionar Contratos      |");
            System.out.println("| 4. Gestionar Novedades      |");
            System.out.println("| 5. Gestionar Nomina         |");
            System.out.println("| 6. Informes de Nomina       |");    
            System.out.println("| 7. Parametros Generales     |");
            System.out.println("| 0. Salir                    |");
            System.out.println("-------------------------------");

            System.out.print("\n- Seleccione una opción: ");
            int selecciondeUsuario = scanner.nextInt();
            scanner.nextLine();
       
            switch (selecciondeUsuario) {

                case 1:

                    limpiarPantalla.limpiarConsola();
                    gestionUsuarios.gestionUsuario(rolActual);
                    break;

                case 2:

                    limpiarPantalla.limpiarConsola();
                    gestionEmpleados.gestionEmpleado(rolActual);
                    break;

                case 3:

                    limpiarPantalla.limpiarConsola();
                    gestionContratos.gestionContrato(rolActual);
                    break;
                    
                case 4:

                    limpiarPantalla.limpiarConsola();
                    novedades.menuNovedades(rolActual);
                    break;

                case 5:

                    limpiarPantalla.limpiarConsola();
                    gestionNominas.gestionNomina(rolActual);
                    break;

                case 6:

                    limpiarPantalla.limpiarConsola();
                    informesNomina.motrarInformes(rolActual);
                    break;

                case 7:

                    limpiarPantalla.limpiarConsola();
                    gestionParametros.menuGestionParametros();
                    break;

                case 0:

                    continuar = false;
                    System.out.println("Operación cancelada por el usuario.");
                    System.out.println("Saliendo..."); 
                    System.exit(0);
            }
        }
    }
        
    public static void menuUsuarioAuxiliar(String rolActual){

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while(continuar){

            limpiarPantalla.limpiarConsola();

            System.out.println("-------------------------------");
            System.out.println("|        MENU PRINCIPAL       |");
            System.out.println("-------------------------------");
            System.out.println("| 1. Gestionar Empleados      |");
            System.out.println("| 2. Gestionar Contratos      |");
            System.out.println("| 3. Gestionar Novedades      |");
            System.out.println("| 4. Informes de Nomina       |");
            System.out.println("| 0. Salir                    |");
            System.out.println("-------------------------------\n");

            System.out.print("Seleccione una opción: ");
            int selecciondeUsuario = scanner.nextInt();
            scanner.nextLine();

            switch (selecciondeUsuario) {

                case 1:

                    limpiarPantalla.limpiarConsola();
                    gestionEmpleados.gestionEmpleado(rolActual);
                    break;

                case 2:

                    limpiarPantalla.limpiarConsola();
                    gestionContratos.gestionContrato(rolActual);
                    break;

                case 3:

                    limpiarPantalla.limpiarConsola();
                    novedades.menuNovedades(rolActual);
                    break;

                case 4:
                
                    limpiarPantalla.limpiarConsola();
                    informesNomina.motrarInformes(rolActual);
                    break;

                case 0:

                    continuar = false;
                    limpiarPantalla.limpiarConsola();
                    System.out.println("Cerrando sesión, Saliendo...");
                    System.exit(0);
                    break;

                default:

                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }
    
    public static void menuUsuarioCoordinador (String rolActual){
        
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while(continuar){

            limpiarPantalla.limpiarConsola();

            System.out.println("------------------------------------");
            System.out.println("|          MENU PRINCIPAL          |");
            System.out.println("------------------------------------");
            System.out.println("|1. Gestionar Empleados            |");
            System.out.println("|2. Gestionar Contratos            |");
            System.out.println("|3. Gestionar Novedades            |");
            System.out.println("|4. Gestionar Nomina               |");
            System.out.println("|5. Informes de Nomina             |");
            System.out.println("|0. Salir                          |");
            System.out.println("------------------------------------");

            System.out.print("\n- Seleccione una opción: ");
            int selecciondeUsuario = scanner.nextInt();
            scanner.nextLine();
       
            switch (selecciondeUsuario) {

                case 1:

                    limpiarPantalla.limpiarConsola();
                    gestionEmpleados.gestionEmpleado(rolActual);
                    break;

                case 2:

                    limpiarPantalla.limpiarConsola();
                    gestionContratos.gestionContrato(rolActual);
                    break;

                case 3:

                    limpiarPantalla.limpiarConsola();
                    novedades.menuNovedades(rolActual);
                    break;

                case 4:

                    limpiarPantalla.limpiarConsola();
                    gestionNominas.gestionNomina(rolActual);
                    break;

                case 5:

                    limpiarPantalla.limpiarConsola();
                    informesNomina.motrarInformes(rolActual);
                    break;

                case 0:

                    continuar = false;
                    limpiarPantalla.limpiarConsola();
                    System.out.println("Cerrando sesión, Saliendo...");
                    System.exit(0);
                
                default:

                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }
}
        
    
    

