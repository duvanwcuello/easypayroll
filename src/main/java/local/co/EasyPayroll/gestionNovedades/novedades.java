
package local.co.EasyPayroll.gestionNovedades;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import local.co.EasyPayroll.seguridad.menuUsuarios;
import local.co.EasyPayroll.utilidades.ContinuaEnter;
import local.co.EasyPayroll.utilidades.datosDeUsoGeneral;
import local.co.EasyPayroll.utilidades.formatoMoneda;
import local.co.EasyPayroll.utilidades.limpiarPantalla;

public class novedades {

    public static void menuNovedades(String rolActual) {

        boolean continuar = true;
        limpiarPantalla.limpiarConsola();

        while (continuar) {

            Scanner scanner = new Scanner(System.in);

            System.out.println("----------------------------------------------------");
            System.out.println("|               GESTIÓN DE NOVEDADES               |");
            System.out.println("----------------------------------------------------");
            System.out.println("| 1. Consulta de novedades                         |");
            System.out.println("| 2. Registro masivo de novedades                  |");
            System.out.println("| 3. Registro de novedades por empleado            |");
            System.out.println("| 4. Atras                                         |");
            System.out.println("| 0. Salir                                         |");
            System.out.println("----------------------------------------------------\n");

            System.out.print("- Seleccione una opción: ");
            int opcionNovedades = scanner.nextInt();
            scanner.nextLine();

            switch (opcionNovedades) {

                case 1:

                    limpiarPantalla.limpiarConsola();
                    consolidadoNovedades();
                    break;

                case 2:

                    limpiarPantalla.limpiarConsola();
                    registroMasivoNovedades();
                    break;

                case 3:

                    limpiarPantalla.limpiarConsola();
                    registrarNovedadesxEmpleado();
                    break;

                case 4:
                    
                    menuUsuarios.menuPrincipalUsuario(rolActual);
                    break;

                case 0:

                    continuar = false;
                    System.out.println("\nOperación cancelada por el usuario.");
                    System.out.println("Saliendo..."); 
                    System.exit(0);

                default:

                    System.out.println("Opción no válida, por favor, seleccione una opción válida.");
                    ContinuaEnter.PressEnter('C');
            }
        }
    }

    public static void consolidadoNovedades() {

        Scanner scanner = new Scanner(System.in);
        limpiarPantalla.limpiarConsola();

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
            ContinuaEnter.PressEnter('C');
            return;
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
            ContinuaEnter.PressEnter('E');

        } catch (IOException e) {

            System.out.println("\nERROR: No se puede leer el archivo: " + e.getMessage());
            ContinuaEnter.PressEnter('C');
        }
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
                ContinuaEnter.PressEnter('C');
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

                if (!datos[1].equalsIgnoreCase("A")) continue;

                String nombreEmpleado = datos[5] + " " + datos[7] + " " + datos[8];
                double salarioEmpleado = Double.parseDouble(datos[17]);

                System.out.println("Empleado: " + nombreEmpleado);
                System.out.println("Salario base: " + formatoMoneda.formatear(salarioEmpleado));

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
                ContinuaEnter.PressEnter('C');

            }
                
        } catch (IOException e) {

            System.out.println("\nERROR: No se guardaron las novedades: " + e.getMessage());
            ContinuaEnter.PressEnter('E');
        }
   
    }

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
                ContinuaEnter.PressEnter('C');
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
            ContinuaEnter.PressEnter('E');
            return;
        }

        if (!empleadoExiste) {
            
            System.out.println("\nERROR: El empleado con identificación " + idEmpleado + " no existe,");
            System.out.println("INFO: Por favor, Dirijase al modulo de empleados y registrelo.");
            ContinuaEnter.PressEnter('E');
            return;
        }

        boolean novedadExiste = false;
        String rutaArchivo = novedades;
        File archivoNovedades = new File(rutaArchivo);

        // Verifica que el archivo de novedades exista antes de intentar leer o escribir
        if (!archivoNovedades.exists()) {

            System.out.println("\nERROR: El archivo de novedades no existe. No se puede registrar la novedad.");
            ContinuaEnter.PressEnter('C');
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(archivoNovedades))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos.length > 1 && datos[1].trim().equals(idEmpleado)) {

                    novedadExiste = true;
                    break;
                }
            }
        } catch (IOException e) {

            System.out.println("\nERROR: No se pudo leer el archivo: " + e.getMessage());
            ContinuaEnter.PressEnter('E');
            return;
        }

        if (novedadExiste) {

            System.out.println("\nERROR: Ya existe un registro de novedades para el empleado " + idEmpleado + " en el archivo " + rutaArchivo);
            ContinuaEnter.PressEnter('E');
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
        ContinuaEnter.PressEnter('E');
    }

    private static void guardarNovedades(String archivo, String id, String periodo, double hed, double hen, double rn,double hedDom, double henDom, double recDom) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {

            bw.write(LocalDate.now() + "," + id + "," + periodo + "," + hed + "," + hen + "," + rn + "," + hedDom + "," + henDom + "," + recDom);
            bw.newLine();

        } catch (IOException e) {

            System.out.println("\nERROR: No se guardaron las novedades: " + e.getMessage());
            ContinuaEnter.PressEnter('E');
        }
    }
}