package local.co.EasyPayroll.GestionEmpleado;

import java.io.*;
import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import local.co.EasyPayroll.GestionUtilidades.DatosDeUsoGeneral;
import local.co.EasyPayroll.GestionUtilidades.LimpiarPantalla;
import local.co.EasyPayroll.GestionUtilidades.SimulacionPrograma;

public class NuevoEmpleado {

    private static final Scanner scanner = new Scanner(System.in);
    private static int contadorId = 1;
    //gestionUsuarios.obtenerSiguienteId(datosDeUsoGeneral.getArchivoEmpleados());

    public static void crearNuevoEmpleado() {

        LimpiarPantalla.limpiarConsola();

        System.out.println("----------------------------------------------------");
        System.out.println("|             CREAR NUEVO EMPLEADO                 |");
        System.out.println("| Por Favor Ingrese los datos del nuevo empleado.  |");
        System.out.println("----------------------------------------------------\n");       
        
        System.out.print("- Ingrese identificación: ");
        String identificacion = scanner.nextLine();
        identificacion = validarCampoString(identificacion, "identificación");

        if (existeEmpleado(identificacion)) {
            System.out.println("\n-----------------------------------------------------------------------");
            System.out.println("| ¡ADVERTENCIA! Empleado con Identificacion " + identificacion + " ya existe. |");
            System.out.println("-----------------------------------------------------------------------\n");

            System.out.println("¿Desea editar al empleado?");
            System.out.println("| 1. SI | 2. NO |");
            System.out.print("Indique Opcion: ");
            int seleccion = scanner.nextInt();
            scanner.nextLine();

            if (seleccion == 1) {
                LimpiarPantalla.limpiarConsola();
                EditarEmpleado.editarEmpleadoExistente(identificacion);
            } else {
                System.out.println("Volviendo a Gestion de Empleados...");
                SimulacionPrograma.simulaEjecucion();
                LimpiarPantalla.limpiarConsola();
            }
        } else {
            Empleado empleado = solicitarDatosEmpleado(identificacion);
            guardarEmpleado(empleado);
        }
    }

    private static String validarCampoString(String valor, String etiqueta) {

        while (valor == null || valor.trim().isEmpty()) {
           
            if (etiqueta.equals("ciudad")){
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

    private static LocalDate validarCampoFecha(LocalDate valor, String etiqueta) {
        
        while (valor == null) {
            valor = LocalDate.parse(scanner.nextLine());
            if (valor != null) {
                System.out.println("- Campo obligatorio, ingrese nuevamente la " + etiqueta + ": ");
                return valor;
            }
        }
        return valor;
    }

    private static LocalDate leerFechaValida(String mensaje, String formato) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        LocalDate fecha = null;

        while (fecha == null) {
            try {
                System.out.print(mensaje);
                String entrada = scanner.nextLine();
                fecha = LocalDate.parse(entrada, formatter);
            } catch (DateTimeException e) {
                System.out.println(" Error: Formato de fecha inválido. Intente nuevamente (formato: " + formato + ").");
            }
        }

        return fecha;
    }

    @SuppressWarnings("unused")
    private static int validarCampoNumero(int valor, String etiqueta) {
        
        while (valor == 0) {
            System.out.println("- Campo obligatorio, ingrese nuevamente el " + etiqueta + ": ");
            valor = scanner.nextInt();
            if (valor != 0) {
                return valor;
            }
        }
        return valor;
    }

    // Solicita información del nuevo empleado a crear y validamos datos con las funciones validar datos
    public static Empleado solicitarDatosEmpleado(String identificacion) {

        System.out.print("* Primer Nombre: ");
        String primerNombre = scanner.nextLine().toUpperCase();
        primerNombre = validarCampoString(primerNombre, "primer Nombre");

        System.out.print("* Segundo Nombre: ");
        String segundoNombre = scanner.nextLine().toUpperCase();
        //segundoNombre = validarCampoString(segundoNombre, "segundo Nombre");

        System.out.print("* Primer apellido: ");
        String primerApellido = scanner.nextLine().toUpperCase();
        primerApellido = validarCampoString(primerApellido, "primer Apellido");

        System.out.print("* Segundo apellido: ");
        String segundoApellido = scanner.nextLine().toUpperCase();
        segundoApellido = validarCampoString(segundoApellido, "segundo Apellido");

        LocalDate fechaNacimiento = leerFechaValida("* Fecha de nacimiento (yyyy-MM-dd): ", "yyyy-MM-dd");

        System.out.print("* Tipo y RH de Sangre: ");
        String tipoSangre = scanner.nextLine().toUpperCase();
        tipoSangre = validarCampoString(tipoSangre, "tipo de sangre");

        System.out.print("* Genero - (M/F): ");
        String sexo = scanner.nextLine().toUpperCase();
        sexo = validarCampoString(sexo, "sexo");

        System.out.print("* Estado Civil: ");
        String estadoCivil = scanner.nextLine().toUpperCase();
        estadoCivil = validarCampoString(estadoCivil, "estado civil");

        System.out.print("* Nivel de estudio: ");
        String nivelEstudio = scanner.nextLine().toUpperCase();
        nivelEstudio = validarCampoString(nivelEstudio, "nivel de estudio");

        System.out.print("* Correo electrónico: ");
        String correoElectronico = scanner.nextLine();
        correoElectronico = validarCampoString(correoElectronico, "correo electrónico");

        System.out.print("* Dirección de Residencia: ");
        String direccionResidencia = scanner.nextLine().toUpperCase();
        direccionResidencia = validarCampoString(direccionResidencia, "dirección de residencia");

        System.out.print("* Barrio: ");
        String barrio = scanner.nextLine().toUpperCase();
        barrio = validarCampoString(barrio, "barrio");

        System.out.print("* Ciudad: ");
        String ciudad = scanner.nextLine().toUpperCase();
        ciudad = validarCampoString(ciudad, "ciudad");
        
        System.out.print("* Departamento: ");
        String departamento = scanner.nextLine().toUpperCase();
        departamento = validarCampoString(departamento, "departamento");
        
        // la funcion retorna datos tipo empleado para guardarlo
        return new Empleado(contadorId, identificacion, primerNombre, segundoNombre, primerApellido,
                segundoApellido, fechaNacimiento, tipoSangre, sexo, estadoCivil, nivelEstudio,
                correoElectronico, direccionResidencia, barrio, ciudad, departamento);

    }

    // Recibe tipo de datos Empleado y Guarda la información en el archivo
    public static void guardarEmpleado(Empleado empleado) {
        String rutaArchivo = DatosDeUsoGeneral.getArchivoEmpleados();

        //leemos el archivo  yenviamos los datos tipo empleado separados por coma (,)
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

            SimulacionPrograma.simulaEjecucion();
            System.out.println("\n------------------------------------------");
            System.out.println("| INFO: Empleado Guadado Exitosamente.    |");
            System.out.println("------------------------------------------\n");
            SimulacionPrograma.simulaEjecucion();
            LimpiarPantalla.limpiarConsola();

        } catch (IOException e) {
            System.out.println("Error al guardar el empleado: " + e.getMessage());
        }
    }

    //Verifica si la identificación ya está en el archivo
    private static boolean existeEmpleado(String identificacion) {

        try (BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoEmpleados()))) {
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

            SimulacionPrograma.continuarConTeclado();
        }

        return false;
    }

    // Enviar datos para edición
    public static void enviarDatosEditarEmpleado(String identificacion) {

        try (BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoEmpleados()))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos[1].equals(identificacion)) {
                    EditarEmpleado.editarEmpleadoExistente(identificacion);
                }
            }
        } catch (IOException e) {
            System.out.println("\n-------------------------------------------------------");
            System.out.println("| ERROR: No se pudo leer el archivo de empleados. " + e.getMessage()+ "|");
            System.out.println("-------------------------------------------------------\n");

            SimulacionPrograma.continuarConTeclado();
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
