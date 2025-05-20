package local.co.EasyPayroll.gestionEmpleado;

import java.util.*;
public class gestionEmpleados {

      
    /**
     * Método principal para gestionar empleados.
     * Despliega un menú con opciones para crear, consultar o editar empleados.
     * Ejecuta la acción correspondiente según la opción seleccionada por el usuario.
     * @param rolActual Rol del usuario que está gestionando empleados.
     */
    public static void gestionEmpleado(String rolActual) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("\n*********************************");
            System.out.println("GESTIÓN DE EMPLEADOS");
            System.out.println("-------------------------------");
            System.out.println("1. Crear nuevo empleado");
            System.out.println("2. Consultar empleado existente");
            System.out.println("3. Editar empleado existente");
            System.out.println("4. Mostrar todos los empleados");
            System.out.println("5. Salir");
            System.out.println("-------------------------------");
            System.out.print("Seleccione una opción: ");
            int selecciondeUsuario = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (selecciondeUsuario) {
                case 1:
                    nuevoEmpleado.crearNuevoEmpleado();
                    break;
                case 2:
                    consultaEmpleado.consultarEmpleadoExistente();
                    break;
                case 3:
                    System.out.print("Ingrese Identificacion del Empleado: ");
                    String identificacion = scanner.nextLine();
                    editarEmpleado.editarEmpleadoExistente(identificacion);
                    break;
                case 4:
                    consultaEmpleado.mostrarEmpleados();
                    break;
                case 5:
                    continuar = false;
                    System.out.println("Saliendo de la gestión de Empleados...");
                    break;
                default:
                    scanner.close();
                    System.out.println("Opción no válida. Intente de nuevo.");
                    
            }
        }
    }
}
