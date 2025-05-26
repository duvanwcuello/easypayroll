package local.co.EasyPayroll.gestionParametrosLegales;

import java.util.Scanner;

import local.co.EasyPayroll.gestionParametrosLegales.parametrosLegalesGenerales.conceptosLegales;
import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;

public class gestionParametros {
    
    public static void menuGestionParametros(){

    Scanner scanner = new Scanner(System.in);
    boolean continuar = true;


        while (continuar) {
            System.out.println("-----------------------------------------------------");
            System.out.println("|       GESTIÓN DE PARÁMETROS GENERALES             |");
            System.out.println("-----------------------------------------------------");
            System.out.println("| 1. Ver parámetros actuales                        |");
            System.out.println("| 2. Editar salario mínimo                          |");
            System.out.println("| 3. Editar salario integral                        |");
            System.out.println("| 4. Editar auxilio de transporte                   |");
            System.out.println("| 5. Editar porcentaje salud (Empleado/Empleador)   |");
            System.out.println("| 6. Editar porcentaje pensión (Empleado/Empleador) |");
            System.out.println("| 7. Editar periodicidad de nómina                  |");
            System.out.println("| 9. Regresar                                       |");
            System.out.println("| 0. Salir                                          |");
            System.out.println("|===================================================|");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){
                case 1:
                    limpiarPantalla.limpiarConsola();
                    visualizarParametrosLegales.parametosLegalesActuales();
                    break;                    
                case 2:
                    limpiarPantalla.limpiarConsola();
                    editarParametrosLegales.actualizarSalarioMinimo();
                    break;
                case 3:
                    System.out.print("Nuevo salario integral: ");
                    double nuevoSalarioIntegral = scanner.nextDouble();
                    conceptosLegales.setSalarioIntegral(nuevoSalarioIntegral);
                    System.out.println("Salario integral actualizado.");
                    break;
                case 4:
                    System.out.print("Nuevo auxilio de transporte: ");
                    double nuevoAux = scanner.nextDouble();
                    conceptosLegales.setAuxTransporte(nuevoAux);
                    System.out.println("Auxilio actualizado.");
                    break;
                case 5:
                    System.out.print("Nuevo porcentaje salud empleado (ej. 0.04): ");
                    double saludEmp = scanner.nextDouble();
                    System.out.print("Nuevo porcentaje salud empleador (ej. 0.085): ");
                    double saludEmpr = scanner.nextDouble();
                    conceptosLegales.setSaludEmpleado(saludEmp);
                    conceptosLegales.setSaludEmpleador(saludEmpr);
                    System.out.println("Porcentajes de salud actualizados.");
                    break;
                case 6:
                    System.out.print("Nuevo porcentaje pensión empleado (ej. 0.04): ");
                    double pensEmp = scanner.nextDouble();
                    System.out.print("Nuevo porcentaje pensión empleador (ej. 0.12): ");
                    double pensEmpr = scanner.nextDouble();
                    conceptosLegales.setPensionEmpleado(pensEmp);
                    conceptosLegales.setPensionEmpleador(pensEmpr);
                    System.out.println("Porcentajes de pensión actualizados.");
                    break;
                case 7:
                    System.out.print("Nueva periodicidad de nómina (15 o 30): ");
                    int nuevaPeriodicidadNomina = scanner.nextInt();

                    if (nuevaPeriodicidadNomina == 15 || nuevaPeriodicidadNomina == 30){
                        conceptosLegales.setPeriodicidadNomina(nuevaPeriodicidadNomina);
                        System.out.println("Periodicidad actualizada.");
                    } else {
                        System.out.println("Valor inválido. Debe ser 15 o 30.");
                    }
                    break;
                case 9:
                    continuar = false;
                    limpiarPantalla.limpiarConsola();
                    System.out.println("Saliendo de la gestión de Empleados...");
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
            }
        }
    }
}

