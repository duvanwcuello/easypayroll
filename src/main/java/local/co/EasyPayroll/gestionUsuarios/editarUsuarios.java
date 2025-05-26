package local.co.EasyPayroll.gestionUsuarios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import local.co.EasyPayroll.gestionUtilidades.datosDeUsoGeneral;
import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;

public class editarUsuarios {

    /**
     * Permite editar la contraseña y el rol de un usuario existente.
     * También actualiza la fecha del último inicio de sesión.
     */
    public static void editarUsuarioExistente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-----------------------------------");
        System.out.println("|       EDITANDO USUARIOS         |");
        System.out.println("-----------------------------------");
        System.out.print("Ingrese Usuario a Editar: ");
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
                    datos[4] = scanner.nextLine().trim().toUpperCase();
                    datos[5] = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    linea = String.join(",", datos);
                    encontrado = true;
                }
                usuariosActualizados.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
           // return;
        }

        if (encontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(datosDeUsoGeneral.getArchivoUsuarios()))) {
                for (String u : usuariosActualizados) {
                    bw.write(u);
                    bw.newLine();
                }
                limpiarPantalla.limpiarConsola();
                System.out.println("--------------------------------------");
                System.out.println("|         GUARDADO EXITOSO           |");
                System.out.println("| Usuario actualizado correctamente. |");
                System.out.println("--------------------------------------");
                simulacionPrograma.simulaEjecucion();
                limpiarPantalla.limpiarConsola();
               // menuUsuarios.menuPrincipalUsuario(usuarioIngresado);
            } catch (IOException e) {
                System.out.println("Error al guardar cambios: " + e.getMessage());
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }
    
}
