package local.co.EasyPayroll.seguridad;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import local.co.EasyPayroll.utilidades.*;

public class gestionUsuarios {
    
    private static int contadorIdUsuario = 1;

    public static void gestionUsuario(String rolActual){
       
        Scanner scanner = new Scanner(System.in);
        
        while (true) {

            limpiarPantalla.limpiarConsola();

            System.out.println("----------------------------------");
            System.out.println("|       GESTION DE USUARIOS      |");
            System.out.println("----------------------------------");
            System.out.println("| 1. Crear nuevo Usuario         |");
            System.out.println("| 2. Consulta x usuario          |");
            System.out.println("| 3. Editar usuario              |");
            System.out.println("| 4. Eliminar usuario            |");
            System.out.println("| 5. Consulta todos los usuarios |");
            System.out.println("| 6. Atras                       |");
            System.out.println("| 0. Salir                       |");
            System.out.println("----------------------------------\n");

            System.out.print("- Seleccione una opción: ");
            int selecciondeUsuario = scanner.nextInt();
            scanner.nextLine();

            switch (selecciondeUsuario) {

                case 1:

                    limpiarPantalla.limpiarConsola();
                    crearNuevoUsuario();
                    ContinuaEnter.PressEnter('E');
                    break;

                case 2:

                    limpiarPantalla.limpiarConsola();
                    consultarUsuarioExistente();
                    ContinuaEnter.PressEnter('E');
                    break;

                case 3:

                    limpiarPantalla.limpiarConsola();
                    editarUsuarioGuardado();
                    ContinuaEnter.PressEnter('E');
                    break;

                case 4:

                    limpiarPantalla.limpiarConsola();
                    eliminarUsuarioGuardado();
                    ContinuaEnter.PressEnter('E');
                    break;

                case 5:

                    limpiarPantalla.limpiarConsola();
                    mostrarTodosLosUsuarios();
                    ContinuaEnter.PressEnter('E');
                    break;

                case 6:

                    menuUsuarios.menuPrincipalUsuario(rolActual);
                    break;

                case 0:

                    System.out.println("Operación cancelada por el usuario.");
                    System.out.println("Saliendo...");
                    System.exit(0);
                    return;

                default:
                
                    System.out.println("Opción no válida, Intente de nuevo.");
            }
        }
    }


    public static void eliminarUsuarioGuardado() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------------------");
        System.out.println("|       ELIMINAR USUARIO            |");
        System.out.println("-------------------------------------");

        System.out.print("\n- Ingrese el nombre del usuario a eliminar: ");
        String usuarioBuscado = scanner.nextLine().trim();

        try {

            List<String> lineas = Files.readAllLines(Paths.get(datosDeUsoGeneral.getArchivoUsuarios()));
            boolean usuarioEncontrado = false;
            List<String> lineasActualizadas = new ArrayList<>();

            for (String linea : lineas) {

                String[] datos = linea.split(",");

                if (datos[2].equalsIgnoreCase(usuarioBuscado)) {

                    usuarioEncontrado = true;

                } else {

                    lineasActualizadas.add(linea);
                }
            }

            if (usuarioEncontrado) {

                Files.write(Paths.get(datosDeUsoGeneral.getArchivoUsuarios()),lineasActualizadas);

                System.out.println("""

                --------------------------------------
                | INFO: Usuario eliminado exitosamente. |
                --------------------------------------

                """);

            } else {

                System.out.println("""

                --------------------------------------
                | ERROR: Usuario no encontrado.       |
                --------------------------------------

                """);

            }

        } catch (IOException e) {

            System.out.println("""

            -------------------------------------------------
            | ERROR: No se pudo eliminar el usuario.        |
            -------------------------------------------------
            
            """ + e.getMessage());
        }
    }

    public static boolean validaExisteUsuario(String usuario) {

        String usuarioBuscado = usuario;

        try {

            List<String> lineas = Files.readAllLines(Paths.get(datosDeUsoGeneral.getArchivoUsuarios()));
            boolean usuarioEncontrado = false;

            for (String linea : lineas) {

                String[] datos = linea.split(",");

                if (datos[2].equalsIgnoreCase(usuarioBuscado)) {

                    usuarioEncontrado = true;
                    break;

                }
            }

            if (usuarioEncontrado) {

                return true;

            } else {

                return false;

            }

        } catch (IOException e) {

            System.out.println("\n");
            System.out.println("""
            -------------------------------------------------------------------
            | ERROR: No se pudo validar el usuario contra el archivo raiz.    |
            -------------------------------------------------------------------
            """ + e.getMessage());

            System.out.println("\n");
            ContinuaEnter.PressEnter('C');
            return false;
        }
    }
    
    public static void crearNuevoUsuario() {
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------------------");
        System.out.println("|       CREAR NUEVO USUARIO         |");
        System.out.println("-------------------------------------\n");

        System.out.print("- Ingrese nombre de usuario: ");
        String usuario = scanner.nextLine().trim();

        if (validaExisteUsuario(usuario) == false) {

            System.out.print("- Ingrese contraseña: ");
            String contrasena = scanner.nextLine().trim();

            System.out.print("- Ingrese nombre completo del empleado: ");
            String nombreEmpleado = scanner.nextLine().trim();

            System.out.print("- Ingrese rol (Administrador, Auxiliar, Coordinador): ");
            String rol = scanner.nextLine().trim().toUpperCase();

            String fechaRegistro = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(datosDeUsoGeneral.getArchivoUsuarios(), true))) {

                contadorIdUsuario = obtenerSiguienteId(datosDeUsoGeneral.getArchivoUsuarios());

                bw.write(contadorIdUsuario + "," + nombreEmpleado + "," + usuario + "," + contrasena + "," + rol + "," + fechaRegistro);
                bw.newLine();

                System.out.println("""

                --------------------------------------
                | INFO: Usuario creado exitosamente. |
                --------------------------------------

                """);
   
            } catch (IOException e) {

                System.out.println("""

                -------------------------------------------------
                | ERROR: El usuario no se guardo correctamente. |
                -------------------------------------------------
                
                """ + e.getMessage());
            }

        } else {

            System.out.println("""

            --------------------------------------
            | ERROR: El usuario ya existe.       |
            --------------------------------------

            """);
            
        }
    }

    public static void consultarUsuarioExistente() {

        Scanner sc = new Scanner(System.in);

        System.out.println("-------------------------------------");
        System.out.println("|        CONSULTA DE USUARIO        |");
        System.out.println("-------------------------------------");

        System.out.print("\n- Ingrese el nombre del usuario a consultar: ");
        String usuarioBuscado = sc.nextLine().trim();

        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoUsuarios()))){

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos.length >= 6 && datos[2].equalsIgnoreCase(usuarioBuscado)) {

                    System.out.println("\n------------------------------------");
                    System.out.println("° ID: " + datos[0] );
                    System.out.println("° EMPLEADO: " + datos[1]);
                    System.out.println("° USUARIO: " + datos[2]);
                    System.out.println("° ROL: " + datos[4]);
                    System.out.println("------------------------------------\n");

                    encontrado = true;
                    break;
                }
            }

            System.out.println("\n");

        } catch (IOException e) {

            System.out.println("""

            -----------------------------------------
            | ERROR: No se pudo leer el archivo.    |
            -----------------------------------------
            
            """ + e.getMessage());

            ContinuaEnter.PressEnter('C');

        }

        if (!encontrado) {

            System.out.println("""

            ----------------------------------------------------
            | ERROR: Usuario no encontrado dentro del archivo. |
            ----------------------------------------------------

            """);

            ContinuaEnter.PressEnter('C');
        }
    }

    public static void editarUsuarioGuardado() {

        Scanner sc = new Scanner(System.in);

        System.out.println("------------------------------------");
        System.out.println("|     EDITAR USUARIO EXISTENTE     |");
        System.out.println("------------------------------------");

        System.out.print("\n- Ingrese el nombre del usuario a editar: ");
        String usuarioBuscado = sc.nextLine().trim();

        List<String> usuariosActualizados = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoUsuarios()))){

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos.length >= 6 && datos[2].equalsIgnoreCase(usuarioBuscado)) {

                    System.out.print("- Nueva contraseña: ");
                    datos[3] = sc.nextLine().trim();

                    System.out.print("- Ingrese nuevo rol (Administrador, Auxiliar, Coordinador): ");
                    datos[4] = sc.nextLine().trim().toUpperCase();

                    datos[5] = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    linea = String.join(",", datos);

                    encontrado = true;
                }

                usuariosActualizados.add(linea);

            }

        } catch (IOException e) {

            System.out.println("""

            --------------------------------------------------
            | ERROR: No se pudo leer el archivo.             |
            --------------------------------------------------
            
            """ + e.getMessage());

        }

        if (encontrado) {

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(datosDeUsoGeneral.getArchivoUsuarios()))) {

                for (String u : usuariosActualizados) {

                    bw.write(u);
                    bw.newLine();
                }

                System.out.println("""

                ---------------------------------------------
                | ERROR: Usuario actualizado correctamente. |
                ---------------------------------------------

                """);

            } catch (IOException e) {

                System.out.println("""

                --------------------------------------------------
                | ERROR: No se pudo actualizar el usuario.       |
                --------------------------------------------------
                
                """ + e.getMessage());

            }

        } else {

                System.out.println("""

                --------------------------------------
                | ERROR: Usuario no encontrado.      |
                --------------------------------------

                """);

        }
    }

    public static void mostrarTodosLosUsuarios() {

        int conteo = 0;

        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("LISTADO DE USUARIOS                                                                                                             ");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-25s %-20s %-20s %-15s %-25s%n", "| ID", "| NOMBRE EMPLEADO", "| USUARIO", "| CONTRASEÑA", "| ROL", "| ÚLTIMO ACCESO ");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoUsuarios()))){

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos.length >= 6) {

                    conteo++;

                    System.out.printf("|%-10s|%-25s|%-20s|%-20s|%-15s|%-25s%n",
                    datos[0], datos[1], datos[2], datos[3], datos[4], datos[5]);

                }
            }

            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Total de usuarios: " + (conteo));
            System.out.println("\n");

        } catch (IOException e) {

            System.out.println("Error al leer el archivo: " + e.getMessage());

        }
    }

    public static int obtenerSiguienteId(String rutaArchivo) {

        int maxId = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos.length > 0) {

                    try {

                        int id = Integer.parseInt(datos[0].trim());

                        if (id > maxId) {

                            maxId = id;

                        }

                    } catch (NumberFormatException e) {}
                }
            }

        } catch (IOException e) {

            System.out.println("No se pudo leer el archivo: " + e.getMessage());

        }

        return maxId + 1;
    }
}