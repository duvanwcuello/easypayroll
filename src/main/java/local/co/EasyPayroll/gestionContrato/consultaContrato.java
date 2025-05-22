package local.co.EasyPayroll.gestionContrato;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import local.co.EasyPayroll.utilidades.datosDeUsoGeneral;
import local.co.EasyPayroll.utilidades.limpiarPantalla;

public class consultaContrato {
    
    public static void consultarContratoExistente() {
       
        Scanner scanner = new Scanner(System.in);
        limpiarPantalla.limpiarConsola();

        System.out.println("--------------------------------------------------");
        System.out.println("|           CONSULTA DE CONTRATOS                |");
        System.out.println("--------------------------------------------------");

        System.out.print("\n- Ingrese la identificación del titular: ");

        String identificacionEmpleado = scanner.nextLine();
        boolean contratoEncontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoContratos()))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");
                
                if (datos.length < 18) continue;

                if (datos[4].equals(identificacionEmpleado)) {

                    System.out.println("\n----------------------------------------------------------");
                    System.out.println(" N° CONTRATO: " + datos[2] + " | TIPO DE CONTRATO: " + datos[14]);
                    System.out.println(" FECHA INICIO: " + datos[15]);
                    System.out.println("----------------------------------------------------------");
                    System.out.println("° Nombre del Empleado: " + datos[5]+ " " + datos[6] + " " + datos[7]);
                    System.out.println("° Identificacion del Empleado: " + datos[4]);
                    System.out.println("° Direccion: "+ datos[11]+" | Barrio: "+datos[12] + " | Cuidad: " +datos[13]);
                    System.out.println("° Correo Electrónico: "+ datos[10]);
                    System.out.println("° Cargo Asignado: " + datos[16]);
                    System.out.println("° Salario: " + datos[17]);
                    System.out.println("----------------------------------------------------------\n");

                   // continuarUsuario.PressEnter('E');
                    contratoEncontrado = true;
                    break;

                }

            }
            
            System.out.println("\nERROR: Contrato no existente para la identificación: " + identificacionEmpleado);
         
        } catch (IOException e) {

          System.out.println("ERROR: No fue posible leer el archivo: " + e.getMessage());

        }
        
        // Si el contrato no fue encontrado, se ofrece al usuario la opción de crear uno nuevo
        if (!contratoEncontrado) {

            System.out.print("\n¿Desea registrar nuevo Contrato? - ( 1. Sí | 2. No ): ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {

                nuevoContrato.crearNuevoContrato();

            } else {

                System.out.println("\nOperación cancelada por el usuario.");
              //  continuarUsuario.PressEnter('E');

            }
        }
           
    }

    public static void mostrarContratos() {

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoContratos()))) {

            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("CONSOLIDADO DE CONTRATOS");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-12s | %-6s | %-15s | %-7s %-7s %-8s | %-15s | %-20s | %-14s\n",
                    "N° CONTRATO", "ESTADO", "F. INICIO", "EMPLEADO","","", " IDENTIFICACIÓN", "CARGO", "SALARIO");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] dato = linea.split(",");

                if (dato.length >= 18) {

                    System.out.printf("%-12s | %-6s | %-15s | %-7s  %-7s  %-7s | %-16s| %-20s | %-14s\n",
                    dato[2], dato[1], dato[15], dato[5],dato[7], dato[8], dato[4], dato[16], dato[17], dato[0]);
                }
            }

            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
          //  continuarUsuario.PressEnter('C');

        } catch (IOException e) {

            System.out.println("ERROR: No fue posible leer los empleados: " + e.getMessage());
        }
    } 
}
