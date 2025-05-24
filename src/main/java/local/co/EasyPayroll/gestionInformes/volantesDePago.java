package local.co.EasyPayroll.gestionInformes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import local.co.EasyPayroll.gestionUtilidades.formatoMoneda;
import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;

public class volantesDePago {
    
    
    public static void mostrarVolanteEmpleado() {

        Scanner scanner = new Scanner (System.in);
        limpiarPantalla.limpiarConsola();

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
                    System.out.println("|) Salario base: " + formatoMoneda.formatear(Double.parseDouble(d[2])));
                    System.out.println("|) Auxilio transporte: " +formatoMoneda.formatear(Double.parseDouble(d[3])));
                    System.out.println("|) Recargos: " +formatoMoneda.formatear(Double.parseDouble(d[4])));
                    System.out.println("|) Total devengado: " + formatoMoneda.formatear(Double.parseDouble(d[5])));
                    System.out.println("|) Salud: " + formatoMoneda.formatear(Double.parseDouble(d[6])) + " | Pensión: " + formatoMoneda.formatear(Double.parseDouble(d[7])));
                    System.out.println("=========================================");
                    System.out.println("NETO A PAGAR: " +formatoMoneda.formatear(Double.parseDouble(d[8])));
                    System.out.println("-----------------------------------------");

                    simulacionPrograma.continuarConTeclado();
                    return;
                }
            }
            System.out.println("\nERROR: Empleado no encontrado en la planilla.");
        } catch (IOException e) {
            System.out.println("\nERROR: No fue posible leer el archivo: " + e.getMessage());
        }
    }
}
