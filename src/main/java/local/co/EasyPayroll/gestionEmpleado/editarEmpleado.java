package local.co.EasyPayroll.GestionEmpleado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import local.co.EasyPayroll.GestionUtilidades.DatosDeUsoGeneral;
import local.co.EasyPayroll.GestionUtilidades.SimulacionPrograma;

public class EditarEmpleado {
    
    public static void editarEmpleadoExistente(String identificacion) {

        List<String> empleados = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoEmpleados()))) {

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
                    Empleado empleadoActualizado = NuevoEmpleado.solicitarDatosEmpleado(identificacion);

                    empleados.add(empleadoActualizado.getId() + "," +
                    empleadoActualizado.getIdentificacion() + "," +
                    empleadoActualizado.getPrimerNombre() + "," +
                    empleadoActualizado.getSegundoNombre() + "," +
                    empleadoActualizado.getPrimerApellido() + "," +
                    empleadoActualizado.getSegundoApellido() + "," +
                    empleadoActualizado.getFechaNacimiento() + "," +
                    empleadoActualizado.getTipoSangre() + "," +
                    empleadoActualizado.getSexo() + "," +
                    empleadoActualizado.getEstadoCivil() + "," +
                    empleadoActualizado.getNivelEstudio() + "," +
                    empleadoActualizado.getCorreoElectronico() + "," +
                    empleadoActualizado.getDireccionResidencia() + "," +
                    empleadoActualizado.getBarrio() + "," +
                    empleadoActualizado.getCiudad() + "," +
                    empleadoActualizado.getDepartamento());

                } else {
                    empleados.add(linea);
                }
            }

        } catch (IOException e) {

            System.out.println("\n-------------------------------------------------------");
            System.out.println("| ERROR: No se pudo leer el archivo de empleados. " + e.getMessage()+ "|");
            System.out.println("-------------------------------------------------------\n");

            SimulacionPrograma.continuarConTeclado();
            return;

        }

        if (!encontrado) {

            System.out.println("\n------------------------------------");
            System.out.println("| ERROR: Empleado no encontrado.     |");
            System.out.println("--------------------------------------\n");

            SimulacionPrograma.continuarConTeclado();
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DatosDeUsoGeneral.getArchivoEmpleados()))) {

            for (String empleado : empleados) {
                bw.write(empleado);
                bw.newLine();
            }

            System.out.println("\n------------------------------------------");
            System.out.println("| INFO: Empleado editado exitosamente!    |");
            System.out.println("-------------------------------------------\n");

            SimulacionPrograma.simulaEjecucion();

        } catch (IOException e) {
            
            
            System.out.println("\n-------------------------------------------------------");
            System.out.println("| ERROR: No se pudo guardar el empleado editado. " + e.getMessage()+ "|");
            System.out.println("-------------------------------------------------------\n");

            SimulacionPrograma.continuarConTeclado();
        }
    }
}
