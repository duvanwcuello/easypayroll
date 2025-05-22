package local.co.EasyPayroll.gestionEmpleado;

import java.io.*;
import java.time.*;
import java.util.*;

import local.co.EasyPayroll.seguridad.gestionUsuarios;
import local.co.EasyPayroll.utilidades.ContinuaEnter;
import local.co.EasyPayroll.utilidades.datosDeUsoGeneral;
import local.co.EasyPayroll.utilidades.limpiarPantalla;

public class nuevoEmpleado {

    private static final Scanner scanner = new Scanner(System.in);
    private static int contadorId = gestionUsuarios.obtenerSiguienteId(datosDeUsoGeneral.getArchivoEmpleados());

    public static void crearNuevoEmpleado() {

        limpiarPantalla.limpiarConsola();

        System.out.println("----------------------------------------------------");
        System.out.println("|             CREAR NUEVO EMPLEADO                 |");
        System.out.println("----------------------------------------------------\n");
        
        System.out.print("- Ingrese la identificación del empleado: ");
        String identificacion = scanner.nextLine();
        identificacion = validarCampoString(identificacion, "identificación");

        if (existeEmpleado(identificacion)) {

            System.out.println("\n----------------------------------------------------");
            System.out.println("| ERROR: ID Fiscal " + identificacion + " esta duplicado. ");
            System.out.println("----------------------------------------------------\n");

            System.out.print("- ¿Desea editar al empleado? (1. SI | 2. NO): ");

            int seleccion = scanner.nextInt();
            scanner.nextLine();

            if (seleccion == 1) {

                limpiarPantalla.limpiarConsola();
                editarEmpleado.editarEmpleadoExistente(identificacion);

            } else {

                limpiarPantalla.limpiarConsola();
            }

        } else {

            Empleado empleado = solicitarDatosEmpleado(identificacion);
            guardarEmpleado(empleado);
        }
    }

    public static String validarCampoString(String valor, String etiqueta) {

        while (valor == null || valor.trim().isEmpty()) {

            if (etiqueta.equals("ciudad")) {

                System.out.print("- Campo obligatorio, ingrese nuevamente la " + etiqueta + ": ");

            } else if (etiqueta.equals("identificación")) {

                System.out.print("- Campo obligatorio, ingrese nuevamente la " + etiqueta + ": ");

            }else{

                System.out.print("- Campo obligatorio, ingrese nuevamente el " + etiqueta + ": ");
            }

            valor = scanner.nextLine().toUpperCase();

            if (valor != null && !valor.trim().isEmpty()) {

                return valor;
            }
        }

        return valor;
    }

    public static LocalDate validarCampoFecha(LocalDate valor, String etiqueta) {

        while (valor == null) {

            valor = LocalDate.parse(scanner.nextLine());

            if (valor != null) {

                System.out.println("- Campo obligatorio, ingrese nuevamente la " + etiqueta + ": ");
                return valor;
                
            }
        }

        return valor;
    }

    public static int validarCampoNumero(int valor, String etiqueta) {

        while (valor == 0) {

            System.out.println("- Campo obligatorio, ingrese nuevamente el " + etiqueta + ": ");
            valor = scanner.nextInt();

            if (valor != 0) {

                return valor;
            }
        }

        return valor;
    }

    // Solicita información del nuevo empleado a crear
    public static Empleado solicitarDatosEmpleado(String identificacion) {

        System.out.print("\n- Ingrese el primer nombre: ");
        String primerNombre = scanner.nextLine().toUpperCase();
        primerNombre = validarCampoString(primerNombre, "primer Nombre");

        System.out.print("- Ingrese el segundo nombre: ");
        String segundoNombre = scanner.nextLine().toUpperCase();
        segundoNombre = validarCampoString(segundoNombre, "segundo Nombre");

        System.out.print("- Ingrese el primer apellido: ");
        String primerApellido = scanner.nextLine().toUpperCase();
        primerApellido = validarCampoString(primerApellido, "primer Apellido");

        System.out.print("- Ingrese el segundo apellido: ");
        String segundoApellido = scanner.nextLine().toUpperCase();
        segundoApellido = validarCampoString(segundoApellido, "segundo Apellido");

        System.out.print("- Ingrese la fecha de nacimiento (YYYY-MM-DD): ");
        LocalDate fechaNacimiento = LocalDate.parse(scanner.nextLine());
        fechaNacimiento = validarCampoFecha(fechaNacimiento, "fecha de nacimiento");

        System.out.print("- Ingrese el tipo de sangre: ");
        String tipoSangre = scanner.nextLine().toUpperCase();
        tipoSangre = validarCampoString(tipoSangre, "tipo de sangre");

        System.out.print("- Ingrese el sexo - (M/F): ");
        String sexo = scanner.nextLine().toUpperCase();
        sexo = validarCampoString(sexo, "sexo");

        System.out.print("- Ingrese el estado civil: ");
        String estadoCivil = scanner.nextLine().toUpperCase();
        estadoCivil = validarCampoString(estadoCivil, "estado civil");

        System.out.print("- Ingrese el nivel de estudio: ");
        String nivelEstudio = scanner.nextLine().toUpperCase();
        nivelEstudio = validarCampoString(nivelEstudio, "nivel de estudio");

        System.out.print("- Ingrese el correo electrónico: ");
        String correoElectronico = scanner.nextLine();
        correoElectronico = validarCampoString(correoElectronico, "correo electrónico");

        System.out.print("- Ingrese la dirección de residencia: ");
        String direccionResidencia = scanner.nextLine().toUpperCase();
        direccionResidencia = validarCampoString(direccionResidencia, "dirección de residencia");

        System.out.print("- Ingrese el barrio: ");
        String barrio = scanner.nextLine().toUpperCase();
        barrio = validarCampoString(barrio, "barrio");

        System.out.print("- Ingrese la ciudad: ");
        String ciudad = scanner.nextLine().toUpperCase();
        ciudad = validarCampoString(ciudad, "ciudad");
        
        System.out.print("- Ingrese el departamento: ");
        String departamento = scanner.nextLine().toUpperCase();
        departamento = validarCampoString(departamento, "departamento");
        
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

            System.out.println("\n------------------------------------------");
            System.out.println("| INFO: Empleado creado exitosamente.    |");
            System.out.println("------------------------------------------\n");

            ContinuaEnter.PressEnter('C');

        } catch (IOException e) {

            System.out.println("Error al guardar el empleado: " + e.getMessage());

        }
    }

    //Verifica si la identificación ya está en el archivo
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

            System.out.println("\n-------------------------------------------------------");
            System.out.println("| ERROR: No se pudo leer el archivo de empleados. " + e.getMessage()+ "|");
            System.out.println("-------------------------------------------------------\n");

            ContinuaEnter.PressEnter('E');
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

            System.out.println("\n-------------------------------------------------------");
            System.out.println("| ERROR: No se pudo leer el archivo de empleados. " + e.getMessage()+ "|");
            System.out.println("-------------------------------------------------------\n");

            ContinuaEnter.PressEnter('E');
            return;

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
