package local.co.EasyPayroll.gestionUsuarios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import local.co.EasyPayroll.gestionUtilidades.datosDeUsoGeneral;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;

public class consultarUsuarios {

    /**
     * Consulta un usuario existente por su nombre de usuario y muestra sus datos.
     */
    public static void consultarUsuarioExistente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("|                        CONSULTA DE USUARIOS                      |");
        System.err.println("| Permite consultar un usuario existente por su nombre de usuario. |");   
        System.out.println("--------------------------------------------------------------------");
        System.out.print("Nombre de usuario a consultar: ");
        String usuarioBuscado = scanner.nextLine().trim();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] usuarioGuardado = linea.split(",");
                if (usuarioGuardado.length >= 6 && usuarioGuardado[2].equalsIgnoreCase(usuarioBuscado)) {
                    System.err.println("-----------------------------------------------------------");
                    System.err.println("|                Datos de Usuario Consultado              |");
                    System.err.println("-----------------------------------------------------------");
                    System.out.println("ID                       | " + usuarioGuardado[0]);
                    System.out.println("Empleado                 | " + usuarioGuardado[1]);
                    System.out.println("Usuario                  | " + usuarioGuardado[2]);
                    System.out.println("Rol                      | " + usuarioGuardado[4]);
                    System.out.println("Último Inicio de Sesión: |" + usuarioGuardado[5]);
                    System.out.println("----------------------------------------------------------");
                    System.out.println("");
                    encontrado = true;
                    
                    simulacionPrograma.continuarConTeclado();         
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        if (!encontrado) {
            System.out.println("Usuario no encontrado.");
            simulacionPrograma.simulaEjecucion();
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
