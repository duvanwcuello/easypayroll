package local.co.EasyPayroll.gestionSeguridad;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import local.co.EasyPayroll.gestionUtilidades.datosDeUsoGeneral;

public class recuperarContrasenia {
    
      /**
     * Informa si un usuario existe para recuperar su cuenta.
     */
    public static void recuperarContrasena() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre de usuario: ");
        String usuario = scanner.nextLine().trim();

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 6 && datos[2].equals(usuario)) {
                    System.out.println("\nUsuario encontrado: " + datos[2]);
                    System.out.println("Por seguridad, contacte con el administrador para restablecer su contraseña.");
                    return;
                }
            }
            System.out.println("Usuario no encontrado.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de usuarios: " + e.getMessage());
        }
        scanner.close();
    }
}
