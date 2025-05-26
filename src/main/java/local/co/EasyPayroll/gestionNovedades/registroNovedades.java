package local.co.EasyPayroll.gestionNovedades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import local.co.EasyPayroll.gestionUtilidades.datosDeUsoGeneral;
import local.co.EasyPayroll.gestionUtilidades.formateadorTextro;
import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;

public class registroNovedades {

     public static void registrarNovedadesxEmpleado() {
        
        Scanner scanner = new Scanner(System.in);
        limpiarPantalla.limpiarConsola();

        System.out.println("----------------------------------------------------");
        System.out.println("|        REGISTRO DE NOVEDADES POR EMPLEADO        |");
        System.out.println("----------------------------------------------------");

        System.out.println("\n- Ingrese la nomenclatura de las novedades de las novedades que desea consultar.");
        System.out.print("\nNOMENCLATURA ESPERADA (1Q-MM-YYYY): ");

        String novedades = null;
        String quincenaMes = null;
        
        while (novedades == null) {
            quincenaMes = scanner.nextLine().toUpperCase();

            if (quincenaMes.isEmpty() || quincenaMes == null || quincenaMes.length() != 10) {
                System.out.println("\nERROR: Formato incorrecto, por favor, intente nuevamente.");
                simulacionPrograma.continuarConTeclado();
                return;
            }else{                
                novedades = "novedades_" + quincenaMes + ".txt";
                System.out.println("\n-------------------------------------------------------");
                System.out.println("Documento en edición: " + novedades);
            }
        }

        System.out.print("\n- Ingrese la identificación del empleado: ");
        String idEmpleado = scanner.nextLine().trim();

        boolean empleadoExiste = false;

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoContratos()))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                
                if (datos[2].trim().equals(idEmpleado)) {
                    empleadoExiste = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("\nERROR: No se pudo leer empleados.txt: " + e.getMessage());
            simulacionPrograma.continuarConTeclado();
            return;
        }
        
        if (!empleadoExiste) {
            
            System.out.println("\nERROR: El empleado con identificación " + idEmpleado + " no existe,");
            System.out.println("INFO: Por favor, Dirijase al modulo de empleados y registrelo.");
            simulacionPrograma.continuarConTeclado();
            return;
        }

        boolean novedadExiste = false;
        String rutaArchivo = novedades;
        File archivoNovedades = new File(rutaArchivo);

        // Verifica que el archivo de novedades exista antes de intentar leer o escribir
        if (!archivoNovedades.exists()) {
            System.out.println("\nERROR: El archivo de novedades no existe. No se puede registrar la novedad.");
            simulacionPrograma.continuarConTeclado();
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivoNovedades))){
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length > 1 && datos[1].trim().equals(idEmpleado)){
                    novedadExiste = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("\nERROR: No se pudo leer el archivo: " + e.getMessage());
            simulacionPrograma.continuarConTeclado();
            return;
        }
        if (novedadExiste) {
            System.out.println("\nERROR: Ya existe un registro de novedades para el empleado " + idEmpleado + " en el archivo " + rutaArchivo);
            simulacionPrograma.continuarConTeclado();
            return;
        }
        System.out.print("\n) DV08 - Horas extra diurnas: ");
        double hed = scanner.nextDouble();

        System.out.print(") DV09 - Horas extra nocturnas: ");
        double hen = scanner.nextDouble();

        System.out.print(") DV05 - Recargos nocturnos: ");
        double rn = scanner.nextDouble();

        System.out.print(") DV10 - Horas extra dominicales: ");
        double hedDom = scanner.nextDouble();

        System.out.print(") DV11 - Horas nocturnas dominicales: ");
        double henDom = scanner.nextDouble();

        System.out.print(") DV07 - Recargos dominicales: ");
        double recDom = scanner.nextDouble();
        scanner.nextLine();

        guardarNovedades(rutaArchivo, idEmpleado, quincenaMes, hed, hen, rn, hedDom, henDom, recDom);

        System.out.println("\nSUCCES: Registro guardado correctamente para el empleado: " + idEmpleado);

        simulacionPrograma.continuarPrograma();
    
    }


    public static void registroMasivoNovedades() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------------------------------------");
        System.out.println("|            REGISTRO MASIVO DE NOVEDADES          |");
        System.out.println("----------------------------------------------------");

        System.out.println("\n1. CREACIÓN DE ARCHIVO DE NOVEDADES                  ");
        System.out.print("\n- Ingrese el prefijo 1Q mas el mes y año de las novedades, Ej: 1Q-MM-YYYY: ");

        String archivoNovedades = null;
        String quincenaMes = null;
        
        while (quincenaMes == null) {
            quincenaMes = scanner.nextLine().toUpperCase();

            if (quincenaMes.isEmpty() || quincenaMes == null || quincenaMes.length() != 10) {
                System.out.println("\nERROR: Formato incorrecto, por favor, intente nuevamente.");
                simulacionPrograma.continuarConTeclado();
                return;
            }else{                
                archivoNovedades = "novedades_" + quincenaMes + ".txt";
                System.out.println("\nDocumento asignado: " + archivoNovedades);
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoContratos()))) {

            String linea;
            System.out.println("\n2. REGISTRO DE LAS NOVEDADES\n");

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                if (!datos[1].equalsIgnoreCase("A")) 
                continue;

                String nombreEmpleado = datos[5] + " " + datos[7] + " " + datos[8];
                double salarioEmpleado = Double.parseDouble(datos[17]);

                System.out.println("Empleado: " + nombreEmpleado);
                System.out.println("Salario base: " + formateadorTextro.formatearMoneda(salarioEmpleado));

                System.out.print("\nDV08 - Horas extra diurnas: "); 
                double hed = scanner.nextDouble();

                System.out.print("DV09 - Horas extra nocturnas: "); 
                double hen = scanner.nextDouble();

                System.out.print("DV05 - Recargos nocturnos: "); 
                double rn = scanner.nextDouble();

                System.out.print("DV10 - Horas extra dominicales: "); 
                double hedDom = scanner.nextDouble();

                System.out.print("DV11 - Horas nocturnas dominicales: "); 
                double henDom = scanner.nextDouble();

                System.out.print("DV07 - Recargos dominicales: "); 
                double recDom = scanner.nextDouble();
                scanner.nextLine();

                guardarNovedades(archivoNovedades, datos[4], quincenaMes, hed, hen, rn, hedDom, henDom, recDom);
                System.out.println("\nSUCCES: registro guardado para el empleado: " + nombreEmpleado);
                System.out.println("----------------------------------------------------\n");
            }

            if (linea == null) {
                System.out.println("\nSUCCES: Registro exitoso.");                
                simulacionPrograma.continuarConTeclado();
            }               
        } catch (IOException e) {
            System.out.println("\nERROR: No se guardaron las novedades: " + e.getMessage());
            simulacionPrograma.continuarConTeclado();
        } 
    }



     private static void guardarNovedades(String archivo, String id, String periodo, double hed, double hen, double rn,double hedDom, double henDom, double recDom){

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(LocalDate.now() + "," + id + "," + periodo + "," + hed + "," + hen + "," + rn + "," + hedDom + "," + henDom + "," + recDom);
            bw.newLine();

        } catch (IOException e){
            System.out.println("\nERROR: No se guardaron las novedades: " + e.getMessage());
            simulacionPrograma.continuarConTeclado();
        }
    }

    
}
