
package local.co.EasyPayroll.GestionUsuarios;

import java.io.*;
import java.util.*;

import local.co.EasyPayroll.GestionUtilidades.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NuevoUsuario {

    private static int contadorIdUsuario = 1;

    /**
     * Crea un nuevo usuario con ID único, nombre de empleado, usuario, contraseña, rol y fecha de registro.
     * Almacena la información en el archivo usuarios.txt.
     */
    public static void crearNuevoUsuario() {

        //leemos el ultimo Id del archivo
        cargarContadorId();

        Scanner scanner = new Scanner(System.in);

        System.out.println("---------------------------------------");
        System.out.println("|       CREANDO NUEVO USUARIO         |");
        System.out.println("---------------------------------------");
        
        System.out.print("Ingrese nombre de usuario: ");
        String usuarioNuevo = scanner.nextLine().trim();
         
        if (usuarioYaExiste(usuarioNuevo)) {
            System.out.println("---------------------------------------");
            System.out.println("\t\t¡ADVERTENCIA!");
            System.out.println("El nombre de usuario ( "+usuarioNuevo.toUpperCase()+" ), ya está en uso.");
            System.out.println("---------------------------------------");
            System.out.println("¿Desea editar el usuario existente?");
            System.err.print("(1. Sí | 2. No): ");
            int opcion = scanner.nextInt();
            
            scanner.nextLine();

            if (opcion == 1) {
                LimpiarPantalla.limpiarConsola();
                EditarUsuarios.editarUsuarioExistente();
            } else {
                System.out.println("Operación cancelada.....");
                System.out.println("Volviendo a Gestion de Usuarios...");
                SimulacionPrograma.simulaEjecucion();;
                LimpiarPantalla.limpiarConsola();
                GestionUsuarios.menuGestionUsuarios(usuarioNuevo);
            }
           return;
        }
    
        System.out.print("Ingrese nombre completo del Nuevo Usuario: ");
        String nombreEmpleado = scanner.nextLine().trim();

        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine().trim();

        String rol= null;
        boolean continuar = true;
       
            while (continuar) {
                try { 
                    System.out.println(" ");
                    System.out.println("---------------------");
                    System.out.println("Roles Disponibles.");
                    System.out.println("* 1. Administrador.\n* 2. Auxiliar.\n* 3. Coordinador");
                    System.out.println("---------------------");
                    System.out.print("Seleccione Rol: ");
                    int seleccionRol = scanner.nextInt();
                
                    switch (seleccionRol) {
                        case 1:
                            rol = "ADMINISTRADOR";
                            break;
                        case 2:
                            rol = "AUXILIAR";
                            break;
                        case 3:
                            rol = "COORDINADOR";
                            break;
                        default:
                            System.out.println("Opción no válida. Intente de nuevo.");
                            continue;
                    }
                    String fechaRegistro = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                    try (BufferedWriter bw = new BufferedWriter(new FileWriter(DatosDeUsoGeneral.getArchivoUsuarios(), true))) {
                        bw.write(contadorIdUsuario + "," + nombreEmpleado + "," + usuarioNuevo + "," + contrasena + "," + rol + "," + fechaRegistro);
                        bw.newLine();
                        System.out.println("GUARDANDO USUARIO...");
                        SimulacionPrograma.continuarPrograma();
                        LimpiarPantalla.limpiarConsola();
                        System.out.println("USUARIO GUARDADO EXITOSAMENTE...");
                        contadorIdUsuario++;
                        SimulacionPrograma.continuarPrograma();
                        LimpiarPantalla.limpiarConsola();
                        continuar = false;
                    } catch (IOException e) {
                        System.out.println("Error al guardar el usuario: " + e.getMessage());
                    }
                } catch (InputMismatchException e){
                    System.out.println("Intente nuevamente.");
                }
            }
    }
    
    //Cargamos el contador desde el archivo empleados
    private static void cargarContadorId() {

        try (BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            int maxId = 0;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                if (id > maxId) maxId = id;
            }
            contadorIdUsuario = maxId + 1;
        } catch (IOException e) {         
            System.out.println("No se pudo cargar el ID desde archivo: " + e.getMessage());
        }
    }

    /**
     * Verifica si un nombre de usuario ya existe en el archivo usuarios.txt.
     * @param nombreUsuario el nombre a verificar
     * @return true si el usuario ya está registrado, false en caso contrario
     */
    private static boolean usuarioYaExiste(String nombreUsuario) {
        try (BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoUsuarios()))) {
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


}
