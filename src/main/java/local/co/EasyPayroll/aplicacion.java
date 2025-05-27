package local.co.EasyPayroll;

import local.co.EasyPayroll.GestionSeguridad.GestiondeContrasenias;
import local.co.EasyPayroll.GestionSeguridad.Loggin;
import local.co.EasyPayroll.GestionSeguridad.MenuUsuarios;
import local.co.EasyPayroll.GestionUsuarios.EditarUsuarios;
import local.co.EasyPayroll.GestionUtilidades.LimpiarPantalla;
import local.co.EasyPayroll.GestionUtilidades.MensajesparaUsuarios;

public class Aplicacion {
    public static void main(String[] args) {

        String rolUsuarioIngresado= null;
        String usuarioEnSesion = null;
        //Produccion
        ejecutarProduccion(rolUsuarioIngresado);

        //pruebas
        //pruebasModulos(rolUsuarioIngresado, usuarioEnSesion);

    }

    private static void ejecutarProduccion(String rolUsuarioIngresado){
        
        // Instanciamos la clase limpiarPantalla
        LimpiarPantalla.limpiarConsola();
        
        // Llamamos al metodo que muestra la bienvenida
        MensajesparaUsuarios.mostrarBienvenida();
        
        // presionamos una tecla para continuar
        // continuarEjecucionPrograma.continuarPrograma();
        
        // Instanciamos la clase limpiarPantalla
        // limpiarPantalla.limpiarConsola();

        //Solicitamos Inicio de Sesion al usuario
        Loggin.solicitarDatosSesionInicial();

        //Limpiamos la pantalla
       // limpiarPantalla.limpiarConsola();

        //Cargamos el menu del usuario
        MenuUsuarios.menuPrincipalUsuario(rolUsuarioIngresado);

    }

    private static void pruebasModulos(String rolUsuarioIngresado, String usuarioEnSesion){

        //prueba gestion de nomina
        //gestionNominas.gestionNomina();
        
        // GESTION CONTRATOS 
        //gestionContratos.gestionContrato();

        // GESTION EMPLEADO 
        // gestionEmpleados.gestionEmpleado(usuarioEnSesion);

        // editarUsuarios.editarUsuarioExistente();

        // gestiondeContrasenias.cambiarContraseñiaUsuario();

        // Solicitamos Inicio de Sesion al usuario
        // logginUsuario.solicitarDatosSesionInicial();
                
        // Cargamos el menu del usuario
        // menuUsuarios.menuPrincipalUsuario(rolUsuarioIngresado);
        
        // menuUsuarios.menuPrincipalUsuario(rolUsuarioIngresado);;
    
    }
   
}


    