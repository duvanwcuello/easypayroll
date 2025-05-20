package local.co.EasyPayroll.gestionContrato;

import java.io.*;
import java.time.*;
import java.util.*;


import local.co.EasyPayroll.utilidades.datosDeUsoGeneral;
import local.co.EasyPayroll.gestionEmpleado.nuevoEmpleado;


// Clase que representa los datos de un contrato de trabajo


public class nuevoContrato {

    private static Scanner scanner = new Scanner(System.in);
    private static int contadorId = 1;

    // Crea un nuevo contrato verificando condiciones iniciales
    public static void crearNuevoContrato(){
         
        // Actualiza el contador según el último contrato guardado
        cargarContadorId();
        
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("POR FAVOR REGISTRE LOS DATOS DEL NUEVO CONTRATO");
        System.out.println("--------------------------------------------------");
        System.out.println("\nEl siguiente contrato quedará registrado\nbajo el Numero de Registro Unico (NRU): "+ contadorId);
        System.out.print("\nIngrese Número de contrato: ");
        String numeroDeContrato = scanner.nextLine();

        // Verifica si el contrato ya existe
        if (validarExistenciaContrato(numeroDeContrato)) {
            System.out.println("------------------------------------------");
            System.out.println("El contrato "+numeroDeContrato+" ya existe.");
            System.out.print("¿Desea editarlo? (1. SI | 2. NO): ");
            System.out.print("Indique una Opcion: ");
            int seleccion = scanner.nextInt();
            scanner.nextLine();

            if (seleccion == 1) {
                editarContrato.editarContratoGuardado(numeroDeContrato);
            } else {
                System.out.println("Saliendo...");
            }
        } else {
            // Solicita identificación del empleado para buscarlo en archivo empleados
            System.out.print("Ingrese la identificación del empleado: ");
            String identificacionEmpleado = scanner.nextLine();

            if (!empleadoExiste(identificacionEmpleado)){
                 System.out.println("---------------------------");
                 System.out.println("\n¡ALERTA!\n¡¡EL EMPLEADO NO EXISTE!!.\nPor Favor Registre nuevo empleado.\n");
                 System.out.println("---------------------------");

                // Llama Metodo para crear nuevo empleado. 
                nuevoEmpleado.crearNuevoEmpleado();  

            } else if (validaEstadoContrato(identificacionEmpleado)){
                System.out.println("\n¡ALERTA!\n!!Este empleado ya tiene un contrato activo.\nNo se puede registrar Un nuevo contrato.¡¡");
                return;
            }

            // Si todo lo válidado esta Ok, solicita datos adicionales y guarda el contrato
            contrato contrato = solicitarDatosContrato(numeroDeContrato, identificacionEmpleado);
            
            if (contrato != null) {
                //enviamos datos al contrato para ser guardado
                guardarContrato(contrato);
                //sumamos contador
                contadorId++;
                System.out.println("\nCONTRATO CREADO EXITOSAMENTE.");
            } else {
                //esto es opcional, proviene de la primera version del codigo antes de meterle las mejoras de crear directamente el empleado.
                System.out.println("Empleado no encontrado. No se pudo crear el contrato.");
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
            contadorId = maxId + 1;
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

    // Solicita datos faltantes del contrato y construye el objeto Contrato
    public static contrato solicitarDatosContrato(String numeroContrato, String identificacion) {
        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoEmpleados()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[1].equals(identificacion)) {
                    contrato contrato = new contrato();
                    contrato.setIdContrato(contadorId);
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

                    System.out.print("Ingrese tipo de contrato: ");
                    contrato.setTipoContrato(scanner.nextLine());

                    System.out.print("Ingrese fecha de inicio del contrato (yyyy-MM-dd): ");
                    contrato.setFechaInicioContrato(LocalDate.parse(scanner.nextLine()));

                    System.out.print("Ingrese cargo del empleado: ");
                    contrato.setCargoEmpleado(scanner.nextLine());

                    System.out.print("Ingrese salario del empleado: ");
                    while (!scanner.hasNextDouble()) {
                        System.out.print("Salario inválido. Intente nuevamente: ");
                        scanner.next();
                    }
                    contrato.setSalarioEmpleado(scanner.nextDouble());
                    scanner.nextLine();

                    contrato.setEstado("A");  // Por defecto, contrato nuevo está activo
                    return contrato;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer empleados: " + e.getMessage());
        }
        return null;
    }

    // Guarda el contrato en el archivo de texto
    private static void guardarContrato(contrato contrato) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(datosDeUsoGeneral.getArchivoContratos(), true))) {
            bw.write(contrato.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el contrato: " + e.getMessage());
        }
    }

    
   
}
