package local.co.EasyPayroll.gestionEmpleado;

import java.util.Scanner;

import local.co.EasyPayroll.seguridad.menuUsuarios;
import local.co.EasyPayroll.utilidades.limpiarPantalla;

public class gestionEmpleados {
    
    public static void gestionEmpleado(String rolActual){
       
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
         
            System.out.println("-----------------------------------");
            System.out.println("|      GESTION DE EMPLEADOS       |");
            System.out.println("-----------------------------------");
            System.out.println("| 1. Crear nuevo empleado         |");
            System.out.println("| 2. Consultar empleado           |");
            System.out.println("| 3. Editar empleado              |");
            System.out.println("| 4. Mostrar todos los empleados  |");
            System.out.println("| 5. Atras                        |");
            System.out.println("| 0. Salir                        |");
            System.out.println("-----------------------------------\n");

            System.out.print("Seleccione una opción: ");
            
            int selecciondeUsuario = scanner.nextInt();
            scanner.nextLine();

            switch (selecciondeUsuario) {

                case 1:

                    limpiarPantalla.limpiarConsola();
                    nuevoEmpleado.crearNuevoEmpleado();
                    break;

                case 2:

                    limpiarPantalla.limpiarConsola();
                    consultaEmpleado.consultarEmpleadoExistente();
                    break;

                case 3:

                    limpiarPantalla.limpiarConsola();
                    System.out.println("------------------------------------");
                    System.out.println("|        EDICIÓN DE EMPLEADOS      |");
                    System.out.println("------------------------------------\n");

                    System.out.print("- Ingrese la identificación del empleado a editar: ");
                    String identificacion = scanner.nextLine();
                    
                    limpiarPantalla.limpiarConsola();
                    editarEmpleado.editarEmpleadoExistente(identificacion);
                    break;

                case 4:
                
                    limpiarPantalla.limpiarConsola();
                    consultaEmpleado.mostrarEmpleados();
                    break;

                case 5:

                    menuUsuarios.menuPrincipalUsuario(rolActual);
                    break;

                case 0:

                    limpiarPantalla.limpiarConsola();
                    System.out.println("Cerrando sesión, Saliendo...");
                    System.exit(0);
                    return;
                    
                default:
                
                    System.out.println("ERROR: Opción no válida. Intente de nuevo.");
            }
        }
    }
}
