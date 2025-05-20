package local.co.EasyPayroll.gestionContrato;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import local.co.EasyPayroll.utilidades.datosDeUsoGeneral;
public class editarContrato {

    // Permite editar un contrato existente por su número
     public static void editarContratoGuardado(String numeroDeContrato) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        List<String> listaContratos = new ArrayList<>();
        boolean contratoExistente = false;

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoContratos()))) {
        String linea;

        while ((linea = br.readLine()) != null) {
            String[] datos = linea.split(",");
            if (datos[2].equals(numeroDeContrato)) {
                System.out.println("\nUSTED ESTA EDITANDO ESTE CONTRATO: " + numeroDeContrato);
                boolean continuar = true;
                while (continuar) {
                    System.out.println("\n------------------------------------------------");
                    System.out.println("SELECCIONE LA INFORMACION QUE DESEA MODIFICAR:");
                    System.out.println("------------------------------------------------");
                    System.out.println("1. Tipo de contrato\t| 2. Fecha de inicio\n3. Cargo del empleado\t| 4. Salario del empleado");
                    System.out.println("5. Estado del contrato\t| 6. Finalizar edición");
                    System.out.println("------------------------------------------------\n");
                    System.out.print("Indique una Opción: ");
                    int seleccionEditarContrato = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (seleccionEditarContrato) {
                        case 1:
                            System.out.println("TIPO DE CONTRATO REGISTRADO: " + datos[14]);
                            System.out.print("Ingrese nuevo tipo de contrato: ");
                            datos[14] = scanner.nextLine();
                            break;
                        case 2:
                            System.out.println("FECHA DE INICIO REGISTRADA: " + datos[15]);
                            System.out.print("Ingrese nueva fecha de inicio (yyyy-MM-dd): ");
                            datos[15] = scanner.nextLine();
                            break;
                        case 3:                        
                            System.out.println("CARGO ACTUAL REGISTRADO: " + datos[16]);
                            System.out.print("Ingrese nuevo cargo del empleado: ");
                            datos[16] = scanner.nextLine();
                            break;
                        case 4:
                            System.out.println("SALARIO ACTUAL REGISTRADO: " + datos[17]);
                            double salarioActual = Double.parseDouble(datos[17]);
                            System.out.print("Ingrese nuevo salario (Actual: " + salarioActual + "): ");
                            double nuevoSalario;
                            while (true) {
                                while (!scanner.hasNextDouble()) {
                                    System.out.print("Valor inválido. Intente nuevamente: ");
                                    scanner.next();
                                }
                                nuevoSalario = scanner.nextDouble();
                                scanner.nextLine(); // Limpiar buffer

                                if (nuevoSalario >= salarioActual) {
                                    datos[17] = String.valueOf(nuevoSalario);
                                    break;
                                } else {
                                    System.out.print("El nuevo salario no puede ser menor al actual (" + salarioActual + "). Intente de nuevo: ");
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

    } catch (IOException e) {
        System.out.println("Error al leer el archivo de contratos: " + e.getMessage());
    }
            if (!contratoExistente) {
                System.out.println("Contrato no encontrado.");
                return;
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(datosDeUsoGeneral.getArchivoContratos()))) {
                for (String linea : listaContratos) {
                    bw.write(linea);
                    bw.newLine();
                }
                System.out.println("Contrato actualizado exitosamente.");
            } catch (IOException e) {
                System.out.println("Error al guardar los cambios del contrato: " + e.getMessage());
            }
        }
        
    }
