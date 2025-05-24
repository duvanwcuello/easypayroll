package local.co.EasyPayroll.gestionEmpleado;

import java.util.Scanner;

import local.co.EasyPayroll.gestionSeguridad.menuUsuarios;
import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;

public class gestionEmpleados {
    
    public static void gestionEmpleado(String rolActual){
       
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("-----------------------------------");
            System.out.println("|      GESTION DE EMPLEADOS       |");
            System.out.println("-----------------------------------");
            System.out.println("| 1. Crear nuevo empleado         |");
            System.out.println("| 2. Consultar empleado           |");
            System.out.println("| 3. Editar empleado              |");
            System.out.println("| 4. Mostrar todos los empleados  |");
            System.out.println("| 9. Atras                        |");
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
                    consultaEmpleado.consultarTodosLosEmpleados();
                    break;
                case 9:
                    continuar = false;
                    limpiarPantalla.limpiarConsola();
                    System.out.println("Saliendo de la gestión de Empleados...");
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
                    System.out.println("ERROR: Opción no válida. Intente de nuevo."); 
            }
        }
    }
}
