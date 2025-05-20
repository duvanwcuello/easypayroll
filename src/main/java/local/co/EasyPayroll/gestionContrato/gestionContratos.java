package local.co.EasyPayroll.gestionContrato;

import java.util.Scanner;

public class gestionContratos {

     @SuppressWarnings("resource")
     
    public static void gestionContrato(){
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            //System.out.println("\n*********************************");
               System.out.println("\nGESTION DE CONTRATOS");
               System.out.println("-------------------------------");
               System.out.println("1. Crear nuevo contrato");
               System.out.println("2. Consultar contrato existente");
               System.out.println("3. Editar contrato existente");
               System.out.println("4. Mostrar Consolidado de Contratos");
              // por implementar System.out.println("4. Volver al MENU PRINCIPAL");
               System.out.println("5. Salir");
               System.out.println("-------------------------------");
               System.out.print("Seleccione una opción: ");
               int selecciondeUsuario = scanner.nextInt();
               scanner.nextLine(); // Limpiar el buffer

           switch (selecciondeUsuario) {
               case 1:
                   nuevoContrato.crearNuevoContrato();
                   break;
               case 2:
                   consultaContrato.consultarContratoExistente();
                   break;
               case 3:
                    System.out.print("Indique Numero de contrato a Editar: ");
                    String numeroContratoEditar = scanner.nextLine();
                    editarContrato.editarContratoGuardado(numeroContratoEditar);
                   break;
              case 4:
                    consultaContrato.mostrarContratos();
                    break;
               case 5:
                   continuar = false;
                    System.out.println("Saliendo de la gestión de Contratos...");
                    break;
                default:
                    scanner.close();
                    System.out.println("Opción no válida. Intente de nuevo.");
           }
               
        } 
    }
}
