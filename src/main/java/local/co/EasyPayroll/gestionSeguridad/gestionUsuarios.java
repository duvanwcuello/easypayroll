/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.co.EasyPayroll.gestionSeguridad;

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import local.co.EasyPayroll.utilidades.*;

public class gestionUsuarios {

    private static int contadorIdUsuario = 1;

    /**
     * Muestra un menú interactivo para gestionar usuarios.
     * Permite crear, consultar, editar, mostrar usuarios o salir del sistema.
     */
    public static void menuGestionUsuario(String rolActual) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;       

        while (continuar) {
            System.out.println("\n*********************************");
            System.out.println("GESTIÓN DE USUARIOS");
            System.out.println("-------------------------------");
            System.out.println("1. Crear nuevo Usuario");
            System.out.println("2. Consultar usuario existente");
            System.out.println("3. Editar usuario existente");
            System.out.println("4. Mostrar todos los usuarios");
            System.out.println("5. Regresar al MENÚ PRINCIPAL");
            System.out.println("6. Salir");
            System.out.println("-------------------------------");
            System.out.print("Seleccione una opción: ");
            int seleccion = scanner.nextInt();
            scanner.nextLine();

            switch (seleccion) {
                case 1:
                    crearNuevoUsuario();
                    break;
                case 2:
                    consultarUsuarioExistente();
                    break;
                case 3:
                    editarUsuarioExistente();
                    break;
                case 4:
                    mostrarTodosLosUsuarios();
                    break;
                case 5:
                    menuUsuarios.menuPrincipalUsuario(rolActual);
                    return;
                case 6:
                    continuar = false;
                    System.out.println("Saliendo de la gestión de Usuarios...");
                    break;
                default:
                    scanner.close();
                    System.out.println("Opción no válida. Intente de nuevo.");
           }
        }
    }

    /**
     * Crea un nuevo usuario con ID único, nombre de empleado, usuario, contraseña, rol y fecha de registro.
     * Almacena la información en el archivo usuarios.txt.
     */
    public static void crearNuevoUsuario() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese nombre completo del empleado: ");
        String nombreEmpleado = scanner.nextLine().trim();

        System.out.print("Ingrese nombre de usuario: ");
        String usuario = scanner.nextLine().trim();

        if (usuarioYaExiste(usuario)) {
            System.out.println("El nombre de usuario ya está en uso.");
            return;
        }

        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine().trim();

        System.out.print("Ingrese rol (Administrador, Auxiliar, Coordinador): ");
        String rol = scanner.nextLine().trim().toUpperCase();

        String fechaRegistro = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(datosDeUsoGeneral.getArchivoUsuarios(), true))) {
            bw.write(contadorIdUsuario + "," + nombreEmpleado + "," + usuario + "," + contrasena + "," + rol + "," + fechaRegistro);
            bw.newLine();
            System.out.println("Usuario creado exitosamente.");
            contadorIdUsuario++;
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

        System.out.print("Ingrese el nombre de usuario a editar: ");
        String usuarioBuscado = scanner.nextLine().trim();
        
        List<String> usuariosActualizados = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 6 && datos[2].equalsIgnoreCase(usuarioBuscado)) {
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
            } catch (IOException e) {
                System.out.println("Error al guardar cambios: " + e.getMessage());
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    /**
     * Muestra una tabla con todos los usuarios registrados.
     * Incluye ID, nombre del empleado, nombre de usuario, contraseña cifrada, rol y último login.
     */
    public static void mostrarTodosLosUsuarios() {
        System.out.printf("%-10s %-25s %-20s %-20s %-15s %-25s%n", "ID", "Nombre Empleado", "Usuario", "Contraseña", "Rol", "Último Login");
        System.out.println("----------------------------------------------------------------------------------------------");

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 6) {
                    System.out.printf("%-10s %-25s %-20s %-20s %-15s %-25s%n",
                            datos[0], datos[1], datos[2], datos[3], datos[4], datos[5]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

}
