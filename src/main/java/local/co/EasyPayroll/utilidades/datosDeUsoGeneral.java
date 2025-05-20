
package local.co.EasyPayroll.utilidades;

import java.io.*;
import java.util.Scanner;

public class datosDeUsoGeneral {

    private static final String ARCHIVO_EMPLEADOS = "empleados.txt";
    private static final String ARCHIVO_USUARIOS = "usuarios.txt";
    private static final String ARCHIVO_CONTRATOS = "contratos.txt";
   // static final Scanner SCANNER = new Scanner(System.in);
    private static final int INTENTOS_MAXIMOS = 3;

    // Getters para las rutas de archivos
    public static String getArchivoEmpleados() {
        asegurarArchivo(ARCHIVO_EMPLEADOS);
        return ARCHIVO_EMPLEADOS;
    }

    public static String getArchivoUsuarios() {
        asegurarArchivo(ARCHIVO_USUARIOS);
        return ARCHIVO_USUARIOS;
    }

    public static String getArchivoContratos() {
        asegurarArchivo(ARCHIVO_CONTRATOS);
        return ARCHIVO_CONTRATOS;
    }

    public static int getIntentosMaximos() {
        return INTENTOS_MAXIMOS;
    }

    // Método para verificar y crear archivos si no existen
    private static void asegurarArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (!archivo.exists()) {
            try {
                if (archivo.createNewFile()) {
                    System.out.println("Archivo creado: " + archivo.getAbsolutePath());
                }
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + rutaArchivo + ". " + e.getMessage());
            }
        }
    }

    // Método adicional específico para empleados (opcional)
    public static void leerArchivoEmpleados(String ruta) {
        File archivoEmpleados = new File(ruta);
        if (!archivoEmpleados.exists()) {
            System.out.println("VALIDE EL ARCHIVO EMPLEADOS. No existe en la ruta: " + ruta);
        }
    }
}


   
