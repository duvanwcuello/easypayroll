package local.co.GestionContratos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import local.co.GestionUtilidades.DatosDeUsoGeneral;
import local.co.GestionUtilidades.LimpiarPantalla;
import local.co.GestionUtilidades.SimulacionPrograma;


public class EditarContrato {

    // Permite editar un contrato existente por su número
    public static void editarContrato(String numeroDeContrato) {

        Scanner scanner = new Scanner(System.in);

        List<String> listaContratos = new ArrayList<>();
        boolean contratoExistente = false;

        try(BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoContratos()))) {
            String linea;
            
            while((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                
                if(datos[2].equals(numeroDeContrato)) {
                    LimpiarPantalla.limpiarConsola();
                    System.out.println("CONTRATO EN EDICIÓN: " + numeroDeContrato);
                    boolean continuar = true;
                    
                    while(continuar) {
                        LimpiarPantalla.limpiarConsola();
                        System.out.println("\n---------------------------------------------------------");
                        System.out.println("SELECCIONE LA INFORMACION QUE DESEA MODIFICAR     ");
                        System.out.println("---------------------------------------------------------  ");
                        System.out.println("| 1. Tipo de contrato\t\t| 2. Fecha de inicio\t|\n| 3. Cargo del empleado\t\t| 4. Salario\t\t|");
                        System.out.println("| 5. Estado del contrato\t| 6. Finalizar edición\t|    ");
                        System.out.println("---------------------------------------------------------\n");
                        System.out.print("- Seleccione una opción: ");
                        int seleccionEditarContrato = scanner.nextInt();
                        scanner.nextLine();
 
                        switch(seleccionEditarContrato) {
                            case 1:
                                System.out.println("\nTIPO DE CONTRATO REGISTRADO: " + datos[14]);
                                System.out.print("- Ingrese nuevo tipo de contrato: ");
                                datos[14] = scanner.nextLine().toUpperCase();
                                break;
                            case 2:
                                System.out.println("\nFECHA DE INICIO REGISTRADA: " + datos[15]);
                                System.out.print("- Ingrese nueva fecha de inicio (YYYY-MM-DD): ");
                                datos[15] = scanner.nextLine();
                                break;
                            case 3:
                                System.out.println("\nCARGO ACTUAL REGISTRADO: " + datos[16]);
                                System.out.print("- Ingrese nuevo cargo del empleado: ");
                                datos[16] = scanner.nextLine().toUpperCase();
                                break;
                            case 4:
                                System.out.println("\nSALARIO ACTUAL REGISTRADO: " + datos[17]);
                                double salarioActual = Double.parseDouble(datos[17]);
                                System.out.print("- Ingrese nuevo salario (Actual: " + salarioActual + "): ");
                                double nuevoSalario;
                                
                                while(true) {
                                    while (!scanner.hasNextDouble()) {
                                        System.out.println("Valor inválido, intente nuevamente: ");
                                        scanner.next();
                                    }
                                    nuevoSalario = scanner.nextDouble();
                                    scanner.nextLine();
                                    if (nuevoSalario >= salarioActual) {
                                        datos[17] = String.valueOf(nuevoSalario);
                                        System.out.println("\n------------------------------------------------");
                                        System.out.println("| Salario registrado corectamente! " + nuevoSalario + "|");
                                        System.out.println("------------------------------------------------\n");
                                        SimulacionPrograma.simulaEjecucion();                                        break;
                                    } else {
                                        System.out.print("El nuevo salario no puede ser menor al actual (" + salarioActual + "), registre nuevamente: ");
                                    }
                                }
                                break;
                            case 5:
                                System.out.println("ESTADO ACTUAL: " + datos[1]);
                                System.out.print("Ingrese nuevo estado de contrato | (A) Activo (I) Inactivo): ");
                                datos[1] = scanner.nextLine();
                                break;
                            case 6:
                                continuar = false;
                                break;
                            default:
                                System.out.println("Opción no válida. Intente de nuevo.");
                                break;
                        }
                    }
                    contratoExistente = true;
                }
                listaContratos.add(String.join(",", datos));
            }
        }catch (IOException e){
            System.out.println("Error al leer el archivo de contratos: " + e.getMessage());
        }
        
        if(!contratoExistente) {
            System.out.println("Contrato no encontrado.");
            
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DatosDeUsoGeneral.getArchivoContratos()))) {
            for (String linea : listaContratos) {
                bw.write(linea);
                bw.newLine();
            }
            System.out.println("Contrato actualizado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los cambios del contrato: " + e.getMessage());
        } scanner.close();
    }
}
