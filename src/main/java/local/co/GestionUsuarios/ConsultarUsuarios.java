package local.co.GestionUsuarios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import local.co.GestionUtilidades.DatosDeUsoGeneral;
import local.co.GestionUtilidades.SimulacionPrograma;

public class ConsultarUsuarios {

    /**
     * Consulta un usuario existente por su nombre de usuario y muestra sus datos.
     */
    public static void consultarUsuarioExistente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("|------------------------------------------------------------------|");
        System.out.println("|                        CONSULTA DE USUARIOS                      |");
        System.err.println("| Permite consultar un usuario existente por su nombre de usuario. |");   
        System.out.println("|------------------------------------------------------------------|");
        System.out.print("Nombre de usuario a consultar: ");
        String usuarioBuscado = scanner.nextLine().trim();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] usuarioGuardado = linea.split(",");
                if (usuarioGuardado.length >= 8 && usuarioGuardado[3].equalsIgnoreCase(usuarioBuscado)) {
                    System.out.println("");
                    System.out.println("--------------------------------------------------------------------");
                    System.err.println("                    Datos de Usuario Consultado                    ");
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println(" Id Unico                     | " + usuarioGuardado[0]);
                    System.out.println(" Estado                       | " + usuarioGuardado[1]);
                    System.out.println(" Nombre Usuario               | " + usuarioGuardado[2]);
                    System.out.println(" Usuario Asignado             | " + usuarioGuardado[3]);
                    System.out.println(" Rol Actual del usuario       | " + usuarioGuardado[5]);
                    System.out.println(" Fecha de Creacion            | " + usuarioGuardado[6]);
                    System.out.println(" Ultimo cambio de contraseña  | " + usuarioGuardado[7]);
                    System.out.println(" Ultimo inicio sesion         | " + usuarioGuardado[8]);
                
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("");
                    encontrado = true;
                    
                    SimulacionPrograma.continuarConTeclado();         
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        if (!encontrado) {
            System.out.println("Usuario no encontrado.");
            SimulacionPrograma.simulaEjecucion();
        }
    }

        /**
     * Muestra una tabla con todos los usuarios registrados.
     * Incluye ID, nombre del empleado, nombre de usuario, contraseña cifrada, rol y último login.
     */
    public static void consultarTodosLosUsuarios() {
        
        //formateamos texto de salida para mostrarlo tipo tabla
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                                    CONSOLIDADO DE USUARIOS");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-4s %-6s %-25s %-10s %-10s %-15s %-20s %-23s %-20s%n", 
        "ID","Estado","Nombre Empleado","Usuario","Contraseña","Rol","Fecha de Creacion","Fecha Mod Contraseña","Último Login");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------");
        
        //leemos datos desde el archivo
        try (BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 9) {
                    System.out.printf("%-4s %-6s %-25s %-10s %-10s %-15s %-20s %-23s %-20s%n"
                    ,datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6], datos[7],datos[8]);
                }
            } System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
            
            
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
}
