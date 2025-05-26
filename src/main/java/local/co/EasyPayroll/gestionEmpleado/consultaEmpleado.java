package local.co.EasyPayroll.gestionEmpleado;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import local.co.EasyPayroll.gestionUtilidades.datosDeUsoGeneral;
import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;

public class consultaEmpleado {
    
    public static void consultarEmpleadoExistente() {
       
        Scanner scanner = new Scanner(System.in);

        limpiarPantalla.limpiarConsola();

        System.out.println("-----------------------------------------------------");
        System.out.println("|                 CONSULTAR EMPLEADO                |");
        System.out.println("----------------------------------------------------\n");

        System.out.print("- Ingrese la identificación del empleado a consultar: ");

        String identificacionEmpleado = scanner.nextLine();
        boolean empleadoEncontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoEmpleados()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");          
                if (datos.length < 15) continue;
                if (datos[1].equals(identificacionEmpleado)) {
                    System.out.println("\n-----------------------------------------------------------");
                    System.out.println("|                   INFORMACIÓN EMPLEADO                  |");
                    System.out.println("-----------------------------------------------------------");
                    System.out.println("| ID UNICO: " + datos[0] + "  |  IDENTIFICACIÓN: " + datos[1]+"             |");
                    System.out.println("-----------------------------------------------------------");
                    if (datos[3].isEmpty()){
                        System.out.println("| * Nombre Completo: " +datos[2]+ " " +datos[4]+ " " +datos[5]);
                    } else {
                        System.out.println("| * Nombre Completo: " +datos[2]+ " " +datos[3] + " " +datos[4]+ " " +datos[5]);
                    }
                    System.out.println("| * Fecha Nacimiento: " +datos[6]);
                    System.out.println("| * Estado Civil: " + datos[9]);
                    System.out.println("| * Nivel de Estudio: " + datos[10]);
                    System.out.println("| * Correo Electrónico: " + datos[11]);
                    System.out.println("| * Dirección: " + datos[12] + " - Barrio: " + datos[13]);
                    System.out.println("---------------------------------------------------------------");

                    empleadoEncontrado = true;
                    simulacionPrograma.continuarConTeclado();
                    limpiarPantalla.limpiarConsola();
                    break;
                }
            } 
        } catch (IOException e) {
          System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        
        // Si el empleado no fue encontrado, se ofrece al usuario la opción de crear uno nuevo
        if (!empleadoEncontrado) {
            limpiarPantalla.limpiarConsola();

            System.out.println("---------------------------------");
            System.out.println("| ERROR: Empleado no encontrado |");
            System.out.println("---------------------------------\n");

            System.out.print("¿Desea registrar un nuevo empleado? (1 = SI | 2 = NO): ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                nuevoEmpleado.crearNuevoEmpleado();
            } else {
                limpiarPantalla.limpiarConsola();
            }
        }    
    }
    
    public static void consultarTodosLosEmpleados() {

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoEmpleados()))) {

            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("                                                 MAESTRO DE EMPLEADOS");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s | %-15s | %-15s | %-15s | %-20s | %-30s | %-30s\n",
                    "ID", "IDENTIFICACIÓN", "NOMBRE", "APELLIDO", "F. NACIMIENTO", "CORREO", "DIRECCIÓN");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
            
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");
                if (d.length >= 16) {
                    System.out.printf("%-5s | %-15s | %-15s | %-15s | %-20s | %-30s | %-30s\n",
                            d[0], d[1], d[2], d[4], d[6], d[11], d[12]);
                }
            }
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
            simulacionPrograma.continuarConTeclado();
            limpiarPantalla.limpiarConsola();
        } catch (IOException e) {
            System.out.println("\n-------------------------------------------------------");
            System.out.println("| ERROR: No se pudo leer el archivo de empleados. " + e.getMessage()+ "|");
            System.out.println("-------------------------------------------------------\n");
            simulacionPrograma.continuarConTeclado();
            limpiarPantalla.limpiarConsola();
        }
    }
}