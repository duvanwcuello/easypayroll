package local.co.EasyPayroll.gestionInformes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import local.co.EasyPayroll.gestionNomina.calculoNovedades;
import local.co.EasyPayroll.gestionNomina.empleadoNomina;
import local.co.EasyPayroll.gestionNomina.procesarNominaMes;
import local.co.EasyPayroll.gestionParametrosLegales.parametrosLegalesGenerales;
import local.co.EasyPayroll.gestionUtilidades.formateadorTextro;
import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;

public class informePlanillaPagos {

    public static void mostrarPlanilla() {

    Scanner scanner = new Scanner(System.in);

    limpiarPantalla.limpiarConsola();
    
    System.out.println("\n------------------------------------------");
    System.out.println("|         GENERAR PLANILLA DE PAGO       |");
    System.out.println("------------------------------------------\n");

    System.out.print("- Ingrese el mes de la planilla (1Q-MM-YYYY): ");
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
            double auxTransporte = salario <= (parametrosLegalesGenerales.conceptosLegales.getSalarioMinimo() * 2) ? parametrosLegalesGenerales.conceptosLegales.getAuxTransporte() : 0;

            // Descuentos
            double salud = salario * parametrosLegalesGenerales.conceptosLegales.getSaludEmpleado();
            double pension = salario * parametrosLegalesGenerales.conceptosLegales.getPensionEmpleado();
            double totalDescuentos = salud + pension;

            // Total a pagar
            double totalPagar = salario + auxTransporte + totalNovedades - totalDescuentos;

            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                id,
                emp.getNombre(),
                formateadorTextro.formatearMoneda(salario),
                formateadorTextro.formatearMoneda(auxTransporte),
                formateadorTextro.formatearMoneda(totalNovedades),
                formateadorTextro.formatearMoneda(salud),
                formateadorTextro.formatearMoneda(pension),
                formateadorTextro.formatearMoneda(totalDescuentos),
                formateadorTextro.formatearMoneda(totalPagar));;
        }
        
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        simulacionPrograma.continuarConTeclado();

    } catch (IOException e) {

        System.out.println("Error al leer la planilla: " + e.getMessage());
    }
}
    
}
