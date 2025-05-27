package local.co.EasyPayroll.GestionInformes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import local.co.EasyPayroll.GestionUtilidades.FormateadorTextro;
import local.co.EasyPayroll.GestionUtilidades.LimpiarPantalla;
import local.co.EasyPayroll.GestionUtilidades.SimulacionPrograma;

public class VolantesDePago {
    
    
    public static void mostrarVolanteEmpleado() {

        Scanner scanner = new Scanner (System.in);
        LimpiarPantalla.limpiarConsola();

        System.out.println("\n------------------------------------------");
        System.out.println("|         GENERAR VOLANTE DE PAGO         |");
        System.out.println("------------------------------------------\n");

        System.out.print("- Mes de la planilla (1Q-MMMYYYY): ");
        String mes = scanner.nextLine().toUpperCase();

        System.out.print("- Identificación del empleado: ");
        String id = scanner.nextLine();

        String archivo = "planilla_" + mes + ".txt";

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");

                if (d[0].equals(id)) {
                    System.out.println("\n============ VOLANTE DE PAGO ============");
                    System.out.println("|) Empleado: " + d[1]);
                    System.out.println("|) Salario base: " + FormateadorTextro.formatearMoneda(Double.parseDouble(d[2])));
                    System.out.println("|) Auxilio transporte: " +FormateadorTextro.formatearMoneda(Double.parseDouble(d[3])));
                    System.out.println("|) Recargos: " +FormateadorTextro.formatearMoneda(Double.parseDouble(d[4])));
                    System.out.println("|) Total devengado: " + FormateadorTextro.formatearMoneda(Double.parseDouble(d[5])));
                    System.out.println("|) Salud: " + FormateadorTextro.formatearMoneda(Double.parseDouble(d[6])) + " | Pensión: " + FormateadorTextro.formatearMoneda(Double.parseDouble(d[7])));
                    System.out.println("=========================================");
                    System.out.println("NETO A PAGAR: " +FormateadorTextro.formatearMoneda(Double.parseDouble(d[8])));
                    System.out.println("-----------------------------------------");

                    SimulacionPrograma.continuarConTeclado();
                    return;
                }
            }
            System.out.println("\nERROR: Empleado no encontrado en la planilla.");
        } catch (IOException e) {
            System.out.println("\nERROR: No fue posible leer el archivo: " + e.getMessage());
        }
    }
}
