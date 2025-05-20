
package local.co.EasyPayroll;

import local.co.EasyPayroll.gestionEmpleado.gestionEmpleados;
import local.co.EasyPayroll.gestionInformes.informesNomina;
import local.co.EasyPayroll.gestionNomina.gestionNominas;
import local.co.EasyPayroll.gestionNovedades.novedades;
import local.co.EasyPayroll.gestionSeguridad.*;
import local.co.EasyPayroll.gestionContrato.*;



public class aplicacion {
    
    //private static final String ARCHIVO_EMPLEADOS = "empleados.txt";
    public static void main(String[] args) {

        
        //Variables globales.        
        
        // String rolUsuarioIngresado= "Administrador";
        String rolUsuarioIngresado= null ;
        String usuarioEnSesion = null;
        
        // String rolActual = "ADMINISTRADOR";
        
        // mostramos Mensaje de Bienvenida al Usuario.
         mostrarBienvenida();

        // se le solicitará al usuario iniciar sesion
          logginUsuario.solicitarDatosSesionInicial();

        //pruebas de escritorio para cada modulo
        // realizarPurebasaModulos(rolUsuarioIngresado, usuarioEnSesion);

    }
       
    private static void mostrarBienvenida() {
        System.out.println("===================================================================");
        System.out.println("          BIENVENIDO AL SISTEMA EASY PAYROLL");
        System.out.println("===================================================================");
        System.out.println("Nuestra App le permitirá realizar gestión inteligente de su nomina ");
        System.out.println( "- Realizar registros de contratos.");
        System.out.println("- Seguridad y control para cada empleado.");
        System.out.println("- Calcular automáticamente recargos, auxilios y deducciones.");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("        ¡Optimiza tu gestión de nómina desde aquí!");
        System.out.println("===================================================================");
    }

    private static void realizarPurebasaModulos(String rolUsuarioIngresad, String usuarioEnSesion){

        //prueba gestion de nomina
        //gestionNominas.gestionNomina();
        
        //GESTION CONTRATOS TODO OK
        //gestionContratos.gestionContrato();

        //GESTION EMPLEADO TODO OK
        //gestionEmpleados.gestionEmpleado(usuarioEnSesion);

        //Solicitamos Inicio de Sesion al usuario
        //logginUsuario.solicitarDatosSesionInicial();
                
        //Cargamos el menu del usuario
        // menuUsuarios.menuPrincipalUsuario(rolUsuarioIngresado);
        
        //gestionUsuarios.crearNuevoUsuario();
    
    }
}