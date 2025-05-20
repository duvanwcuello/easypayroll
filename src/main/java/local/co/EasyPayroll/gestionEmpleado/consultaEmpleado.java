package local.co.EasyPayroll.gestionEmpleado;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import local.co.EasyPayroll.utilidades.datosDeUsoGeneral;

public class consultaEmpleado {
    
    public static void consultarEmpleadoExistente() {
       
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nIngrese la identificación del empleado a consultar: ");
        String identificacionEmpleado = scanner.nextLine();
        boolean empleadoEncontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoEmpleados()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                
                if (datos.length < 14) continue;

                if (datos[1].equals(identificacionEmpleado)) {
                    System.out.println("\nInformación del empleado:");
                    System.out.println("----------------------------------------------------");
                    System.out.println("ID UNICO: " + datos[0] +"  |  Identificación: " + datos[1]);
                    System.out.println("Nombre Completo: " + datos[2]+" "+datos[4]+" "+datos[5]);
                    System.out.println("Fecha Nacimiento: " + datos[6]);
                    System.out.println("Estado Civil: " + datos[9]);
                    System.out.println("Nivel de Estudio: " + datos[10]);
                    System.out.println("Correo Electrónico: " + datos[11]);
                    System.out.println("Dirección: " + datos[12]+" - Barrio: "+datos[13]);
                    System.out.println("-----------------------------------------------------");
                    empleadoEncontrado = true;
                    break;
                }
            } System.out.println("--------------------------");
              System.out.println("¡¡Empleado no encontrado.!!");
              System.out.println("--------------------------\n");
         
        } catch (IOException e) {
          System.out.println("Error al leer el archivo: " + e.getMessage());
         // return;
        }
        
        // Si el empleado no fue encontrado, se ofrece al usuario la opción de crear uno nuevo
        if (!empleadoEncontrado) {
            System.out.println("Empleado no encontrado.");
            System.out.print("¿Desea registrar un nuevo empleado? (1 = Sí, 2 = No): ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer
            if (opcion == 1) {
                nuevoEmpleado.crearNuevoEmpleado();
            } else {
                System.out.println("Operación cancelada.");
            }
        }
           
    }
    

     /**
     * Muestra todos los empleados en formato de una tabla.
     */
    public static void mostrarEmpleados() {
        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoEmpleados()))) {
            System.out.println("\n\t\t\t\t\tCONSOLIDADO DE EMPLEADOS.");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s | %-15s | %-15s | %-15s | %-20s | %-30s | %-30s\n",
                    "ID", "Identificación", "Nombre", "Apellido", "FecNacimiento", "Correo", "Dirección");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");
                if (d.length >= 16) {
                    System.out.printf("%-5s | %-15s | %-15s | %-15s | %-20s | %-30s | %-30s\n",
                            d[0], d[1], d[2], d[4], d[6], d[11], d[12]);
                }
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        } catch (IOException e) {
            System.out.println("Error al leer empleados: " + e.getMessage());
        }
    }
    
}
