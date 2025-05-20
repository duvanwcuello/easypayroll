package local.co.EasyPayroll.gestionEmpleado;

import java.io.*;
import java.time.*;
import java.util.*;

import local.co.EasyPayroll.utilidades.datosDeUsoGeneral;

public class nuevoEmpleado {

    private static final Scanner scanner = new Scanner(System.in);
    private static int contadorId = 1;

    // Método principal para crear un nuevo empleado
    public static void crearNuevoEmpleado() {
        System.out.print("Ingrese la identificación del empleado: ");
        String identificacion = scanner.nextLine();

        if (existeEmpleado(identificacion)) {
            System.out.println("----------------------------------------------------");
            System.out.println("El empleado con identificación " + identificacion + " ya existe.");
            System.out.println("----------------------------------------------------");
            System.out.print("\n¿Desea editar al empleado? (1. SI | 2. NO): ");
            int seleccion = scanner.nextInt();
            scanner.nextLine();
            if (seleccion == 1) {
                editarEmpleado.editarEmpleadoExistente(identificacion);
            } else {
                System.out.println("Operación cancelada por el usuario.");
            }
        } else {
            Empleado empleado = solicitarDatosEmpleado(identificacion);
            guardarEmpleado(empleado);
            contadorId++;
        }
        crearContratoDespuesDeEmpleado(identificacion);
        
    }

    // Carga el último ID desde el archivo Empleados.txt
    public static void cargarContadorId() {
        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoEmpleados()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                if (id >= contadorId) {
                    contadorId = id + 1;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Solicita información del nuevo empleado a crear
    public static Empleado solicitarDatosEmpleado(String identificacion) {
        System.out.print("Ingrese el primer nombre: ");
        String primerNombre = scanner.nextLine();
        System.out.print("Ingrese el segundo nombre: ");
        String segundoNombre = scanner.nextLine();
        System.out.print("Ingrese el primer apellido: ");
        String primerApellido = scanner.nextLine();
        System.out.print("Ingrese el segundo apellido: ");
        String segundoApellido = scanner.nextLine();
        System.out.print("Ingrese la fecha de nacimiento (yyyy-MM-dd): ");
        LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine());
        System.out.print("Ingrese el tipo de sangre: ");
        String tipoSangre = scanner.nextLine();
        System.out.print("Ingrese el sexo: ");
        String sexo = scanner.nextLine();
        System.out.print("Ingrese el estado civil: ");
        String estadoCivil = scanner.nextLine();
        System.out.print("Ingrese el nivel de estudio: ");
        String nivelEstudio = scanner.nextLine();
        System.out.print("Ingrese el correo electrónico: ");
        String correoElectronico = scanner.nextLine();
        System.out.print("Ingrese la dirección de residencia: ");
        String direccionResidencia = scanner.nextLine();
        System.out.print("Ingrese el barrio: ");
        String barrio = scanner.nextLine();
        System.out.print("Ingrese la ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Ingrese el departamento: ");
        String departamento = scanner.nextLine();

        return new Empleado(contadorId, identificacion, primerNombre, segundoNombre, primerApellido,
                segundoApellido, fechaNacimiento, tipoSangre, sexo, estadoCivil, nivelEstudio,
                correoElectronico, direccionResidencia, barrio, ciudad, departamento);
    }

    // Guarda la información en el archivo
    public static void guardarEmpleado(Empleado empleado) {
        String rutaArchivo = datosDeUsoGeneral.getArchivoEmpleados();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            bw.write(empleado.getId() + "," +
                    empleado.getIdentificacion() + "," +
                    empleado.getPrimerNombre() + "," +
                    empleado.getSegundoNombre() + "," +
                    empleado.getPrimerApellido() + "," +
                    empleado.getSegundoApellido() + "," +
                    empleado.getFechaNacimiento() + "," +
                    empleado.getTipoSangre() + "," +
                    empleado.getSexo() + "," +
                    empleado.getEstadoCivil() + "," +
                    empleado.getNivelEstudio() + "," +
                    empleado.getCorreoElectronico() + "," +
                    empleado.getDireccionResidencia() + "," +
                    empleado.getBarrio() + "," +
                    empleado.getCiudad() + "," +
                    empleado.getDepartamento());
            bw.newLine();
            System.out.println("\nEmpleado creado exitosamente.\n");
        } catch (IOException e) {
            System.out.println("Error al guardar el empleado: " + e.getMessage());
        }
    }

    // Verifica si la identificación ya está en el archivo
    private static boolean existeEmpleado(String identificacion) {
        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoEmpleados()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[1].equals(identificacion)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return false;
    }

    // Enviar datos para edición
    public static void enviarDatosEditarEmpleado(String identificacion) {
        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoEmpleados()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[1].equals(identificacion)) {
                    editarEmpleado.editarEmpleadoExistente(identificacion);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    //metodo para ir a crear nuevo contrato de una se debe revisar para terminar de implementarlo.
    public static void crearContratoDespuesDeEmpleado(String identificacion){
        System.out.println("Implementacion a futuro");
       /* System.err.print("¿Desea Crear Contrato al nuevo empleado?\n 1.(Si) - 2.(No)");
        int seleccion = scanner.nextInt();
        if (seleccion == 1){
            nuevoContrato.crearNuevoContrato();
        }else if (seleccion == 2) {
            menuUsuarios.menuPrincipalUsuario(identificacion)
        } */

    }
}
