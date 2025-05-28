package local.co.GestionNomina;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import local.co.GestionParametrosLegales.ParametrosLegalesGenerales.conceptosLegales;
import local.co.GestionUtilidades.DatosDeUsoGeneral;
import local.co.GestionUtilidades.FormateadorTextro;
import local.co.GestionUtilidades.SimulacionPrograma;

    
public class ProcesarNominaMes{

public static void procesarNomina(){

    Scanner scanner = new Scanner(System.in);

    System.out.println("--------------------------------------------------------------------");
    System.out.println("|                PROCESAMIENTO DE NÓMINA QUINCENAL                  |");
    System.out.println("--------------------------------------------------------------------\n");

    System.out.print("- Ingrese el mes a procesar (1Q-MM-YYYY): ");
    String mes = scanner.nextLine().toUpperCase();

    String archivoNovedades = "novedades_" + mes + ".txt";
    String archivoPlanilla = "planilla_" + mes + ".txt";

    if (mes.isEmpty() || mes.length() < 10) {

        System.out.println("\nERROR: El nombre del archivo es invalido o esta vacío.");
        SimulacionPrograma.continuarConTeclado();
        procesarNomina();
        //return;
    }
    
    try (BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoContratos()));

        BufferedWriter bw = new BufferedWriter(new FileWriter(archivoPlanilla))){
        String linea;
                
        while ((linea = br.readLine()) != null){

            String[] datos = linea.split(",");
            if (!datos[1].equalsIgnoreCase("A")) continue;

            String id = datos[4];
            String nombreEmpleado = datos[5] + " " + datos[7] + " " + datos[8];
            double salarioBaseEmpleado = Double.parseDouble(datos[17]);
            double aux = salarioBaseEmpleado <= conceptosLegales.getSalarioMinimo() * 2 ? conceptosLegales.getAuxTransporte() : 0;

            double hed = 0, hen = 0, rn = 0, hedDom = 0, henDom = 0, recDom = 0;
            
            try (BufferedReader nov = new BufferedReader(new FileReader(archivoNovedades))) {

                String novedad;

                while ((novedad = nov.readLine()) != null) {

                    String[] n = novedad.split(",");

                    if (n[1].equals(id)) {
                        
                        hed = Double.parseDouble(n[3]);
                        hen = Double.parseDouble(n[4]);
                        rn = Double.parseDouble(n[5]);
                        hedDom = Double.parseDouble(n[6]);
                        henDom = Double.parseDouble(n[7]);
                        recDom = Double.parseDouble(n[8]);
                        break;
                    }
                }
            }

            double extras = CalculoNovedades.calculoHorasExtrasDiurnas(salarioBaseEmpleado, hed)
                        + CalculoNovedades.calculoHorasExtrasNocturnas(salarioBaseEmpleado, hen)
                        + CalculoNovedades.calculoRecargoNocturno(salarioBaseEmpleado, rn)
                        + CalculoNovedades.calculoHorasExtrasDiurnasDomYFest(salarioBaseEmpleado, conceptosLegales.getPeriodicidadNomina(), hedDom)
                        + CalculoNovedades.calculoHorasExtrasNocturnasDomYFest(salarioBaseEmpleado, conceptosLegales.getPeriodicidadNomina(), henDom)
                        + CalculoNovedades.calculoRecargoDomYFest(salarioBaseEmpleado, conceptosLegales.getPeriodicidadNomina(), recDom);

            double salarioPeriodo = (salarioBaseEmpleado / 30) * conceptosLegales.getPeriodicidadNomina();
            double devengado = salarioPeriodo + aux + extras;

            double saludEmpleado = devengado * conceptosLegales.getSaludEmpleado();
            double pensionEmpleado = devengado * conceptosLegales.getPensionEmpleado();
            double salarioNeto = devengado - (saludEmpleado + pensionEmpleado);

            // Mostrar Procesamiento de datos en pantalla 
            System.out.printf("\nEmpleado: %s\n", nombreEmpleado);
            System.out.printf("Salario base: %s\n", FormateadorTextro.formatearMoneda(salarioBaseEmpleado));
            System.out.printf("Aux transporte: %s\n", FormateadorTextro.formatearMoneda(aux));
            System.out.printf("Total recargos: %s\n", FormateadorTextro.formatearMoneda(extras));
            System.out.printf("Devengado: %s\n", FormateadorTextro.formatearMoneda(devengado));
            System.out.printf("Salud: %s | Pensión: %s\n", FormateadorTextro.formatearMoneda(saludEmpleado), FormateadorTextro.formatearMoneda(pensionEmpleado));
            System.out.printf("Neto a pagar: %s\n", FormateadorTextro.formatearMoneda(salarioNeto));
            System.out.println("--------------------------------------------------------------------");

            // Guardamos planilla de Nomina procesada
            bw.write(id + "," + nombreEmpleado + "," + salarioBaseEmpleado + "," + aux + "," + extras + "," + devengado + "," + saludEmpleado + "," + pensionEmpleado + "," + salarioNeto);
            bw.newLine();
        }

        System.out.println("Procesamiento exitoso. Planilla generada: " + archivoPlanilla);
        SimulacionPrograma.continuarPrograma();

        } catch (IOException e) {
            System.out.println("Error al procesar nómina: " + e.getMessage());
        }
    }

    public static void calcularNomina() {

        List<EmpleadoNomina> empleados = cargarEmpleadosDesdeArchivo();

        String mesActual = LocalDate.now().getMonth().name().toLowerCase();
        String archivoNovedades = "novedades_" + mesActual + ".txt";

        System.out.printf("%-15s %-25s %-12s %-10s %-10s %-12s\n",
                "Identificación", "Nombre", "Salario Base", "H. Extras", "Ausencias", "Total Pago");
        System.out.println("-------------------------------------------------------------------------------------------");

        try (BufferedReader br = new BufferedReader(new FileReader(archivoNovedades))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String id = partes[0];
                int horasExtras = Integer.parseInt(partes[2]);
                int ausencias = Integer.parseInt(partes[3]);

                // Buscar al empleado en la lista
                EmpleadoNomina emp = empleados.stream()
                        .filter(e -> e.getIdentificacion().equals(id))
                        .findFirst()
                        .orElse(null);

                if (emp != null) {
                    double valorHora = emp.getSalarioBase() / 240;
                    double pagoExtras = horasExtras * valorHora * 1.5;
                    double descuento = ausencias * (emp.getSalarioBase() / 30);
                    double totalPago = emp.getSalarioBase() + pagoExtras - descuento;

                    System.out.printf("%-15s %-25s %-12.2f %-10d %-10d %-12.2f\n",
                            emp.getIdentificacion(), emp.getNombre(), emp.getSalarioBase(),
                            horasExtras, ausencias, totalPago);
                } else {
                    System.out.printf("%-15s %-25s\n", id, "Empleado no encontrado");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de novedades: " + e.getMessage());
        }
    }

    public static List<EmpleadoNomina> cargarEmpleadosDesdeArchivo() {
        
        List<EmpleadoNomina> empleados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoContratos()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                // Validar estructura del archivo
                if (datos.length >= 18) {
                    String id = datos[4]; // identificación del empleado
                    String nombre = datos[5] + " " + datos[7]; // Primer nombre + primer apellido
                    double salario = Double.parseDouble(datos[17]); // salario
                    empleados.add(new EmpleadoNomina(id, nombre, salario));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar empleados desde contratos: " + e.getMessage());
        }
        return empleados;
    }
}