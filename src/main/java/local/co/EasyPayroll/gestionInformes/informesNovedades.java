package local.co.EasyPayroll.gestionInformes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import local.co.EasyPayroll.gestionUtilidades.LimpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.SimulacionPrograma;

public class InformesNovedades {
    

     public static void consolidadoNovedades() {

        Scanner scanner = new Scanner(System.in);
        LimpiarPantalla.limpiarConsola();

        System.out.println("----------------------------------------------------");
        System.out.println("|               CONSULTA DE NOVEDADES              |");
        System.out.println("----------------------------------------------------");

        System.out.println("\n- Ingrese la nomenclatura de las novedades de las novedades que desea consultar.");
        System.out.print("\nNOMENCLATURA ESPERADA (1Q-MM-YYYY): ");
        String quincenaMes = scanner.nextLine().toUpperCase();
        
        String rutaArchivo = "novedades_" + quincenaMes + ".txt";
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.out.println("\nERROR: El archivo no existe en la ruta especificada o Operación cancelada.");
            SimulacionPrograma.continuarConTeclado();
            //return;
        }

        System.out.println("\n-------------------------------------------------------------------------------------------------------");
        System.out.println("CONSOLIDADO DE NOVEDADES                                                                               ");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.printf("|%-12s |%-15s |%-10s |%-8s |%-8s |%-8s |%-8s |%-8s |%-8s\n",
            "F. REGISTRO","ID EMPLEADO","PERIODO","HE.D","HE.N","R.N","HE.DDOM","HE.NDOM","REC.DOM");
        System.out.println("-------------------------------------------------------------------------------------------------------");

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                
                if (datos.length >= 9) {
                    System.out.printf("|%-12s |%-15s |%-10s |%-8s |%-8s |%-8s |%-8s |%-8s |%-8s\n",
                        datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6], datos[7], datos[8]);
                }
            }

            System.out.println("-------------------------------------------------------------------------------------------------------");
            SimulacionPrograma.continuarConTeclado();

        } catch (IOException e) {
            System.out.println("\nERROR: No se puede leer el archivo: " + e.getMessage());
            SimulacionPrograma.continuarConTeclado();
        }
    }

}
