package local.co.EasyPayroll.gestionUsuarios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import local.co.EasyPayroll.gestionSeguridad.gestiondeContrasenias;
import local.co.EasyPayroll.gestionUtilidades.datosDeUsoGeneral;
import local.co.EasyPayroll.gestionUtilidades.limpiarPantalla;
import local.co.EasyPayroll.gestionUtilidades.simulacionPrograma;

public class eliminarUsuarios {
    
     public static void eliminarUsuarioGuardado() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------------------------------------------------------");
        System.out.println("|                        ELIMINAR USUARIO                    |");
        System.out.println("| Opere con cuidado, se eliminará todo registro del usuario. |");
        System.out.println("-------------------------------------------------------------");

        System.out.print("\n- Ingrese el nombre del usuario: ");
        String usuarioBuscado = scanner.nextLine().trim();

        List<String> usuariosActualizados = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length >= 6 && datos[2].equalsIgnoreCase(usuarioBuscado)) {

                    System.out.println("\n|---------------------------------------------|");
                    System.err.println("|        Datos Registrados Actualmente        |");
                    System.out.println("|---------------------------------------------|");
                    System.out.println("Nombre Usuario:  | " + datos[1]);
                    System.out.println("Usuario Asignado | " + datos[2]);
                    System.out.println("Contraseña       | " + datos[3]);
                    System.out.println("Rol actual:      | " + datos[4]);
                    System.out.println("Última sesión:   | " + datos[5]);
                    System.out.println("-----------------------------------------------\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }
        
        System.out.println("¿DESEA CONTINUAR CON LA ELIMINACION DEL USUARIO?");
        System.out.print("(S) SI   |   (N) NO : ");
        String confirmacionEliminar = scanner.nextLine().trim().toUpperCase();
        
        if (!confirmacionEliminar.equals("S")) {

           
            simulacionPrograma.continuarPrograma();
            simulacionPrograma.continuarPrograma();
            System.out.println("Operación cancelada. No se realizaron cambios.");
            simulacionPrograma.simulaEjecucion();
            return;
        }else{
            limpiarPantalla.limpiarConsola();

           /** implementar seguridad para la eliminacion
            * gestiondeContrasenias.validarUsuarioActual();
            *    
            */
            
            buscarUsuarioEliminara(usuarioBuscado);
        }
    }

    private static void buscarUsuarioEliminara(String usuarioBuscado){
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
                limpiarPantalla.limpiarConsola();
                System.out.println("""
                |---------------------------------------|
                | INFO: Usuario eliminado exitosamente. |
                |---------------------------------------|""");
                simulacionPrograma.continuarPrograma();
                limpiarPantalla.limpiarConsola();
                consultarUsuarios.consultarTodosLosUsuarios();
                System.out.println("");
                simulacionPrograma.continuarConTeclado();
                limpiarPantalla.limpiarConsola();
            } else {
                System.out.println("""
                --------------------------------------
                | ERROR: Usuario no encontrado.       |
                --------------------------------------
                """);
                simulacionPrograma.continuarConTeclado();
                limpiarPantalla.limpiarConsola();
            }
        } catch (IOException e) {
            System.out.println("""
            -------------------------------------------------
            | ERROR: No se pudo eliminar el usuario.        |
            -------------------------------------------------           
            """ + e.getMessage());
             simulacionPrograma.continuarConTeclado();
             limpiarPantalla.limpiarConsola();
        }
    }
    
}
