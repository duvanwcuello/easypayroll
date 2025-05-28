package local.co.GestionInformes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import local.co.GestionNomina.CalculoNovedades;
import local.co.GestionNomina.EmpleadoNomina;
import local.co.GestionNomina.ProcesarNominaMes;
import local.co.GestionParametrosLegales.ParametrosLegalesGenerales;
import local.co.GestionUtilidades.FormateadorTextro;
import local.co.GestionUtilidades.LimpiarPantalla;
import local.co.GestionUtilidades.SimulacionPrograma;



public class InformePlanillaPagos {

    public static void mostrarPlanilla() {

    Scanner scanner = new Scanner(System.in);

    LimpiarPantalla.limpiarConsola();
    
    System.out.println("--------------------------------------------------------------------");
    System.out.println("|                     GENERAR PLANILLA DE PAGO                     |");
    System.out.println("--------------------------------------------------------------------\n");

    System.out.print("- Ingrese el mes de la planilla (1Q-MM-YYYY): ");
    String planillaMes = scanner.nextLine();

    String archivoNovedades = "novedades_" + planillaMes.toLowerCase() + ".txt";

    List<EmpleadoNomina> empleados = ProcesarNominaMes.cargarEmpleadosDesdeArchivo();

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

            Optional<EmpleadoNomina> empOpt = empleados.stream().filter(e -> e.getIdentificacion().equals(id)).findFirst();
            
            if (empOpt.isEmpty()) continue;

            EmpleadoNomina emp = empOpt.get();
            double salario = emp.getSalarioBase();

            // Calcular novedades
            double totalNovedades = 0;

            if (hed > 0) totalNovedades += CalculoNovedades.calculoHorasExtrasDiurnas(salario, hed);
            if (hen > 0) totalNovedades += CalculoNovedades.calculoHorasExtrasNocturnas(salario, hen);
            if (rn > 0) totalNovedades += CalculoNovedades.calculoRecargoNocturno(salario, rn);
            if (hedDom > 0) totalNovedades += CalculoNovedades.calculoHorasExtrasDiurnasDomYFest(salario, 15, hedDom);
            if (henDom > 0) totalNovedades += CalculoNovedades.calculoHorasExtrasNocturnasDomYFest(salario, 15, henDom);
            if (recDom > 0) totalNovedades += CalculoNovedades.calculoRecargoDomYFest(salario, 15, recDom);

            // Auxilio transporte
            double auxTransporte = salario <= (ParametrosLegalesGenerales.conceptosLegales.getSalarioMinimo() * 2) ? ParametrosLegalesGenerales.conceptosLegales.getAuxTransporte() : 0;

            // Descuentos
            double salud = salario * ParametrosLegalesGenerales.conceptosLegales.getSaludEmpleado();
            double pension = salario * ParametrosLegalesGenerales.conceptosLegales.getPensionEmpleado();
            double totalDescuentos = salud + pension;

            // Total a pagar
            double totalPagar = salario + auxTransporte + totalNovedades - totalDescuentos;

            System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                id,
                emp.getNombre(),
                FormateadorTextro.formatearMoneda(salario),
                FormateadorTextro.formatearMoneda(auxTransporte),
                FormateadorTextro.formatearMoneda(totalNovedades),
                FormateadorTextro.formatearMoneda(salud),
                FormateadorTextro.formatearMoneda(pension),
                FormateadorTextro.formatearMoneda(totalDescuentos),
                FormateadorTextro.formatearMoneda(totalPagar));;
        }
        
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        SimulacionPrograma.continuarConTeclado();

    } catch (IOException e) {

        System.out.println("Error al leer la planilla: " + e.getMessage());
    }
}
    
}
