package local.co.EasyPayroll.gestionContrato;

import java.util.Scanner;

import local.co.EasyPayroll.GestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionSeguridad.menuPorRolUsuario;

public class gestionContratos {

    public static void gestionContrato(String rolActual) {

        Scanner scanner = new Scanner(System.in);
        
        while (true) {

            limpiarPantalla.limpiarConsola();

            System.out.println("\n---------------------------------");
            System.out.println("|      GESTION DE CONTRATOS     |");
            System.out.println("---------------------------------");
            System.out.println("| 1. Crear nuevo contrato       |");
            System.out.println("| 2. Consultar contrato         |");
            System.out.println("| 3. Editar contrato            |");
            System.out.println("| 4. Consolidado de Contratos   |");
            System.out.println("| 5. Atras                      |");
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
                    editarContrato.editarContratoGuardado(numeroContratoEditar);
                    break;
                case 4:
                    limpiarPantalla.limpiarConsola();
                    consultaContrato.mostrarContratos();
                    break;
                case 5:
                    menuPorRolUsuario.menuPrincipalUsuario(rolActual);
                    break;
                case 0:
                   System.out.println("Operación cancelada por el usuario.");
                   System.out.println("Saliendo...");
                   System.exit(0);
                   return;
                default:
                   System.out.println("Opción no válida. Intente de nuevo.");
            }
        } 
    }
}