package local.co.GestionEmpleados;

import java.util.Scanner;

import local.co.GestionUtilidades.LimpiarPantalla;
import local.co.GestionUtilidades.SimulacionPrograma;

public class GestionEmpleados {
    
    public static void gestionEmpleado(String rolActual){
       
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("|                        GESTION DE EMPLEADOS                      |");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("| 1. Crear nuevo empleado                                          |");
            System.out.println("| 2. Consultar empleado                                            |");
            System.out.println("| 3. Editar empleado                                               |");
            System.out.println("| 4. Mostrar todos los empleados                                   |");
            System.out.println("| 9. Atras                                                         |");
            System.out.println("| 0. Salir                                                         |");
            System.out.println("--------------------------------------------------------------------\n");

            System.out.print("Seleccione una opción: ");
            
            int selecciondeUsuario = scanner.nextInt();
            scanner.nextLine();

            switch (selecciondeUsuario) {
                case 1:
                    LimpiarPantalla.limpiarConsola();
                    NuevoEmpleado.crearNuevoEmpleado();
                    break;
                case 2:
                    LimpiarPantalla.limpiarConsola();
                    ConsultaEmpleado.consultarEmpleadoExistente();
                    break;
                case 3:
                    LimpiarPantalla.limpiarConsola();
                   System.out.println("--------------------------------------------------------------------");
                    System.out.println("|                      EDICIÓN DE EMPLEADOS                       |");
                    System.out.println("--------------------------------------------------------------------\n");

                    System.out.print("- Ingrese la identificación del empleado a editar: ");
                    String identificacion = scanner.nextLine();
                    
                    LimpiarPantalla.limpiarConsola();
                    EditarEmpleado.editarEmpleadoExistente(identificacion);
                    break;
                case 4:
                    LimpiarPantalla.limpiarConsola();
                    ConsultaEmpleado.consultarTodosLosEmpleados();
                    break;
                case 9:
                    continuar = false;
                    LimpiarPantalla.limpiarConsola();
                    System.out.println("Saliendo de la gestión de Empleados...");
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
                    System.out.println("ERROR: Opción no válida. Intente de nuevo."); 
                    SimulacionPrograma.simulaEjecucion();
            }
        }
    }
}
