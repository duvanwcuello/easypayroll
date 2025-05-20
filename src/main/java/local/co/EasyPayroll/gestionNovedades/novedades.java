
package local.co.EasyPayroll.gestionNovedades;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import local.co.EasyPayroll.utilidades.datosDeUsoGeneral;
import local.co.EasyPayroll.utilidades.formatoMoneda;

public class novedades {

    public static void registrarNovedades() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("INGRESE LAS NOVEDADES DEL MES A PROCESAR\\n" + //
                        "Tome Como Referencia este ejemplo: (1Q-MMMYYYY)");
        String quincenaMes = scanner.nextLine().toUpperCase();
        String archivoNovedades = "novedades_" + quincenaMes + ".txt";

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoContratos()))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (!datos[1].equalsIgnoreCase("A")) continue;

                    String nombreEmpleado = datos[5] + " " + datos[7] + " " + datos[8];
                    double salarioEmpleado = Double.parseDouble(datos[17]);

                    System.out.println("--------------------------------------------");
                    System.out.println("REGISTRE LAS NOVEDADES DE LOS EMPLEADO\n");
                    System.out.println("\nEmpleado: " + nombreEmpleado);
                    System.out.println("Salario base: " + formatoMoneda.formatear(salarioEmpleado));
                    System.out.print("DV08 Horas extra diurnas: "); 
                    double hed = scanner.nextDouble();
                    System.out.print("DV09 Horas extra nocturnas: "); 
                    double hen = scanner.nextDouble();
                    System.out.print("DV05 Recargos nocturnos: "); 
                    double rn = scanner.nextDouble();
                    System.out.print("DV10 Horas extra dominicales: "); 
                    double hedDom = scanner.nextDouble();
                    System.out.print("DV11 Horas nocturnas dominicales: "); 
                    double henDom = scanner.nextDouble();
                    System.out.print("DV07 Recargos dominicales: "); 
                    double recDom = scanner.nextDouble();
                    scanner.nextLine();

                    guardarNovedades(archivoNovedades, datos[4], quincenaMes, hed, hen, rn, hedDom, henDom, recDom);
                }
                
            } catch (IOException e) {
                System.out.println("Error al registrar novedades: " + e.getMessage());
        }
   
    }

    private static void guardarNovedades(String archivo, String id, String mes, double hed, double hen, double rn,
                                         double hedDom, double henDom, double recDom) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(LocalDate.now() + "," + id + "," + mes + "," + hed + "," + hen + "," + rn + "," +
                     hedDom + "," + henDom + "," + recDom);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error guardando novedades: " + e.getMessage());
        }
    }
}
