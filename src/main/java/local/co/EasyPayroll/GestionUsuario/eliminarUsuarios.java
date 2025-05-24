package local.co.EasyPayroll.gestionUsuario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import local.co.EasyPayroll.gestionUtilidades.datosDeUsoGeneral;
import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;

public class eliminarUsuarios {
    
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
                consultarUsuarios.consultarTodosLosUsuarios();
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
}
