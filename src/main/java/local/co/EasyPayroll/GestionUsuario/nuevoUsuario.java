
package local.co.EasyPayroll.gestionUsuario;

import java.io.*;
import java.util.*;

import local.co.EasyPayroll.gestionUtilidades.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class nuevoUsuario {

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
            System.out.print("¿Desea editar el usuario existente?");
            System.err.print("(1. Sí | 2. No): ");
            int opcion = scanner.nextInt();
            
            scanner.nextLine();
            if (opcion == 1) {
                limpiarPantalla.limpiarConsola();
                editarUsuarios.editarUsuarioExistente();
            } else {
                System.out.println("Operación cancelada.....");
                System.out.println("Volviendo a Gestion de Usuarios...");
                simulacionPrograma.continuarPrograma();
                limpiarPantalla.limpiarConsola();
                gestionUsuarios.menuGestionUsuarios(usuarioNuevo);
            }
          // return;
        }
       
        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine().trim();

        System.out.print("Ingrese nombre completo del Nuevo Usuario: ");
        String nombreEmpleado = scanner.nextLine().trim();

        System.out.print("Ingrese rol a asignar \n(Administrador, Auxiliar, Coordinador): ");
        String rol = scanner.nextLine().trim().toUpperCase();

        String fechaRegistro = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(datosDeUsoGeneral.getArchivoUsuarios(), true))) {
            bw.write(contadorIdUsuario + "," + nombreEmpleado + "," + usuarioNuevo + "," + contrasena + "," + rol + "," + fechaRegistro);
            bw.newLine();
            System.out.println("Usuario Guardado Exitosamente.");
            contadorIdUsuario++;
            limpiarPantalla.limpiarConsola();
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
        }
        scanner.close();
    }
    
    //Cargamos el contador desde el archivo empleados
    private static void cargarContadorId() {

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoUsuarios()))) {
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
        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoUsuarios()))) {
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
