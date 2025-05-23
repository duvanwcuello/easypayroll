package local.co.EasyPayroll.gestionEmpleado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import local.co.EasyPayroll.GestionUtilidades.datosDeUsoGeneral;
import local.co.EasyPayroll.GestionUtilidades.simulacionPrograma;

public class editarEmpleado {
    
    public static void editarEmpleadoExistente(String identificacion) {

        List<String> empleados = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoEmpleados()))) {

            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("EDICIÓN DEL MAESTRO DE EMPLEADOS");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s | %-15s | %-15s | %-15s | %-20s | %-30s | %-30s\n",
                    "ID", "IDENTIFICACIÓN", "NOMBRE", "APELLIDO", "F. NACIMIENTO", "CORREO", "DIRECCIÓN");
            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos[1].equals(identificacion)) {

                    encontrado = true;

                    System.out.printf("%-5s | %-15s | %-15s | %-15s | %-20s | %-30s | %-30s\n",
                            datos[0], datos[1], datos[2] + " " + datos[3], datos[4] + " " + datos[5], datos[6],
                            datos[11], datos[12] + " - " + datos[13]);

                    System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("\n\n-------------------------------------------------------------------------------------------------------------------------------------------------------");

                    System.out.println("Ingrese los nuevos datos del empleado con ID: " + identificacion + "\n");
                    Empleado actualizado = nuevoEmpleado.solicitarDatosEmpleado(identificacion);

                    empleados.add(actualizado.getId() + "," +
                    actualizado.getIdentificacion() + "," +
                    actualizado.getPrimerNombre() + "," +
                    actualizado.getSegundoNombre() + "," +
                    actualizado.getPrimerApellido() + "," +
                    actualizado.getSegundoApellido() + "," +
                    actualizado.getFechaNacimiento() + "," +
                    actualizado.getTipoSangre() + "," +
                    actualizado.getSexo() + "," +
                    actualizado.getEstadoCivil() + "," +
                    actualizado.getNivelEstudio() + "," +
                    actualizado.getCorreoElectronico() + "," +
                    actualizado.getDireccionResidencia() + "," +
                    actualizado.getBarrio() + "," +
                    actualizado.getCiudad() + "," +
                    actualizado.getDepartamento());

                } else {

                    empleados.add(linea);
                }
            }

        } catch (IOException e) {

            System.out.println("\n-------------------------------------------------------");
            System.out.println("| ERROR: No se pudo leer el archivo de empleados. " + e.getMessage()+ "|");
            System.out.println("-------------------------------------------------------\n");

            simulacionPrograma.continuarConTeclado();
            return;

        }

        if (!encontrado) {

            System.out.println("\n------------------------------------");
            System.out.println("| ERROR: Empleado no encontrado.     |");
            System.out.println("--------------------------------------\n");

            simulacionPrograma.continuarConTeclado();
            return;

        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(datosDeUsoGeneral.getArchivoEmpleados()))) {

            for (String empleado : empleados) {

                bw.write(empleado);
                bw.newLine();

            }

            System.out.println("\n------------------------------------------");
            System.out.println("| INFO: Empleado editado exitosamente!    |");
            System.out.println("-------------------------------------------\n");

            simulacionPrograma.continuarPrograma();

        } catch (IOException e) {
            
            System.out.println("\n-------------------------------------------------------");
            System.out.println("| ERROR: No se pudo guardar el empleado editado. " + e.getMessage()+ "|");
            System.out.println("-------------------------------------------------------\n");

            simulacionPrograma.continuarConTeclado();
        }
    }
}
