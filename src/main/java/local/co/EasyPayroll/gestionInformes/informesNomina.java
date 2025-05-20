package local.co.EasyPayroll.gestionInformes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


import local.co.EasyPayroll.utilidades.formatoMoneda;
import local.co.EasyPayroll.gestionNomina.*;
import local.co.EasyPayroll.gestionNovedades.calculoNovedades;
import local.co.EasyPayroll.gestionParametrosGenerales.*;

public class informesNomina {

    public static void motrarInformes(){
        Scanner scanner = new Scanner (System.in);

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n---------------------------------");
            System.out.println("MOSTRAR INFORMES DE NOMINA");
            System.out.println("-----------------------------------");  
            System.out.println("1. Planilla de pago de Nomina");
            System.out.println("2. Mostrar Volante de pago");
            System.out.println("0. Salir");
            System.out.println("----------------------------------");  
            System.out.print("Seleccione una Opcion: ");
            int seleccion= scanner.nextInt();
            switch(seleccion){
                case 1:
                mostrarPlanilla();
                break;
            case 2:
                mostrarVolanteEmpleado();
                break;
            case 3:
                continuar = false;
                System.out.println("Saliendo de Informes...");
                break;               
            default:
                scanner.close();
                System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
            
    }
    
    
    public static void mostrarPlanilla() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingrese el mes de la planilla (1Q-MMMYYYY): ");
    String planillaMes = scanner.nextLine();
    String archivoNovedades = "novedades_" + planillaMes.toLowerCase() + ".txt";
    
    List<empleadoNomina> empleados = procesarNominaMes.cargarEmpleadosDesdeArchivo();

    System.out.println("\nPLANILLA DE PAGO DE NÓMINA - " + planillaMes.toUpperCase());
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
    System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
            "IDENTIFICACION", "EMPLEADO", "SALARIO BASE", "AUX TRANSPORTE", "TOTAL NOVEDADES", "SALUD", "PENSION", "DESCUENTO", "TOTAL A PAGAR");
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

    try (BufferedReader br = new BufferedReader(new FileReader(archivoNovedades))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");
            if (datos.length < 9) continue;

            String id = datos[1];
            double hed = Double.parseDouble(datos[3]);
            double hen = Double.parseDouble(datos[4]);
            double rn = Double.parseDouble(datos[5]);
            double hedDom = Double.parseDouble(datos[6]);
            double henDom = Double.parseDouble(datos[7]);
            double recDom = Double.parseDouble(datos[8]);

            Optional<empleadoNomina> empOpt = empleados.stream().filter(e -> e.getIdentificacion().equals(id)).findFirst();
            if (empOpt.isEmpty()) continue;

            empleadoNomina emp = empOpt.get();
            double salario = emp.getSalarioBase();

            // Calcular novedades
            double totalNovedades = 0;
            if (hed > 0) totalNovedades += calculoNovedades.calculoHorasExtrasDiurnas(salario, hed);
            if (hen > 0) totalNovedades += calculoNovedades.calculoHorasExtrasNocturnas(salario, hen);
            if (rn > 0) totalNovedades += calculoNovedades.calculoRecargoNocturno(salario, rn);
            if (hedDom > 0) totalNovedades += calculoNovedades.calculoHorasExtrasDiurnasDomYFest(salario, 15, hedDom);
            if (henDom > 0) totalNovedades += calculoNovedades.calculoHorasExtrasNocturnasDomYFest(salario, 15, henDom);
            if (recDom > 0) totalNovedades += calculoNovedades.calculoRecargoDomYFest(salario, 15, recDom);

            // Auxilio transporte
            double auxTransporte = salario <= (parametrosGenerales.conceptosLegales.getSalarioMinimo() * 2) ? parametrosGenerales.conceptosLegales.getAuxTransporte() : 0;

            // Descuentos
            double salud = salario * parametrosGenerales.conceptosLegales.getSaludEmpleado();
            double pension = salario * parametrosGenerales.conceptosLegales.getPensionEmpleado();
            double totalDescuentos = salud + pension;

            // Total a pagar
            double totalPagar = salario + auxTransporte + totalNovedades - totalDescuentos;

            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                id,
                emp.getNombre(),
                formatoMoneda.formatear(salario),
                formatoMoneda.formatear(auxTransporte),
                formatoMoneda.formatear(totalNovedades),
                formatoMoneda.formatear(salud),
                formatoMoneda.formatear(pension),
                formatoMoneda.formatear(totalDescuentos),
                formatoMoneda.formatear(totalPagar));;
        }
         System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        
    } catch (IOException e) {
        System.out.println("Error al leer la planilla: " + e.getMessage());
    }
}

    public static void mostrarVolanteEmpleado() {
        Scanner scanner = new Scanner (System.in);

        System.out.print("Mes de la planilla (1Q-MMMYYYY): ");
        String mes = scanner.nextLine().toUpperCase();
        System.out.print("Identificación del empleado: ");
        String id = scanner.nextLine();
        String archivo = "planilla_" + mes + ".txt";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");
                if (d[0].equals(id)) {
                    System.out.println("\n-----------------------------------------");
                    System.out.println("============ VOLANTE DE PAGO ============");
                    System.out.println("Empleado: " + d[1]);
                    System.out.println("Salario base: " + formatoMoneda.formatear(Double.parseDouble(d[2])));
                    System.out.println("Auxilio transporte: " +formatoMoneda.formatear(Double.parseDouble(d[3])));
                    System.out.println("Recargos: " +formatoMoneda.formatear(Double.parseDouble(d[4])));
                    System.out.println("Total devengado: " + formatoMoneda.formatear(Double.parseDouble(d[5])));
                    System.out.println("Salud: " + formatoMoneda.formatear(Double.parseDouble(d[6])) + " | Pensión: " + formatoMoneda.formatear(Double.parseDouble(d[7])));
                    System.out.println("NETO A PAGAR: " +formatoMoneda.formatear(Double.parseDouble(d[8])));
                    return; 
                }
            }
            System.out.println("Empleado no encontrado en la planilla.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}

