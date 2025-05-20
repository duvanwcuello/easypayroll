package local.co.EasyPayroll.gestionParametrosGenerales;

import java.util.Scanner;

import local.co.EasyPayroll.gestionParametrosGenerales.*;
import local.co.EasyPayroll.gestionParametrosGenerales.parametrosGenerales.conceptosLegales;

public class gestionParametros {
    
    public static void menuGestionParametros(){

    Scanner scanner = new Scanner(System.in);
    boolean continuar = true;

        while (continuar) {
            System.out.println("===============================================");
            System.out.println("       GESTIÓN DE PARÁMETROS GENERALES");
            System.out.println("===============================================");
            System.out.println("1. Ver parámetros actuales");
            System.out.println("2. Editar salario mínimo");
            System.out.println("3. Editar salario integral");
            System.out.println("4. Editar auxilio de transporte");
            System.out.println("5. Editar porcentaje salud (Empleado/Empleador)");
            System.out.println("6. Editar porcentaje pensión (Empleado/Empleador)");
            System.out.println("7. Editar periodicidad de nómina");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("Salario mínimo actual: " + conceptosLegales.getSalarioMinimo());
                    System.out.println("Salario integral actual: " + conceptosLegales.getSalarioIntegral());
                    System.out.println("Auxilio de transporte: " + conceptosLegales.getAuxTransporte());
                    System.out.println("Salud - Empleado: " + conceptosLegales.getSaludEmpleado());
                    System.out.println("Salud - Empleador: " + conceptosLegales.getSaludEmpleador());
                    System.out.println("Pensión - Empleado: " + conceptosLegales.getPensionEmpleado());
                    System.out.println("Pensión - Empleador: " + conceptosLegales.getPensionEmpleador());
                    System.out.println("Periodicidad nómina (días): " + conceptosLegales.getPeriodicidadNomina());
                    break;
                case 2:
                    System.out.print("Nuevo salario mínimo: ");
                    double nuevoSalarioMinimo = scanner.nextDouble();
                    conceptosLegales.setSalarioMinimo(nuevoSalarioMinimo);
                    System.out.println("Salario mínimo actualizado.");
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
                    if (nuevaPeriodicidadNomina == 15 || nuevaPeriodicidadNomina == 30) {
                        conceptosLegales.setPeriodicidadNomina(nuevaPeriodicidadNomina);
                        System.out.println("Periodicidad actualizada.");
                    } else {
                        System.out.println("Valor inválido. Debe ser 15 o 30.");
                    }
                    break;
                case 8:
                    continuar = false;
                    System.out.println("Saliendo de la gestión de parámetros...");
                    break;
                default:
                    scanner.close();
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }
}

