/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.co.EasyPayroll.gestionSeguridad;

import java.io.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import local.co.EasyPayroll.utilidades.datosDeUsoGeneral;

public class logginUsuario {
   
    /**
     * Método para solicitar los datos de inicio de sesión.
     * Si se validan correctamente, permite el acceso al menú principal según el rol.
     */
    public static void solicitarDatosSesionInicial() {
        for (int intento = 1; intento <= datosDeUsoGeneral.getIntentosMaximos(); intento++) {
            String usuarioIngresado = validarDatosIngresados("Ingrese Usuario Asignado: ");
            if (usuarioIngresado == null) cancelarOperacion("Usuario canceló el ingreso de usuario.");

            String passwordIngresado = validarDatosIngresados("Ingrese Contraseña: ");
            if (passwordIngresado == null) cancelarOperacion("Usuario canceló el ingreso de contraseña.");

            Usuario usuarioValidado = validarCredenciales(usuarioIngresado, passwordIngresado);
            if (usuarioValidado != null) {
                actualizarUltimaSesion(usuarioIngresado);
                System.out.println("\nBienvenido. " +usuarioValidado.getNombreEmpleado());
                menuUsuarios.menuPrincipalUsuario(usuarioValidado.getRol());
                return;
            } else {
                mostrarErrorDeInicio(intento, usuarioIngresado, passwordIngresado);
            }
        }

        System.out.println("\n-------------------------------------------------------\nHa superado el número de intentos permitidos.\n");
        System.exit(0);
    }

    /**
     * Valida entradas no vacías por el usuario.
     */
    private static String validarDatosIngresados(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        for (int intentos = 1; intentos <= datosDeUsoGeneral.getIntentosMaximos(); intentos++) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            if (entrada == null || entrada.trim().isEmpty()) {
                System.out.println("El campo no puede estar en blanco. Intento " + intentos + " de " + datosDeUsoGeneral.getIntentosMaximos());
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
        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 6 && datos[2].equals(usuarioIngresado) && datos[3].equals(passwordIngresado)) {
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

        try (BufferedReader br = new BufferedReader(new FileReader(datosDeUsoGeneral.getArchivoUsuarios()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 6 && datos[2].equals(nombreUsuario)) {
                    datos[5] = fechaActual;
                    linea = String.join(",", datos);
                }
                lineasActualizadas.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer para actualizar última sesión: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(datosDeUsoGeneral.getArchivoUsuarios()))) {
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
        System.exit(0);
    }

    /**
     * Muestra un mensaje de error si el inicio de sesión falla.
     */
    private static void mostrarErrorDeInicio(int intento, String usuarioIngresado, String passwordIngresado) {
        System.out.println("------------------------------------------------------");
        System.out.println("Usuario o Contraseña Incorrecta");
        System.out.println("Verifique los datos, Intento N°: " + intento);
        System.out.println("------------------------------------------------------");
    }

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
    }
}
