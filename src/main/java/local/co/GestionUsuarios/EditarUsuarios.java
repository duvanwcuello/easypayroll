package local.co.GestionUsuarios;

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

import local.co.GestionUtilidades.DatosDeUsoGeneral;
import local.co.GestionUtilidades.LimpiarPantalla;
import local.co.GestionUtilidades.SimulacionPrograma;


public class EditarUsuarios {

    /**
     * Permite editar la contraseña y el rol de un usuario existente.
     * También actualiza la fecha del último inicio de sesión.
     */
    public static void editarUsuarioExistente(String usuarioIngresado) {
       
        LimpiarPantalla.limpiarConsola();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--------------------------------------------------------------------");
        System.out.println("|           BIENVENIDO AL MODULO DE EDICION DE USUARIOS            |");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("| En este modulo podrá realizar:                                   |");
        System.out.println("| - Contraseñas.                                                   |");
        System.out.println("| - Rol de Usuario.                                                |");
        System.out.println("--------------------------------------------------------------------\n");
        
        List<String> usuariosActualizados = new ArrayList<>();
        boolean encontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoUsuarios())  )) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                if (datos.length >= 6 && datos[2].equalsIgnoreCase(usuarioIngresado)) {

                    System.out.println("\n-------------------------------------------------------------------");
                    System.err.println("|                    Datos Registrados Actualmente                 |");
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("Nombre Usuario:  | " + datos[1]);
                    System.out.println("Usuario Asignado | " + datos[2]);
                    System.out.println("Contraseña       | " + datos[3]);
                    System.out.println("Rol actual:      | " + datos[4]);
                    System.out.println("Última sesión:   | " + datos[5]);
                    System.out.println("-----------------------------------------------\n");

                    System.out.print("¿Desea continar la edicion? (S/N): ");
                    String confirmarEdicion = scanner.nextLine().trim().toUpperCase();

                        if (!confirmarEdicion.equals("S")) {      
                            SimulacionPrograma.continuarPrograma();
                            System.out.println("Cancelando Edicion de Usuario... No se realizaron cambios.");
                            SimulacionPrograma.simulaEjecucion();
                            
                            System.out.println("Retormando al Menu de Gestion de Usuarios...");
                            SimulacionPrograma.continuarPrograma();
                            LimpiarPantalla.limpiarConsola();
                            return; 
                        } else{
                            System.err.println("\n");
                            System.out.println("REGISTRE NUEVOS DATOS.");
                            System.out.print("Nueva contraseña: ");
                            String nuevaContrasena = scanner.nextLine().trim();
                            
                            while (nuevaContrasena.isEmpty()) {
                                System.out.print("La contraseña no puede estar vacía. Inténtelo de nuevo: ");
                                nuevaContrasena = scanner.nextLine().trim();
                            }
                            datos[3] = nuevaContrasena;

                            System.out.print("¿Desea Mondificar Rol? (S/N): ");
                            String confirmarRol = scanner.nextLine().trim().toUpperCase();
                                
                            if (confirmarRol.equals("S")) {
                                System.out.print("Nuevo rol (Administrador, Auxiliar, Coordinador): ");
                                String nuevoRol = scanner.nextLine().trim().toUpperCase();
                                while (nuevoRol.isEmpty()) {
                                    System.out.print("El rol no puede estar vacío. Inténtelo de nuevo: ");
                                    nuevoRol = scanner.nextLine().trim().toUpperCase();
                                }
                                datos[4] = nuevoRol; 
                            }                       
                        }
                    
                    System.out.print("\n¿Desea guardar los cambios? (S/N): ");
                    String confirmar = scanner.nextLine().trim().toUpperCase();
                            
                    if (!confirmar.equals("S")) {
                        SimulacionPrograma.continuarPrograma();
                        SimulacionPrograma.continuarPrograma();
                        System.out.println("Operación cancelada. No se realizaron cambios.");
                        SimulacionPrograma.simulaEjecucion();
                       
                       //gestionUsuarios.menuGestionUsuarios(usuarioIngresado);
                        return;
                    }else{
                        // Actualizar fecha de última modificación
                        datos[5] = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        // Reemplazamos la línea original con los nuevos datos
                        linea = String.join(",", datos);    
                        encontrado = true;    
                    }
                }
                // Se agrega la línea modificada o no modificado
                usuariosActualizados.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        if (encontrado) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(DatosDeUsoGeneral.getArchivoUsuarios()))) {
                for (String u : usuariosActualizados) {
                        bw.write(u);
                        bw.newLine();
                }

                LimpiarPantalla.limpiarConsola();
                System.out.println("--------------------------------------------------------------------");
                System.out.println("|                         GUARDADO EXITOSO                          |");
                System.out.println("|            Usuario actualizado correctamente.                     |");
                System.out.println("--------------------------------------------------------------------");
                SimulacionPrograma.simulaEjecucion();
                LimpiarPantalla.limpiarConsola();

                 // menuUsuarios.menuPrincipalUsuario(usuarioIngresado);
            } catch (IOException e) {
                System.out.println("Error al guardar cambios: " + e.getMessage());
                }
        }else {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("                            ¡¡ADVERTENCIA!!");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Usuario no encontrado...");
            SimulacionPrograma.continuarPrograma();
            System.out.println("Intente Nuevamente...");
            SimulacionPrograma.simulaEjecucion();
            editarUsuarioExistente(usuarioIngresado);
        }
        
    }    
}

