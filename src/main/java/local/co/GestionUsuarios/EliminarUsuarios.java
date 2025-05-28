package local.co.GestionUsuarios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import local.co.GestionUtilidades.DatosDeUsoGeneral;
import local.co.GestionUtilidades.LimpiarPantalla;
import local.co.GestionUtilidades.SimulacionPrograma;


public class EliminarUsuarios {
    
     public static void eliminarUsuarioGuardado() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------------------------------------------------------");
        System.out.println("|                        ELIMINAR USUARIO                    |");
        System.out.println("| Opere con cuidado, se eliminará todo registro del usuario. |");
        System.out.println("-------------------------------------------------------------");

        System.out.print("\nIngrese el nombre del usuario: ");
        String usuarioBuscado = scanner.nextLine().trim();

        List<String> usuariosActualizados = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length >= 9 && datos[3].equalsIgnoreCase(usuarioBuscado)) {

                    System.out.println("--------------------------------------------------------------");
                    System.err.println("|                Datos Registrados Actualmente               |");
                    System.out.println("--------------------------------------------------------------");
                    System.out.println(" Id Unico                     | " + datos[0]);
                    System.out.println(" Estado                       | " + datos[1]);
                    System.out.println(" Nombre Usuario               | " + datos[2]);
                    System.out.println(" Usuario Asignado             | " + datos[3]);
                    System.out.println(" Rol Actual                   | " + datos[5]);
                    System.out.println(" Fecha de Creacion            | " + datos[6]);
                    System.out.println(" Ultimo cambio de contraseña  | " + datos[7]);
                    System.out.println(" Ultimo inicio sesion         | " + datos[8]);
                    System.out.println("--------------------------------------------------------------\n");
                    
                    if( datos[8] != null){

                        
                    }                
                
                
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }
        


        if()
        System.out.println("¿DESEA CONTINUAR CON LA ELIMINACION DEL USUARIO?");
        System.out.print("(S) SI   |   (N) NO : ");
        String confirmacionEliminar = scanner.nextLine().trim().toUpperCase();
        
        if (!confirmacionEliminar.equals("S")) {

           
            SimulacionPrograma.continuarPrograma();
            SimulacionPrograma.continuarPrograma();
            System.out.println("Operación cancelada. No se realizaron cambios.");
            SimulacionPrograma.simulaEjecucion();
            return;
        }else{
            LimpiarPantalla.limpiarConsola();

           /** implementar seguridad para la eliminacion
            * gestiondeContrasenias.validarUsuarioActual();
            *    
            */
            
            buscarUsuarioEliminara(usuarioBuscado);
        }
    }

    private static void buscarUsuarioEliminara(String usuarioBuscado){
        try {
            List<String> lineas = Files.readAllLines(Paths.get(DatosDeUsoGeneral.getArchivoUsuarios()));
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
                Files.write(Paths.get(DatosDeUsoGeneral.getArchivoUsuarios()),lineasActualizadas);
                LimpiarPantalla.limpiarConsola();
                System.out.println("|---------------------------------------|");
                System.out.println("| INFO: Usuario eliminado exitosamente. |");
                System.out.println("|---------------------------------------|");
                SimulacionPrograma.continuarPrograma();
                LimpiarPantalla.limpiarConsola();
                ConsultarUsuarios.consultarTodosLosUsuarios();
                System.out.println("");
                SimulacionPrograma.continuarConTeclado();
                LimpiarPantalla.limpiarConsola();
            } else {
                System.out.println("|---------------------------------------|");
                System.out.println("|   ERROR: Usuario no encontrado.       |");
                System.out.println("|---------------------------------------|");
                SimulacionPrograma.continuarConTeclado();
                LimpiarPantalla.limpiarConsola();
            }
        } catch (IOException e) {
            System.out.println("|---------------------------------------|");
            System.out.println("| ERROR: No se pudo eliminar el usuario.|");
            System.out.println("|---------------------------------------" + e.getMessage()+" |");
            System.out.println("|---------------------------------------");
            SimulacionPrograma.continuarConTeclado();
            LimpiarPantalla.limpiarConsola();
        }
    }
    
}
