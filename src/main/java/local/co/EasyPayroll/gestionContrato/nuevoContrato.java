package local.co.EasyPayroll.gestionContrato;

import java.io.*;
import java.time.*;
import java.util.*;

import local.co.EasyPayroll.gestionEmpleado.nuevoEmpleado;
import local.co.EasyPayroll.gestionUtilidades.datosDeUsoGeneral;
import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;


// Clase que representa los datos de un contrato de trabajo


public class nuevoContrato {

    private static Scanner scanner = new Scanner(System.in);
    private static int contadorIdContrato = 1;

    // Crea un nuevo contrato verificando condiciones iniciales
    public static void crearNuevoContrato(){
         
        limpiarPantalla.limpiarConsola();
        cargarContadorId();
        
        System.out.println("--------------------------------------------------");
        System.out.println("|           CREACIÓN DE CONTRATOS                |");
        System.out.println("--------------------------------------------------");

        System.out.println("\nEl siguiente contrato quedará registrado bajo el Numero de Registro Unico (NRU): "+ contadorIdContrato);
        System.out.print("\n- Ingrese Número de contrato: ");
        String numeroDeContrato = scanner.nextLine();

        // Verifica si el contrato ya existe
        if (validarExistenciaContrato(numeroDeContrato)) {

            System.out.println("\n---------------------------------------------");
            System.out.println("El contrato " + numeroDeContrato + " ya existe.");

            System.out.println("\n¿Desea editarlo? (1. SI | 2. NO):");
            System.out.print("- Indique una opcion: ");

            int seleccion = scanner.nextInt();
            scanner.nextLine();

            if (seleccion == 1) {
                editarContrato.editarContrato(numeroDeContrato);
            } else {
                System.out.println("Saliendo...");
            }

        } else {
            // Solicita identificación del empleado para buscarlo en archivo empleados
            System.out.println("- Identificación del empleado: " + numeroDeContrato);
            String identificacionEmpleado = numeroDeContrato;

            if (!empleadoExiste(identificacionEmpleado)) {              
                System.out.println("\nINFO: El empleado no existe, debe registrarlo como empleado nuevo.");
                System.out.print("¿Desea registrar nuevo empleado? (1. SI | 2. NO):");
                int resp = scanner.nextInt();
                scanner.nextLine();

                if(resp == 2){
                    System.out.println("\nOperación cancelada por el usuario.");
                    simulacionPrograma.continuarConTeclado();
                    return;                    
                }else if(resp == 1){
                    nuevoEmpleado.crearNuevoEmpleado();
                }
            } else if (validaEstadoContrato(identificacionEmpleado)) {
                System.out.println("INFO: Este empleado ya tiene un contrato ACTIVO.");
                simulacionPrograma.continuarConTeclado();
                return;
            }

            // Si todo es válido, solicita datos adicionales y guarda el contrato
            Contrato contrato = solicitarDatosContrato(numeroDeContrato, identificacionEmpleado);

            if (contrato != null) {  
                guardarContrato(contrato);
                contadorIdContrato++;
                System.out.println("\nSUCCES: Contrato creado correctamente. \n");
                simulacionPrograma.simulaEjecucion();

            } else {
                
                System.out.println("ERROR: Empleado no encontrado, No se pudo crear el contrato.  \n");
                simulacionPrograma.continuarConTeclado();

            }
        }
    }

    // Carga el ultimo ID para seguir la secuencia de creación de contratos
    private static void cargarContadorId() {

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoContratos()))) {
            String linea;
            int maxId = 0;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                if (id > maxId) maxId = id;
            }
            contadorIdContrato = maxId + 1;
        } catch (IOException e) {         
            System.out.println("No se pudo cargar el ID desde archivo: " + e.getMessage());
        }
    }

    // Verifica si un empleado ya está registrado en el sistema
    private static boolean empleadoExiste(String identificacion) {

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoEmpleados()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[1].equals(identificacion)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo de empleados: " + e.getMessage());
        }
        return false;
    }

    // Verifica si un empleado ya tiene un contrato activo
    private static boolean validaEstadoContrato(String identificacion) {

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoContratos()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[4].equals(identificacion) && datos[1].equalsIgnoreCase("A")) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer contratos: " + e.getMessage());
        }
        return false;
    }

    // Verifica si un número de contrato ya existe
    private static boolean validarExistenciaContrato(String numeroDeContrato) {

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoContratos()))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[2].equals(numeroDeContrato)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return false;
    }

    // Solicita datos faltantes del contrato y construye el objeto `Contrato`
    public static Contrato solicitarDatosContrato(String numeroContrato, String identificacion) {
        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoEmpleados()))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[1].equals(identificacion)) {
                    Contrato contrato = new Contrato();
                    contrato.setIdContrato(contadorIdContrato);
                    contrato.setNumeroContrato(numeroContrato);
                    contrato.setIdEmpleado(datos[0]);
                    contrato.setIdentificacionEmpleado(identificacion);
                    contrato.setPrimerNombre(datos[2]);
                    contrato.setSegundoNombre(datos[3]);
                    contrato.setPrimerApellido(datos[4]);
                    contrato.setSegundoApellido(datos[5]);
                    contrato.setFechaNacimiento(LocalDate.parse(datos[6]));
                    contrato.setDireccionResidenciaEmpleado(datos[11]);
                    contrato.setBarrioEmpleado(datos[12]);
                    contrato.setCiudadEmpleado(datos[13]);
                    contrato.setDepartamentoEmpleado(datos[14]);

                    System.out.println("\n--------------------------------------------------");
                    System.out.println("|           CONTINUA CREANDO EL CONTRATO         |");
                    System.out.println("--------------------------------------------------");

                    System.out.println("\n- Número de contrato: " + numeroContrato);
                    System.out.println("- Nombre del empleado: " + datos[2] + " " + datos[3] + " " + datos[4] + " " + datos[5]);
                    System.out.println("- Identificación del empleado: " + identificacion);

                    System.out.print("- Ingrese tipo de contrato: ");
                    contrato.setTipoContrato(scanner.nextLine().toUpperCase());

                    System.out.print("- Ingrese fecha de inicio del contrato (YYYY-MM-DD): ");
                    contrato.setFechaInicioContrato(LocalDate.parse(scanner.nextLine()));

                    System.out.print("- Ingrese cargo del empleado: ");
                    contrato.setCargoEmpleado(scanner.nextLine().toUpperCase());

                    System.out.print("- Ingrese salario del empleado: ");

                    while (!scanner.hasNextDouble()) {

                        System.out.print("Salario inválido, intente nuevamente: ");
                        scanner.next();
                    }
                    contrato.setSalarioEmpleado(scanner.nextDouble());
                    scanner.nextLine();

                    contrato.setEstado("A");
                    return contrato;
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer empleados: " + e.getMessage());

        }
        return null;
    }

    // Guarda el contrato en el archivo de texto
    private static void guardarContrato(Contrato contrato) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(datosDeUsoGeneral.getArchivoContratos(), true))) {
            bw.write(contrato.toString());
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error al guardar el contrato: " + e.getMessage());
        }
    }
}