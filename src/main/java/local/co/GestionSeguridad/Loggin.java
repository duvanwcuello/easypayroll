
package local.co.GestionSeguridad;

import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import local.co.GestionUsuarios.*;
import local.co.GestionUtilidades.DatosDeUsoGeneral;
import local.co.GestionUtilidades.LimpiarPantalla;
import local.co.GestionUtilidades.SimulacionPrograma;

public class Loggin {
   
    /**
     * Método para solicitar los datos de inicio de sesión.
     * Si se validan correctamente los datos ingresados, permite el acceso al menú principal según el rol.
     */
    public static void solicitarDatosSesionInicial(){

        for (int intento = 1; intento <= DatosDeUsoGeneral.getIntentosMaximos(); intento++){
            String usuarioIngresado = validarDatosIngresados("Ingrese Usuario Asignado: ");
            
            if (usuarioIngresado == null){
                System.out.println("--------------------------------------------------------------------\n");
                cancelarOperacion("Datos Errados de usuario.");
            } 
                String passwordIngresado = validarDatosIngresados("Ingrese Contraseña: ");
                if (passwordIngresado == null){
               
                System.out.println("--------------------------------------------------------------------");
                cancelarOperacion("Datos Errados de contraseña."); 
            }
            
            Usuario usuarioValidado = validarCredenciales(usuarioIngresado, passwordIngresado);
            
            if (usuarioValidado != null){

                //actualizamos la fecha de inicio de sesion
                actualizarUltimaSesion(usuarioIngresado);
                
                System.out.print("Ingresando...");
                SimulacionPrograma.simulaEjecucion();
                LimpiarPantalla.limpiarConsola();
                System.out.println("====================================================================");
                System.out.println("|                       BIENVENIDO A EASYPAYROLL                   |"); 
                System.out.println("====================================================================");
                System.out.println("| Usuario actual: " +usuarioValidado.getNombreEmpleado());
               
                MenuUsuarios.menuPrincipalUsuario(usuarioValidado.getRol());
                return;
            } else {
                mostrarErrorDeInicio(intento, usuarioIngresado, passwordIngresado);
            }
        }
        LimpiarPantalla.limpiarConsola();
        System.out.println("--------------------------------------------------------------------");
        System.out.println("            Ha superado el número de intentos permitidos.");
        System.out.println("--------------------------------------------------------------------");
        SimulacionPrograma.continuarPrograma();
        LimpiarPantalla.limpiarConsola();
        System.out.println("Cerrando el Programa...");        
        SimulacionPrograma.continuarPrograma();
        LimpiarPantalla.limpiarConsola();
        System.out.println("¡¡HASTA LUEGO!!..");
        SimulacionPrograma.continuarPrograma();
        System.exit(0);
    }

    /**
     * Valida entradas no vacías por el usuario.
     */
    private static String validarDatosIngresados(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        
        for (int intentos = 1; intentos <= DatosDeUsoGeneral.getIntentosMaximos(); intentos++) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            if (entrada == null || entrada.trim().isEmpty()) {
                System.out.println("El campo no puede estar en blanco. Intento " + intentos + " de " + DatosDeUsoGeneral.getIntentosMaximos());
            } else {
                return entrada.trim();
            }
        }
        return null;
    }

    /**
     * Busca en el archivo usuarios.txt si existe una combinación válida de usuario y contraseña.
     */
    private static Usuario validarCredenciales(String usuarioIngresado, String passwordIngresado) {
        try (BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 7 && datos[2].equals(usuarioIngresado) && datos[3].equals(passwordIngresado)) {
                    return new Usuario(datos[1], datos[2], datos[3], datos[4]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de usuarios: " + e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza la fecha de última sesión activa del usuario en el archivo.
     */
    private static void actualizarUltimaSesion(String nombreUsuario) {

        List<String> lineasActualizadas = new ArrayList<>();
        String fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        try (BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 7 && datos[2].equals(nombreUsuario)) {
                    datos[6] = fechaActual;
                    linea = String.join(",", datos);
                }
                lineasActualizadas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer para actualizar última sesión: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DatosDeUsoGeneral.getArchivoUsuarios()))) {
            for (String linea : lineasActualizadas) {
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar actualización de última sesión: " + e.getMessage());
        }
    }

    /**
     * Finaliza la ejecución mostrando un mensaje al usuario.
     */
    private static void cancelarOperacion(String mensaje) {
        System.out.println(mensaje);
        System.out.println("Operación cancelada. ¡Hasta luego!");
        System.out.println("Cerrando Inicio de Sesion....");
        System.out.println("--------------------------------------------------------------------");
        System.exit(0);
    }

    /**
     * Muestra un mensaje de error si el inicio de sesión falla.
     */
    private static void mostrarErrorDeInicio(int intento, String usuarioIngresado, String passwordIngresado) {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Usuario o Contraseña Incorrecta");
        System.out.println("\nVerifique los datos, Intento N°: " + intento+ " de " +DatosDeUsoGeneral.getIntentosMaximos());
        System.out.println("--------------------------------------------------------------------"); 
    }
}
