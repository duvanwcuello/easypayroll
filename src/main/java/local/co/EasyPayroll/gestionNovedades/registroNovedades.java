package local.co.EasyPayroll.gestionNovedades;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import local.co.EasyPayroll.gestionNomina.*;


public class registroNovedades {
    
    private static final Scanner scanner = new Scanner(System.in);

    
    //reformalo para usar una lista
    public static void registrarNovedadesPorEmpleado() {
        List<empleadoNomina> empleados = procesarNominaMes.cargarEmpleadosDesdeArchivo();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados con contrato activo registrados.");
            return;
        }

        String mesActual = LocalDate.now().getMonth().name().toLowerCase();
        String archivoNovedades = "novedades_" + mesActual + ".txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoNovedades, true))) {
            for (empleadoNomina emp : empleados) {
                System.out.println("\nEmpleado: " + emp.getNombre() + " (" + emp.getidentificacion + ")");

                System.out.print("Ingrese descripción de la novedad: ");
                String descripcion = scanner.nextLine();
                System.out.print("Horas extras trabajadas: ");
                int horasExtras = Integer.parseInt(scanner.nextLine());
                System.out.print("Días de ausencia: ");
                int diasAusencia = Integer.parseInt(scanner.nextLine());

                novedad novedad = new novedad(emp.getIdentificacion(), descripcion, horasExtras, diasAusencia);
                bw.write(novedad.toString());
                bw.newLine();
            }
            System.out.println("Novedades registradas en el archivo: " + archivoNovedades);
        } catch (IOException e) {
            System.out.println("Error al guardar las novedades: " + e.getMessage());
        }
    }

}


