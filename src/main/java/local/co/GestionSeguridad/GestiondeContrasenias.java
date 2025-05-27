package local.co.GestionSeguridad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import local.co.GestionUtilidades.DatosDeUsoGeneral;
import local.co.GestionUtilidades.LimpiarPantalla;
import local.co.GestionUtilidades.SimulacionPrograma;



public class GestiondeContrasenias {
    
      /**
     * Informa si un usuario existe para recuperar su cuenta.
     */
    public static void solicitarRecuperarContrasenia() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su nombre de usuario: ");
        String usuario = scanner.nextLine().trim();

        try (BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoUsuarios()))) {
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

    /**
     * Permite al usuario actualizar la contraseña asignada.
     */

    public static void cambiarContraseñiaUsuario(){
        
        LimpiarPantalla.limpiarConsola();
        Scanner scanner = new Scanner(System.in);

        System.out.println("|----------------------------------------------------------|");
        System.out.println("| BIENVENIDO AL MODULO DE RESTABLECIMIENTO DE CONTRASEÑA.  |");
        System.out.println("|==========================================================|");
        System.out.println("| En este modulo podrá realizar cambio de la contraseña    |");
        System.out.println("| asignada a su usuario.                                   |");
        System.out.println("| Por favor digite usuario y contraseña actual.            |");
        System.out.println("|----------------------------------------------------------|\n");
       
        System.out.print("Usuario: ");String usuarioIngresado = scanner.nextLine().trim();
        System.out.print
        ("Contraseña: ");String passwordIngresado = scanner.nextLine().trim();
        

        List<String> usuariosGuardados = new ArrayList<>();
        boolean encontrado = false;
        try (BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoUsuarios()))){
            String linea;
            while((linea =br.readLine())!=null){
                String [] datos =linea.split(",");
                if(datos.length>=6 && datos[2].equals(usuarioIngresado)&&datos[3].equals(passwordIngresado)){
                    System.out.print("\nIngrese su nueva contraseña: ");
                    datos[3] = scanner.nextLine().trim();
                    encontrado = true;
                    usuariosGuardados.add(String.join(",", datos));
                }
                usuariosGuardados.add(linea);
            }
        }catch (IOException e){
            System.err.println("Error al leer el archivo: "+ e.getMessage());
            return;
        }

        if(encontrado){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(DatosDeUsoGeneral.getArchivoUsuarios()))){
                for (String u : usuariosGuardados){
                    bw.write(u);
                    bw.newLine();
                }
                LimpiarPantalla.limpiarConsola();
                System.out.println("|--------------------------------------|");
                System.out.println("|            GUARDADO EXITOSO          |");
                System.out.println("| Contraseña Actualizada Correctalente |");
                System.out.println("|--------------------------------------|");
                SimulacionPrograma.simulaEjecucion();
                LimpiarPantalla.limpiarConsola();
                
            }catch(IOException e){
                System.out.println("Error al guardar cambios: " + e.getMessage());
            }
        }else{
            System.out.println("Usuario  o Contraseña Incorrecto.");
        }
    }

    public static boolean validarUsuarioActual (){

        boolean usuarioValidado = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("|----------------------------------------------------------|");
        System.out.println("|              ¡VALIDACION DE SEGURIDAD!                   |");
        System.out.println("| Por Seguridad es necesario atenticar sus credenciales    |");
        System.out.println("|             Ingrese usuario y contraseña.                |");
        System.out.println("|----------------------------------------------------------|");
        
        System.out.print("Usuario: ");
        String usuarioIngresado = scanner.nextLine().trim();
        System.out.print("Contraseña: ");
        String passwordIngresado = scanner.nextLine().trim();

        try (BufferedReader br = new BufferedReader(new FileReader(DatosDeUsoGeneral.getArchivoUsuarios()))){
            String linea;
            while((linea =br.readLine())!=null){
                String [] datos =linea.split(",");
                if(datos.length>=6 && datos[2].equals(usuarioIngresado)&&datos[3].equals(passwordIngresado)){
                    usuarioValidado = true;
                    break;
                } 
            }  
        }catch(IOException e){
            System.out.println("¡ERROR! Archivo Usuarios no encontrado.");
        }

        if (!usuarioValidado){
            System.out.println("¡ACCESO DENEGADO! Usuario o Contraseña Incorrecta");
            
        }
            return usuarioValidado;
    } 
    
    
}
