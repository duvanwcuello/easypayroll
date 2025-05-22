package local.co.EasyPayroll.gestionNovedades;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import local.co.EasyPayroll.utilidades.datosDeUsoGeneral;

public class registroNovedades {
    
    private static final String ARCHIVO_EMPLEADOS = datosDeUsoGeneral.getArchivoEmpleados();
    private static final String ARCHIVO_CONTRATOS = datosDeUsoGeneral.getArchivoEmpleados();

    private static final Scanner scanner = new Scanner(System.in);

    static class Novedad {
        
        String identificacion;
        String descripcion;
        int horasExtras;
        int diasAusencia;

        public Novedad(String identificacion, String descripcion, int horasExtras, int diasAusencia) {

            this.identificacion = identificacion;
            this.descripcion = descripcion;
            this.horasExtras = horasExtras;
            this.diasAusencia = diasAusencia;
        }

        @Override
        public String toString() {

            return identificacion + "," + descripcion + "," + horasExtras + "," + diasAusencia;
        }
    }

    static class Contrato {

        String identificacion;
        double salario;

        public Contrato(String identificacion, double salario) {

            this.identificacion = identificacion;
            this.salario = salario;
        }
    }

    public static void registrarNovedadesPorEmpleado() {

        Map<String, String> empleados = cargarEmpleados();

        if (empleados.isEmpty()) {

            System.out.println("No hay empleados registrados.");
            return;
        }

        String mesActual = LocalDate.now().getMonth().name().toLowerCase();
        String archivoNovedades = "novedades_" + mesActual + ".txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoNovedades, true))) {

            for (Map.Entry<String, String> entry : empleados.entrySet()) {

                String identificacion = entry.getKey();
                System.out.println("\nEmpleado: " + entry.getValue() + " (" + identificacion + ")");

                System.out.print("- Ingrese la descripción de la novedad: ");
                String descripcion = scanner.nextLine();

                System.out.print("- Cantidad de horas extras trabajadas: ");
                int horasExtras = Integer.parseInt(scanner.nextLine());
                
                System.out.print("Cantidad de días de ausencia: ");
                int diasAusencia = Integer.parseInt(scanner.nextLine());

                Novedad novedad = new Novedad(identificacion, descripcion, horasExtras, diasAusencia);
                bw.write(novedad.toString());
                bw.newLine();

            }

            System.out.println("SUCCES: Novedades registradas correctamente, Guardado en el archivo: " + archivoNovedades);

        } catch (IOException e) {

            System.out.println("ERROR: No fue posible guardar las novedades: " + e.getMessage());
        }
    }

    public static void calcularNomina() {

        Map<String, String> empleados = cargarEmpleados();
        Map<String, Double> salarios = cargarSalarios();

        String mesActual = LocalDate.now().getMonth().name().toLowerCase();
        String archivoNovedades = "novedades_" + mesActual + ".txt";

        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%-15s %-25s %-12s %-10s %-10s %-12s\n",
                "IDENTIFICACIÓN", "NOMBRE EMPLEADO", "SALARIO BASE", "H. EXTRAS", "AUSENCIAS", "TOTAL A PAGAR");
        System.out.println("-------------------------------------------------------------------------------------------");

        try (BufferedReader br = new BufferedReader(new FileReader(archivoNovedades))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(",");
                String id = partes[0];

                int horasExtras = Integer.parseInt(partes[2]);
                int ausencias = Integer.parseInt(partes[3]);

                String nombre = empleados.getOrDefault(id, "Desconocido");
                double salarioBase = salarios.getOrDefault(id, 0.0);

                double valorHora = salarioBase / 240;
                double pagoExtras = horasExtras * valorHora * 1.5;
                double descuento = ausencias * (salarioBase / 30);
                double totalPago = salarioBase + pagoExtras - descuento;

                System.out.printf("%-15s %-25s %-12.2f %-10d %-10d %-12.2f\n",
                        id, nombre, salarioBase, horasExtras, ausencias, totalPago);
            }
        } catch (IOException e) {

            System.out.println("Error al leer el archivo de novedades: " + e.getMessage());
        }
    }

    private static Map<String, String> cargarEmpleados() {

        Map<String, String> empleados = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_EMPLEADOS))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");
                empleados.put(datos[1], datos[2] + " " + datos[4]);
            }

        } catch (IOException e) {

            System.out.println("Error al cargar empleados: " + e.getMessage());
        }

        return empleados;
    }

    private static Map<String, Double> cargarSalarios() {

        Map<String, Double> salarios = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_CONTRATOS))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");
                String id = datos[0];
                double salario = Double.parseDouble(datos[2]);
                salarios.put(id, salario);

            }

        } catch (IOException e) {

            System.out.println("Error al cargar contratos: " + e.getMessage());

        }

        return salarios;
    }
}


