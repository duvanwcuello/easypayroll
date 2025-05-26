package local.co.EasyPayroll.gestionUsuarios;

import java.util.*;

import local.co.EasyPayroll.gestionUtilidades.*;

public class gestionUsuarios {
    /**
     * Muestra un menú interactivo para gestionar usuarios.
     * Permite crear, consultar, editar, mostrar usuarios o salir del sistema.
     */
    public static void menuGestionUsuarios(String rolActual) {
        Scanner scanner = new Scanner(System.in);
        
        boolean continuar = true;       

        while (continuar) {
           System.out.println("----------------------------------");
            System.out.println("|       GESTION DE USUARIOS      |");
            System.out.println("----------------------------------");
            System.out.println("| 1. Crear nuevo Usuario         |");
            System.out.println("| 2. Consulta por Usuario        |");
            System.out.println("| 3. Editar usuario Existente    |");
            System.out.println("| 4. Eliminar Usuario            |");
            System.out.println("| 5. Consulta todos los usuarios |");
            System.out.println("| 9. Atras                       |");
            System.out.println("| 0. Salir                       |");
            System.out.println("----------------------------------\n");
            
            System.out.print("Seleccione una opción: ");
            int selecciondeUsuario = scanner.nextInt();
            scanner.nextLine();

            switch (selecciondeUsuario) {
                case 1:
                    limpiarPantalla.limpiarConsola();
                    simulacionPrograma.simulaEjecucion();;
                    nuevoUsuario.crearNuevoUsuario();
                    break;
                case 2:
                    limpiarPantalla.limpiarConsola();
                    consultarUsuarios.consultarUsuarioExistente();
                   // simulacionPrograma.continuarConTeclado();
                    limpiarPantalla.limpiarConsola();
                    break;
                case 3:
                    limpiarPantalla.limpiarConsola();
                    editarUsuarios.editarUsuarioExistente();
                   // simulacionPrograma.continuarConTeclado();
                    break;
                case 4:
                    limpiarPantalla.limpiarConsola();
                    editarUsuarios.editarUsuarioExistente();
                    break;
                case 5:
                    limpiarPantalla.limpiarConsola();
                    consultarUsuarios.consultarTodosLosUsuarios();
                    simulacionPrograma.continuarConTeclado();
                    limpiarPantalla.limpiarConsola();
                    menuGestionUsuarios(rolActual);
                    return;
                case 9:
                    continuar = false;
                    limpiarPantalla.limpiarConsola();
                    System.out.println("Saliendo de la gestión de Usuarios...");
                    simulacionPrograma.simulaEjecucion();
                    System.err.println("Retrornando al Menu Principal...");
                    simulacionPrograma.simulaEjecucion();
                    limpiarPantalla.limpiarConsola();
                    break;
                case 0:
                    continuar = false;
                        limpiarPantalla.limpiarConsola();
                        System.out.println("¡Operacion Cancelada!...");
                        simulacionPrograma.simulaEjecucion();
                        System.out.println("Cerrando Sesion...");
                        simulacionPrograma.simulaEjecucion();
                        System.out.println("Cerrando Programa...");
                        simulacionPrograma.continuarPrograma();
                        limpiarPantalla.limpiarConsola();
                        System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    simulacionPrograma.simulaEjecucion();
           }
        }
    }
}