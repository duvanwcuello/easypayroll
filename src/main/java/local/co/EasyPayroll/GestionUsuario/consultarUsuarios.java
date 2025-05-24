package local.co.EasyPayroll.gestionUsuario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import local.co.EasyPayroll.gestionUtilidades.datosDeUsoGeneral;

public class consultarUsuarios {

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
     * Muestra una tabla con todos los usuarios registrados.
     * Incluye ID, nombre del empleado, nombre de usuario, contraseña cifrada, rol y último login.
     */
    public static void consultarTodosLosUsuarios() {
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
