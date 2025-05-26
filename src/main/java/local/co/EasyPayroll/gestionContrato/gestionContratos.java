package local.co.EasyPayroll.gestionContrato;

import java.util.Scanner;

import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;

public class gestionContratos {

    public static void gestionContrato(String rolActual) {

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        
        while (continuar) {
            limpiarPantalla.limpiarConsola();
            System.out.println("\n---------------------------------");
            System.out.println("|      GESTION DE CONTRATOS     |");
            System.out.println("---------------------------------");
            System.out.println("| 1. Crear nuevo contrato       |");
            System.out.println("| 2. Consultar contrato         |");
            System.out.println("| 3. Editar contrato            |");
            System.out.println("| 4. Consolidado de Contratos   |");
            System.out.println("| 9. Atras                      |");
            System.out.println("| 0. Salir                      |");
            System.out.println("---------------------------------\n");

            System.out.print("- Seleccione una opción: ");
            int selecciondeUsuario = scanner.nextInt();
            scanner.nextLine();

            switch (selecciondeUsuario) {
                case 1:
                    limpiarPantalla.limpiarConsola();
                    nuevoContrato.crearNuevoContrato();
                    break;
                case 2:
                    limpiarPantalla.limpiarConsola();
                    consultaContrato.consultarContratoExistente();
                    break;
                case 3:
                    limpiarPantalla.limpiarConsola();
                    System.out.println("-------------------------------------------");
                    System.out.println("|           EDICIÓN DE CONTRATOS          |");
                    System.out.println("-------------------------------------------\n");
                    System.out.print("- Ingrese el Numero de contrato a editar: ");
                    String numeroContratoEditar = scanner.nextLine();
                    editarContrato.editarContrato(numeroContratoEditar);
                    break;
                case 4:
                    limpiarPantalla.limpiarConsola();
                    consultaContrato.consultarContratosExistentes();
                    break;
                case 9:
                    continuar = false;
                    limpiarPantalla.limpiarConsola();
                    System.out.println("Saliendo de la gestión de Contratos...");
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
                    simulacionPrograma.simulaEjecucion();   ;

                    System.out.println("Cerrando Programa...");
                    simulacionPrograma.continuarPrograma();

                    limpiarPantalla.limpiarConsola();
                    System.exit(0);
                    break;
                default:
                    System.out.println("ERROR: Opción no válida. Intente de nuevo."); 
            }
        } 
    }
}