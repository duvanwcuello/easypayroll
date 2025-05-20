package local.co.EasyPayroll.gestionContrato;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import local.co.EasyPayroll.utilidades.datosDeUsoGeneral;
import local.co.EasyPayroll.utilidades.*;

public class consultaContrato {
    
    public static void consultarContratoExistente() {
       
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nPara realizar la busqueda del contratato.\npor favor ingrese la identificación del titular: ");
        String identificacionEmpleado = scanner.nextLine();
        boolean contratoEncontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoContratos()))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length < 18) continue;
                double salarioEmpleado = Double.parseDouble(datos[17]);

                if (datos[4].equals(identificacionEmpleado)){
                    System.out.println("----------------------------------------------------");
                    System.out.println("\nCONTRATO "+datos[14]);
                    System.out.println("----------------------------------------------------");
                    System.out.println("No Contrato:\t\t\t"+ datos[2]);//+ " | Tipo de Contrato: "+ datos[14]);
                    System.out.println("fecha Inicial del contrato:\t" +datos[15]);
                    System.out.println("Nombre del Empleado:\t\t" + datos[5]+" "+datos[7]+" "+datos[8]);
                    System.out.println("Identificacion del Empleado:\t" + datos[4]);
                    System.out.println("Direccion:\t\t\t"+ datos[11]+"\nBarrio: \t\t\t"+datos[12]);
                    System.out.println("Numero de Tel/Cel: "+"\t\nCuidad: \t\t\t"+datos[13]);
                    System.out.println("Correo Electrónico: \t\t"+ datos[10]);
                    System.out.println("Cargo Asignado: \t\t" +datos[16]);
                    System.out.println("Salario: \t\t\t" +formatoMoneda.formatear(salarioEmpleado));
                    System.out.println("-----------------------------------------------------");
                    contratoEncontrado = true;
                    break;
                }                
            }
        } catch (IOException e) {
          System.out.println("Error al leer el archivo: " + e.getMessage());
         // return;
        }
        
        // Si el contrato no fue encontrado, se ofrece al usuario la opción de crear uno nuevo
        if (!contratoEncontrado) {
            System.out.println("--------------------------");
            System.out.println("¡¡Contrato No Existe!!");
            System.out.println("--------------------------\n");
            System.out.print("¿Desea crear uno Nuevo? \n| 1. Sí | 2. No |: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer
            if (opcion == 1) {
                nuevoContrato.crearNuevoContrato();
            } else {
                System.out.println("Operación cancelada.");
            }
            
        }
           
    }
    
     /**
     * Muestra todos los empleados en formato de una tabla.
     */
    public static void mostrarContratos() {
        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoContratos()))) {
            System.out.println("\n\t\t\t\t\t\t\tCONSOLIDADO DE CONTRATOS.");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-3s | %-12s | %-6s | %-18s | %-15s | %-9s %-9s %-9s | %-15s | %-24s | %-19s |\n",
                    "RNU" ,"No CONTRATO", "ESTADO","TIPO DE CONTRATO","FECHA INICIO","EMPLEADO","","","IDENTIFICACION","CARGO", "SALARIO ASIGNADO");
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            String linea;
             

            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");
                
                if (d.length >= 18) {
                    System.out.printf("%-3s | %-12s | %-6s | %-18s | %-15s | %-9s %-9s %-9s | %-15s | %-24s | %-19s |\n",
                            d[0],d[2],d[1],d[14],d[15],d[5],d[7],d[8],d[4],d[16],d[17]);
                }
            }
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } catch (IOException e) {
            System.out.println("Error al leer empleados: " + e.getMessage());
        }
    }
    
}
