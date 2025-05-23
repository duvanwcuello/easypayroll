
package local.co.EasyPayroll.GestionUsuario;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import local.co.EasyPayroll.GestionUtilidades.*;
import local.co.EasyPayroll.gestionSeguridad.menuPorRolUsuario;

public class gestionUsuarios {

    private static int contadorIdUsuario = 1;

    //Cargamos el contador desde el archivo 

    private static void cargarContadorId() {

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            int maxId = 0;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                if (id > maxId) maxId = id;
            }
            contadorIdUsuario = maxId + 1;
        } catch (IOException e) {         
            System.out.println("No se pudo cargar el ID desde archivo: " + e.getMessage());
        }
    }


    /**
     * Crea un nuevo usuario con ID único, nombre de empleado, usuario, contraseña, rol y fecha de registro.
     * Almacena la información en el archivo usuarios.txt.
     */
    public static void crearNuevoUsuario() {

        //leemos el ultimo Id del archivo
        cargarContadorId();

        Scanner scanner = new Scanner(System.in);

        System.out.println("---------------------------------------");
        System.out.println("|       CREANDO NUEVO USUARIO         |");
        System.out.println("---------------------------------------");
        
        System.out.print("Ingrese nombre de usuario: ");
        String usuarioNuevo = scanner.nextLine().trim();
         
        if (usuarioYaExiste(usuarioNuevo)) {
            System.out.println("---------------------------------------");
            System.out.println("\t\t¡ADVERTENCIA!");
            System.out.println("El nombre de usuario ( "+usuarioNuevo.toUpperCase()+" ), ya está en uso.");
            System.out.println("---------------------------------------");
            System.out.print("¿Desea editar el usuario existente?");
            System.err.print("(1. Sí | 2. No): ");
            int opcion = scanner.nextInt();
            
            scanner.nextLine();
            if (opcion == 1) {
                limpiarPantalla.limpiarConsola();
                editarUsuarioExistente();
            } else {
                System.out.println("Operación cancelada.....");
                System.out.println("Volviendo a Gestion de Usuarios...");
                simulacionPrograma.continuarPrograma();
                limpiarPantalla.limpiarConsola();
                menuGestionUsuario.menuGestionUsuarios(usuarioNuevo);
            }
            return;
        }
       
        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine().trim();

        System.out.print("Ingrese nombre completo del Nuevo Usuario: ");
        String nombreEmpleado = scanner.nextLine().trim();

        System.out.print("Ingrese rol a asignar \n(Administrador, Auxiliar, Coordinador): ");
        String rol = scanner.nextLine().trim().toUpperCase();

        String fechaRegistro = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(datosDeUsoGeneral.getArchivoUsuarios(), true))) {
            bw.write(contadorIdUsuario + "," + nombreEmpleado + "," + usuarioNuevo + "," + contrasena + "," + rol + "," + fechaRegistro);
            bw.newLine();
            System.out.println("Usuario Guardado Exitosamente.");
            contadorIdUsuario++;
            limpiarPantalla.limpiarConsola();
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
        }
    }

    /**
     * Verifica si un nombre de usuario ya existe en el archivo usuarios.txt.
     * @param nombreUsuario el nombre a verificar
     * @return true si el usuario ya está registrado, false en caso contrario
     */
    private static boolean usuarioYaExiste(String nombreUsuario) {
        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 3 && datos[2].equalsIgnoreCase(nombreUsuario)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return false;
    }

    /**
     * Consulta un usuario existente por su nombre de usuario y muestra sus datos.
     */
    public static void consultarUsuarioExistente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------------------");
        System.out.println("|         CONSULTA DE USUARIOS        |");
        System.out.println("---------------------------------------");
        System.out.print("Ingrese el nombre de usuario a consultar: ");
        String usuarioBuscado = scanner.nextLine().trim();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 6 && datos[2].equalsIgnoreCase(usuarioBuscado)) {
                    System.out.println("\nID: " + datos[0]);
                    System.out.println("Empleado: " + datos[1]);
                    System.out.println("Usuario: " + datos[2]);
                    System.out.println("Rol: " + datos[4]);
                    System.out.println("Último Inicio de Sesión: " + datos[5]);
                    System.out.println("\n");
                    encontrado = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        if (!encontrado) {
            System.out.println("Usuario no encontrado.");
        }
    }

    /**
     * Permite editar la contraseña y el rol de un usuario existente.
     * También actualiza la fecha del último inicio de sesión.
     */
    public static void editarUsuarioExistente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------");
        System.out.println("\tEDITANDO USUARIOS");
        System.out.println("-----------------------------------");
        System.out.print("Ingrese Usuario a Editar:");
        String usuarioIngresado = scanner.nextLine();
        
        List<String> usuariosActualizados = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 6 && datos[2].equalsIgnoreCase(usuarioIngresado)) {
                    System.out.print("Nueva contraseña: ");
                    datos[3] = scanner.nextLine().trim();
                    System.out.print("Nuevo rol: ");
                    datos[4] = scanner.nextLine().trim();
                    datos[5] = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    linea = String.join(",", datos);
                    encontrado = true;
                }
                usuariosActualizados.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        if (encontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(datosDeUsoGeneral.getArchivoUsuarios()))) {
                for (String u : usuariosActualizados) {
                    bw.write(u);
                    bw.newLine();
                }
                System.out.println("Usuario actualizado correctamente.");
                menuPorRolUsuario.menuPrincipalUsuario(usuarioIngresado);
            } catch (IOException e) {
                System.out.println("Error al guardar cambios: " + e.getMessage());
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }


    public static void eliminarUsuarioGuardado() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------------------");
        System.out.println("|       ELIMINAR USUARIO            |");
        System.out.println("-------------------------------------");

        System.out.print("\n- Ingrese el nombre del usuario: ");
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
                -----------------------------------------
                | INFO: Usuario eliminado exitosamente. |
                -----------------------------------------
                """);
                simulacionPrograma.continuarPrograma();
                limpiarPantalla.limpiarConsola();
                gestionUsuarios.mostrarTodosLosUsuarios();
                System.out.println("");
                simulacionPrograma.continuarConTeclado();
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


    /**
     * Muestra una tabla con todos los usuarios registrados.
     * Incluye ID, nombre del empleado, nombre de usuario, contraseña cifrada, rol y último login.
     */
    public static void mostrarTodosLosUsuarios() {
        System.out.println("-----------------------------------------------------------------------------------------------------");
        System.out.printf("%-3s %-25s %-10s %-10s %-15s %-25s%n", "ID", "Nombre Empleado", "Usuario", "Contraseña", "Rol", "Último Login");
        System.out.println("-----------------------------------------------------------------------------------------------------");

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 6) {
                    System.out.printf("%-3s %-25s %-10s %-10s %-15s %-25s%n",
                            datos[0], datos[1], datos[2], datos[3], datos[4], datos[5]);
                }
            } System.out.println("-----------------------------------------------------------------------------------------------------");
            
            
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

}
