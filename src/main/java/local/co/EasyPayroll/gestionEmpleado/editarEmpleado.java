package local.co.EasyPayroll.gestionEmpleado;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import local.co.EasyPayroll.utilidades.datosDeUsoGeneral;
public class editarEmpleado {
    
    public static void editarEmpleadoExistente(String identificacion) {
        List<String> empleados = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoEmpleados()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[1].equals(identificacion)) {
                    encontrado = true;
                    System.out.println("Editando datos del empleado con identificación: " + identificacion);
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
            System.out.println("Error al leer el archivo para edición: " + e.getMessage());
        }

        if (!encontrado) {
            System.out.println("Empleado no Existe.");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(datosDeUsoGeneral.getArchivoEmpleados()))) {
            for (String empleado : empleados) {
                bw.write(empleado);
                bw.newLine();
            }
            System.out.println("Empleado actualizado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo editado: " + e.getMessage());
        }
    }
}
