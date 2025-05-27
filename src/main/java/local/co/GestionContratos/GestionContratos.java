package local.co.GestionContratos;

import java.util.Scanner;

import local.co.GestionUtilidades.LimpiarPantalla;
import local.co.GestionUtilidades.SimulacionPrograma;


public class GestionContratos {

    public static void gestionContrato(String rolActual) {

        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        
        while (continuar) {
            LimpiarPantalla.limpiarConsola();
            System.out.println("\n--------------------------------------------------------------------");;
            System.out.println("|                         GESTION DE CONTRATOS                     |");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("| 1. Crear nuevo contrato                                          |");
            System.out.println("| 2. Consultar contrato                                            |");
            System.out.println("| 3. Editar contrato                                               |");
            System.out.println("| 4. Consolidado de Contratos                                      |");
            System.out.println("| 9. Atras                                                         |");
            System.out.println("| 0. Salir                                                         |");
            System.out.println("--------------------------------------------------------------------\n");

            System.out.print("- Seleccione una opción: "); 
            int selecciondeUsuario = scanner.nextInt();
            scanner.nextLine();

            switch (selecciondeUsuario) {
                case 1:
                    LimpiarPantalla.limpiarConsola();
                    NuevoContrato.crearNuevoContrato();
                    break;
                case 2:
                    LimpiarPantalla.limpiarConsola();
                    ConsultaContrato.consultarContratoExistente();
                    break;
                case 3:
                    LimpiarPantalla.limpiarConsola();
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("|                          EDICIÓN DE CONTRATOS                     |");
                    System.out.println("--------------------------------------------------------------------\n");
                    System.out.print("- Ingrese el Numero de contrato a editar: ");
                    String numeroContratoEditar = scanner.nextLine();
                    EditarContrato.editarContrato(numeroContratoEditar);
                    break;
                case 4:
                    LimpiarPantalla.limpiarConsola();
                    ConsultaContrato.consultarContratosExistentes();
                    break;
                case 9:
                    continuar = false;
                    LimpiarPantalla.limpiarConsola();
                    System.out.println("Saliendo de la gestión de Contratos...");
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
                    SimulacionPrograma.simulaEjecucion();   ;

                    System.out.println("Cerrando Programa...");
                    SimulacionPrograma.continuarPrograma();

                    LimpiarPantalla.limpiarConsola();
                    System.exit(0);
                    break;
                default:
                    System.out.println("ERROR: Opción no válida. Intente de nuevo."); 
            }
        } 
    }
}