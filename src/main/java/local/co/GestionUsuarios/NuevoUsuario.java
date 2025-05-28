
package local.co.GestionUsuarios;

import java.io.*;
import java.util.*;

import local.co.GestionUtilidades.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NuevoUsuario {

    /**
     * Crea un nuevo usuario con ID único, nombre de empleado, usuario, contraseña, rol y fecha de registro.
     * Almacena la información en el archivo usuarios.txt.
     */
    public static void crearNuevoUsuario() {

        //leemos el ultimo Id del archivo
        int idDisponible = obtenerPrimerIdDisponible();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("|------------------------------------------------------------------|");
        System.out.println("|                      CREACION DE USUARIOS                        |");
        System.out.println("|              Registre los datos del nuevo Usuario                |");
        System.out.println("|------------------------------------------------------------------|");
        System.out.println("\nInicialmente se realizará una validacion \ncon los usuarios existentes\n");
        System.out.print("Ingrese nombre de usuario: ");
        String usuarioNuevo = scanner.nextLine().trim();
         
        //validamos si el usuario existe si existe consultamos al usuario si desea editarlo.
        if (usuarioYaExiste(usuarioNuevo)) {
                System.out.println("--------------------------------------------------------------------");
                System.out.println("\t\t¡ADVERTENCIA!");
                System.out.println("El nombre de usuario ( "+usuarioNuevo.toUpperCase()+" ), ya está en uso.");
                System.out.println("--------------------------------------------------------------------");
                System.out.println("¿Desea editar el usuario existente?");
                System.err.print("(1. Sí | 2. No): ");
            
                try {
                    int opcion = scanner.nextInt();
                    scanner.nextLine();
                      if (opcion == 1) {
                        LimpiarPantalla.limpiarConsola();
                        
                        //si va a editar el usuario pasamos como refernecia el usuario ingresado a la funcion editarUsuarioExistente
                        EditarUsuarios.editarUsuarioExistente(usuarioNuevo);
                    } else {
                        return;
                    }
                } catch (InputMismatchException e) {
                    scanner.nextLine();
                    LimpiarPantalla.limpiarConsola();
                    System.out.println(" ");
                    System.out.println("Dato Ingresado Inválido.");
                    System.out.println("Retornando al Menu Gestion de Usuarios");
                    SimulacionPrograma.continuarPrograma();
                    LimpiarPantalla.limpiarConsola();
                    return;
                }
            }else{

                //si no existe continuamos solicitando los datos y validamos que no esten en blanco

                System.out.print("Ingrese nombre completo del Nuevo Usuario: ");
                String nombreEmpleado = scanner.nextLine().trim();

                while (nombreEmpleado.isEmpty()) {
                    System.out.print("El nombre no puede estar vacío. Ingrese de nuevo: ");
                    nombreEmpleado = scanner.nextLine().trim();                    
                }
                
           /*    System.out.print("Ingrese contraseña: ");
            *    String contrasenaUsuario = scanner.nextLine().trim();
            *
            *    while (contrasenaUsuario.isEmpty()) {
            *    System.out.print("La contraseña no puede estar vacía. Ingrese de nuevo: ");
            *    contrasenaUsuario = scanner.nextLine().trim();
            *      
            *  }
            */  
                //Asignamos la misma contaseña al usuario
                String contrasenaUsuario =nombreEmpleado;

                //solicitamos el rol a asignar al usurio, modificamos los datos ingresados a tipo Mayuscula

                String rolNuevoUsuario= null;
                boolean continuar = true;
       
                while (continuar) {
                    try { 
                        System.out.println(" ");
                        System.out.println("|------------------------------------------------------------------|");
                        System.out.println("|                         ROLES DISPONIBLES.                       |");
                        System.out.println("|   * 1. Administrador. |   * 2. Auxiliar. |   * 3. Coordinador    |");
                        System.out.println("|------------------------------------------------------------------|");
                        System.out.print("Seleccione Rol: ");
                        int seleccionRol = scanner.nextInt();

                        //con Switch capturamos la opcion indicada por el usuario.  

                        switch (seleccionRol) {
                            case 1:
                                rolNuevoUsuario = "ADMINISTRADOR";
                                break;
                            case 2:
                                rolNuevoUsuario = "AUXILIAR";
                                break;
                            case 3:
                                rolNuevoUsuario = "COORDINADOR";
                                break;
                            default:
                                System.out.println("Opción no válida.");
                                continue;
                    }
                    continuar = false;
                }

                //mostramos error si el usuario ingresa un tipo de datos no esperado.

                catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Debe ingresar un número.");
                }
            }
            //actualizamos la modificacion de usuario.
            String fechaRegistro = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            //pasamos referencias a la funcion guardarEmpleadosEnArchivo para guardar el usuario en el archivo externo  
            //enviamos inicio de sesion null al archivo, este se actualizará cuando el usuario inicie sesion por primera vez
            String ultimoInicioSesion = null;
            guardarEmpleadoEnArchivo(idDisponible, nombreEmpleado, usuarioNuevo, contrasenaUsuario, rolNuevoUsuario, fechaRegistro, ultimoInicioSesion);
          
        }             
    }   
        
    //recorremos el archivo y ubicamos el id faltante 
    private static int obtenerPrimerIdDisponible() {
        Set<Integer> idsExistentes = new TreeSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 1) {
                    try {
                        int id = Integer.parseInt(datos[0]);
                        idsExistentes.add(id);
                    } catch (NumberFormatException ignored) {}
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer usuarios: " + e.getMessage());
        }

        int id = 1;
        while (idsExistentes.contains(id)) {
            id++;
        }
        return id;
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
                if (datos.length >= 7 && datos[2].equalsIgnoreCase(nombreUsuario)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            
        }
        return false;
    }

    private static void guardarEmpleadoEnArchivo(int idDisponible, String nombreEmpleado, String usuarioNuevo, String contrasenaUsuario, String rolUsuario, String fechaRegistro ,String ultimoInicioSesion ){
        boolean continuar = true;  
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DatosDeUsoGeneral.getArchivoUsuarios(), true))) {
                       
            //enviamos datos al archivo
            
            bw.write(idDisponible + "," + nombreEmpleado + "," + usuarioNuevo + "," + contrasenaUsuario + "," + rolUsuario + "," + fechaRegistro+","+ ultimoInicioSesion);
            bw.newLine();
            System.out.println("GUARDANDO USUARIO...");
            SimulacionPrograma.continuarPrograma();
            LimpiarPantalla.limpiarConsola();
            System.out.println("USUARIO GUARDADO EXITOSAMENTE...");
            idDisponible++;
            SimulacionPrograma.continuarPrograma();
            LimpiarPantalla.limpiarConsola();
            continuar = false;
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
        } 

    }
}
